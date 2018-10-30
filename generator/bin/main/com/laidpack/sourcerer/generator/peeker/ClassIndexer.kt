package com.laidpack.sourcerer.generator.peeker

import android.util.AttributeSet
import com.github.javaparser.JavaParser
import com.github.javaparser.ParseProblemException
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.ConstructorDeclaration
import com.laidpack.sourcerer.generator.*
import com.laidpack.sourcerer.generator.resources.Widget
import com.laidpack.sourcerer.generator.resources.WidgetConnoisseur
import com.laidpack.sourcerer.generator.resources.WidgetRegistry
import com.laidpack.sourcerer.generator.resources.getWidgetRegistry
import com.squareup.kotlinpoet.ClassName
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*
import kotlinx.dnq.enum.XdEnumEntityType
import kotlinx.dnq.query.eq
import kotlinx.dnq.query.first
import kotlinx.dnq.query.query
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
                        val indexedFile = FileRegistry.findOrCreate(path, url)
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


class IndexedClass(
        entity: Entity
) : XdEntity(entity) {
    companion object : XdNaturalEntityType<IndexedClass>()
    var canonicalName by xdRequiredStringProp(unique = true, trimmed = true)
    var simpleName by xdRequiredStringProp(trimmed = true)
    private var mutableTargetClass: ClassName? = null
    val targetClassName : ClassName
        get() {
            if (mutableTargetClass == null) {
                Store.transactional {
                    mutableTargetClass = ClassName.bestGuess(canonicalName)
                }
            }
            return mutableTargetClass as ClassName
        }

    var classCategory by xdLink0_1(XdClassCategory)
    var isViewGroup by xdBooleanProp()
    var resolvedClassSymbol by xdBooleanProp ()
    var resolvedWidgetSymbol by xdBooleanProp ()
    var nodeHashCode: Int by xdRequiredIntProp(unique = true)
    var file : IndexedFile by xdParent(IndexedFile::classes)
    var sourcererResult by xdChild0_1(XdSourcererResult::targetClass)
    var indexedPackage : IndexedPackage by xdLink1(IndexedPackage::classes)
    val superClasses by xdLink0_N(IndexedClass)
    var widgetAsBeingView : Widget? by xdLink0_1(Widget::viewClass)
    var widgetAsBeingLayoutParams : Widget? by xdLink0_1(Widget::layoutParamClasses)
    val widget : Widget?
        get() {
            return Store.transactional {
                when {
                    widgetAsBeingView != null -> widgetAsBeingView
                    widgetAsBeingLayoutParams != null -> widgetAsBeingLayoutParams
                    else -> null
                }
            }
        }
    private var cachedNode : ClassOrInterfaceDeclaration? = null
    val node : ClassOrInterfaceDeclaration
        get() {
            /*if (cachedNode == null) {
                Store.transactional {
                    //val scope = canonicalName.replace(xdPackage.packageName + ".", "")
                    cachedNode = JavaParser.parse(file.paths.toFile())
                            .firstDescendantOfType(ClassOrInterfaceDeclaration::class.java) { getClassOrInterfaceDeclaration ->
                                getClassOrInterfaceDeclaration.name.identifier == simpleName
                            }
                    if (cachedNode == null) {
                        throw IllegalStateException("Indexed class getClassOrInterfaceDeclaration '$targetClassName' cannot be resolved @ ${file.urlAsString}")
                    }
                }
            }
            return cachedNode as ClassOrInterfaceDeclaration*/
            return Store.transactional {
                JavaParser.parse(file.content)
                        .firstDescendantOfType(ClassOrInterfaceDeclaration::class.java) { node ->
                            node.name.identifier == simpleName
                        } as ClassOrInterfaceDeclaration
            }
        }

}

enum class ClassCategory {
    View,
    LayoutParams;

    fun toEntity(): XdClassCategory {
        return XdClassCategory.query(
                XdClassCategory::presentation eq this.name
        ).first()
    }
}

class XdClassCategory (entity: Entity) : XdEnumEntity(entity) {
    companion object : XdEnumEntityType<XdClassCategory>() {
        val LayoutParams by enumField { presentation = ClassCategory.LayoutParams.name}
        val View by enumField { presentation = ClassCategory.View.name}
    }

    var presentation by xdRequiredStringProp(unique = true, trimmed = true)
    fun toEnum(transaction: Boolean = true): ClassCategory {
        val block =  {
            enumValueOf<ClassCategory>(this.presentation)
        }
        return if(transaction) Store.transactional { block() } else block()
    }
}
