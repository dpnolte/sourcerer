package com.laidpack.sourcerer.generator.index

import android.util.AttributeSet
import com.github.javaparser.JavaParser
import com.github.javaparser.ParseProblemException
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.ConstructorDeclaration
import com.github.javaparser.ast.body.EnumDeclaration
import com.github.javaparser.ast.body.TypeDeclaration
import com.laidpack.sourcerer.generator.*
import com.laidpack.sourcerer.generator.resources.widgets.WidgetConnoisseur
import com.laidpack.sourcerer.generator.resources.WidgetRegistry
import com.squareup.kotlinpoet.ClassName
import java.net.URI
import java.net.URL
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths


object TypeIndexer {
    fun scan(widgetRegistry: WidgetRegistry, reindexAll: Boolean = false) {
        if (reindexAll) {
            Store.deleteAll()
        }
        println("Initiating type declarations scan...")
        for (path in widgetRegistry.paths) {
            val widgetConnoisseur = widgetRegistry[path]
            path.walk { url ->
                scanFile(path, url, widgetRegistry, widgetConnoisseur)
            }
        }
    }

    fun scanFile(
            scanPath: Path,
            url: URL,
            widgetRegistry: WidgetRegistry,
            providedWidgetConnoisseur: WidgetConnoisseur? = null,
            forceScan: Boolean = false
    ) {
        val widgetConnoisseur = providedWidgetConnoisseur ?: widgetRegistry[scanPath]
        if (isEligibleFile(url, forceScan)) {
            println("Scanning type declarations in ${url.file}")
            println("----")
            val cu = try {
                JavaParser.parse(url.readText())
            } catch (e: ParseProblemException) {
                null
            }
            if (cu != null) {
                scanCompilationUnit(
                        cu,
                        scanPath,
                        url,
                        widgetConnoisseur,
                        widgetRegistry
                )
            }
            println("=================================")
        }
    }

    fun scanCompilationUnit(
            cu: CompilationUnit,
            scanPath: Path,
            url: URL,
            providedWidgetConnoisseur: WidgetConnoisseur? = null,
            providedWidgetRegistry: WidgetRegistry? = null,
            checkIfTypeIsWidget: Boolean = true
    ) {
        if (cu.packageDeclaration.isPresent) {
            val isAttributeSetImported = cu.imports.any { it.nameAsString == attributeSetCanonicalName }
            val packageDeclaration = cu.packageDeclaration.get()
            val indexedFile = FileRegistry.findOrCreate(scanPath, url, cu)
            val indexedPackage = PackageRegistry.findOrCreate(packageDeclaration.nameAsString)
            val typeDeclarations = cu.descendantsAndPatshOfType(TypeDeclaration::class.java)
            for ((typeDeclaration, path) in typeDeclarations) {
                val targetClassName = DeclaredSymbolResolver.determineClassName(typeDeclaration, packageDeclaration)
                // check if class name is already indexed
                val hashCode = DeclaredTypeRegistry.getHashCodeForNode(typeDeclaration, indexedPackage, indexedFile)
                val canBeWidget = checkIfTypeIsWidget
                        && canTypeBeWidget(
                        typeDeclaration,
                        providedWidgetConnoisseur ?: throw IllegalStateException("widgetConnoisseur is null while checkIfTypeIsWidget is true"),
                        url,
                        isAttributeSetImported
                )
                if (isClassEligibleForIndexing(targetClassName, hashCode, canBeWidget)) {
                    val xdDeclaredType = DeclaredTypeRegistry.add(
                            targetClassName,
                            hashCode,
                            typeDeclaration.isNestedType,
                            indexedFile,
                            indexedPackage,
                            path,
                            typeDeclaration is ClassOrInterfaceDeclaration && !typeDeclaration.isInterface,
                            typeDeclaration is ClassOrInterfaceDeclaration && typeDeclaration.isInterface,
                            typeDeclaration is EnumDeclaration
                    )
                    var widgetLabel = ""
                    if (canBeWidget) {
                        val widgetConnoisseur = providedWidgetConnoisseur
                                ?: throw IllegalStateException("widgetConnoisseur is null while checkIfTypeIsWidget is true")
                        val widgetRegistry = providedWidgetRegistry
                                ?: throw IllegalStateException("widgetRegistry is null while checkIfTypeIsWidget is true")
                        if (this.canClassBeWidgetLayoutParams(typeDeclaration, widgetConnoisseur, url)) {
                            val widget = widgetRegistry.findOrCreate(indexedFile)
                            DeclaredTypeRegistry.assignClassToWidgetAsLayoutParams(xdDeclaredType, widget)
                            widgetLabel = " + widget layout params"
                        } else if (this.canClassBeWidgetView((typeDeclaration))) {
                            val widget = widgetRegistry.findOrCreate(indexedFile)
                            DeclaredTypeRegistry.assignClassToWidgetAsView(xdDeclaredType, widget)
                            widgetLabel = " + widget view"
                        }
                    }
                    val typeLabel = when {
                        typeDeclaration is ClassOrInterfaceDeclaration && typeDeclaration.isInterface -> "interface"
                        typeDeclaration is ClassOrInterfaceDeclaration -> "class"
                        else -> "enum"
                    }
                    println("Indexed $typeLabel$widgetLabel -> $targetClassName")
                }
            }
        }
    }

    private fun Path.walk(block: (URL) -> Unit) {
        if (this.toString().endsWith(".jar")) {
            val jarFileUriAsString = this.toUri().toString()
            val uri = URI("jar", jarFileUriAsString, null)
            val env = mutableMapOf<String, Any>()
            env["create"] = "true"

            val fileSystem = FileSystems.newFileSystem(uri, env)
            fileSystem
                    .rootDirectories
                    .forEach { root ->
                        Files.walk(root).forEach { path ->
                            val url = URL(uri.toString() + "!" + path.toString())
                            block(url)
                        }
                    }
            fileSystem.close()
        } else {
            this.toFile().walk().forEach { file ->
                block(file.toURI().toURL())
            }
        }

    }

    private fun isEligibleFile(url: URL, forceScan: Boolean): Boolean {
        return (url.file.endsWith(".java") || url.file.endsWith(".aidl"))
                && (forceScan || !FileRegistry.isFileIndexed(url))
    }


    private fun isClassEligibleForIndexing(className: ClassName, hashCode: Int, newClassCanBeWidget: Boolean): Boolean {
        // check if class already is registered, and if so if a widget has been associated to old one
        return DeclaredTypeRegistry.isClassTypeEligibleForIndexing(className, hashCode, newClassCanBeWidget)
    }

    private fun canTypeBeWidget(typeDeclaration: TypeDeclaration<*>, widgetParser: WidgetConnoisseur, fileUrl: URL, isAttributeSetImported: Boolean = true): Boolean {
        if (typeDeclaration is ClassOrInterfaceDeclaration && isAttributeSetImported) {
            val javaDocIsEligible = if (typeDeclaration.comment.isPresent) {
                val javaDoc = JavaParser.parseJavadoc(typeDeclaration.comment.get().toString())
                javaDoc.blockTags.all {
                    it.tagName != "hide"
                }
            } else true
            return javaDocIsEligible
                    && typeDeclaration.isPublic
                    && !typeDeclaration.isInterface
                    && !typeDeclaration.isLocalClassDeclaration
                    && typeDeclaration.annotations.all {
                        it.nameAsString != "Deprecated"
                        && it.nameAsString != "RestrictTo"
                    }
                    && widgetParser.isValidWidgetPath(Paths.get(fileUrl.toURI()))
        }
        return false
    }

    private fun canClassBeWidgetView(typeDeclaration: TypeDeclaration<*>): Boolean {
        if (!typeDeclaration.isStatic) {
            val constructors = typeDeclaration.descendantsOfType(ConstructorDeclaration::class.java)
            return constructors.any {
                it.parameters.any {
                    it.type.isClassOrInterfaceType
                            && it.type.asClassOrInterfaceType().nameAsString == attributeSetType.simpleName
                }
            }
        }
        return false
    }

    private fun canClassBeWidgetLayoutParams(typeDeclaration: TypeDeclaration<*>, widgetParser: WidgetConnoisseur, fileUrl: URL): Boolean {
        if (typeDeclaration.isNestedType && typeDeclaration.isStatic) {
            val parentClassDeclaration = typeDeclaration.firstAncestorOfType(ClassOrInterfaceDeclaration::class.java)
            return parentClassDeclaration != null
                    && canTypeBeWidget(parentClassDeclaration, widgetParser, fileUrl)
                    && canClassBeWidgetView(parentClassDeclaration)
        }
        return false
    }

    private val attributeSetType = AttributeSet::class
    private val attributeSetCanonicalName = attributeSetType.java.canonicalName
}

