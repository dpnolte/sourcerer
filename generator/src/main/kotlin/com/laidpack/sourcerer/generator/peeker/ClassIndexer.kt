package com.laidpack.sourcerer.generator.peeker

import android.util.AttributeSet
import com.github.javaparser.JavaParser
import com.github.javaparser.ParseProblemException
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.ConstructorDeclaration
import com.laidpack.sourcerer.generator.*
import com.laidpack.sourcerer.generator.resources.WidgetConnoisseur
import com.laidpack.sourcerer.generator.resources.WidgetRegistry
import com.squareup.kotlinpoet.ClassName
import java.net.URI
import java.net.URL
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths


object ClassIndexer {
    fun scan(widgetRegistry: WidgetRegistry, reindexAll: Boolean = false) {
        if (reindexAll) {
            Store.deleteAll()
        }
        println("Starting class indexing...")
        for (path in widgetRegistry.paths) {
            val widgetParser = widgetRegistry[path]
            path.walk { url ->
                if (isEligibleFile(url)) {
                    println("Scanning for classes in ${url.file}")
                    println("----")
                    val cu = try {
                        JavaParser.parse(url.readText())
                    } catch (e: ParseProblemException) {
                        null
                    }
                    if (cu != null && cu.packageDeclaration.isPresent) {
                        val isAttributeSetImported = cu.imports.any { it.nameAsString == attributeSetCanonicalName }
                        val packageDeclaration = cu.packageDeclaration.get()
                        val indexedFile = FileRegistry.findOrCreate(path, url, cu)
                        val indexedPackage = PackageRegistry.findOrCreate(packageDeclaration.nameAsString)
                        val classOrInterfaceDeclarations = cu.descendantsOfType(ClassOrInterfaceDeclaration::class.java)
                        for (classOrInterfaceDeclaration in classOrInterfaceDeclarations) {
                            val targetClassName = ClassSymbolResolver.determineClassName(classOrInterfaceDeclaration, packageDeclaration)
                                    ?: continue

                            // check if class name is already registered
                            val hashCode = ClassRegistry.getHashCodeForNode(classOrInterfaceDeclaration, indexedPackage, indexedFile)
                            val canBeWidget = this.canClassBeWidget(classOrInterfaceDeclaration, widgetParser, url, isAttributeSetImported)
                            if (isClassEligibleForIndexing(targetClassName, hashCode, canBeWidget)) {
                                val indexedClass = ClassRegistry.add(
                                        targetClassName,
                                        hashCode,
                                        classOrInterfaceDeclaration.isNestedType,
                                        indexedFile,
                                        indexedPackage
                                )
                                var widgetLabel = ""
                                if (canBeWidget) {
                                    if (this.canClassBeWidgetLayoutParams(classOrInterfaceDeclaration, widgetParser, url)) {
                                        val widget = widgetRegistry.findOrCreate(indexedFile)
                                        ClassRegistry.assignClassToWidgetAsLayoutParams(indexedClass, widget)
                                        widgetLabel = " + widget layout params"
                                    } else if (this.canClassBeWidgetView((classOrInterfaceDeclaration))) {
                                        val widget = widgetRegistry.findOrCreate(indexedFile)
                                        ClassRegistry.assignClassToWidgetAsView(indexedClass, widget)
                                        widgetLabel = " + widget view"
                                    }
                                }
                                println("Indexed class$widgetLabel -> $targetClassName")
                            }
                        }
                    }
                    println("=================================")
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

    private fun isEligibleFile(url: URL): Boolean {
        return url.file.endsWith(".java")
                && !FileRegistry.isFileIndexed(url)
    }


    private fun isClassEligibleForIndexing(className: ClassName, hashCode: Int, newClassCanBeWidget: Boolean): Boolean {
        // check if class already is registered, and if so if a widget has been associated to old one
        return ClassRegistry.isClassEligibleForIndexing(className, hashCode, newClassCanBeWidget)
    }

    private fun canClassBeWidget(classDeclaration: ClassOrInterfaceDeclaration, widgetParser: WidgetConnoisseur, fileUrl: URL, isAttributeSetImported: Boolean = true): Boolean {
        val javaDocIsEligible = if (classDeclaration.comment.isPresent) {
            val javaDoc = JavaParser.parseJavadoc(classDeclaration.comment.get().toString())
            javaDoc.blockTags.all {
                it.tagName != "hide"
            }
        } else true
        return isAttributeSetImported
                && javaDocIsEligible
                && classDeclaration.isPublic
                && !classDeclaration.isInterface
                && !classDeclaration.isLocalClassDeclaration
                && classDeclaration.annotations.all {
                    it.nameAsString != "Deprecated"
                    && it.nameAsString != "RestrictTo"
                }
                && widgetParser.isValidWidgetPath(Paths.get(fileUrl.toURI()))
    }

    private fun canClassBeWidgetView(classDeclaration: ClassOrInterfaceDeclaration): Boolean {
        if (!classDeclaration.isStatic) {
            val constructors = classDeclaration.descendantsOfType(ConstructorDeclaration::class.java)
            return constructors.any {
                it.parameters.any {
                    it.type.isClassOrInterfaceType
                            && it.type.asClassOrInterfaceType().nameAsString == attributeSetType.simpleName
                }
            }
        }
        return false
    }

    private fun canClassBeWidgetLayoutParams(classDeclaration: ClassOrInterfaceDeclaration, widgetParser: WidgetConnoisseur, fileUrl: URL): Boolean {
        if (classDeclaration.isNestedType && classDeclaration.isStatic) {
            val parentClassDeclaration = classDeclaration.firstAncestorOfType(ClassOrInterfaceDeclaration::class.java)
            return parentClassDeclaration != null
                    && canClassBeWidget(parentClassDeclaration, widgetParser, fileUrl)
                    && canClassBeWidgetView(parentClassDeclaration)
        }
        return false
    }

    private val attributeSetType = AttributeSet::class
    private val attributeSetCanonicalName = attributeSetType.java.canonicalName
}

