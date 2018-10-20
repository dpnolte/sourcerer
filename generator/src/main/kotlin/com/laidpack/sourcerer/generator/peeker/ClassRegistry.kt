package com.laidpack.sourcerer.generator.peeker


import com.github.javaparser.JavaParser
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.resources.*
import com.squareup.kotlinpoet.ClassName
import kotlinx.dnq.query.*
import java.util.*
import kotlin.IllegalArgumentException

object ClassRegistry {
    private val classBodyPeeker : ClassBodyPeeker = ClassBodyPeeker()
    private val classSymbolResolver: ClassSymbolResolver = ClassSymbolResolver()

    fun add(
            className: ClassName,
            nodeHashCode: Int,
            isNestedType: Boolean,
            xdFile: XdFile,
            providedPackage: XdPackage
    ): XdClass {
        return Store.transactional {
            val indexedClass= XdClass.new()
            indexedClass.canonicalName = className.canonicalName
            indexedClass.simpleName = className.simpleName
            indexedClass.nodeHashCode = nodeHashCode
            indexedClass.isNestedType = isNestedType
            indexedClass.file = xdFile
            indexedClass.xdPackage = providedPackage
            indexedClass.resolvedClassSymbol = false
            indexedClass.resolvedWidgetSymbol = false
            indexedClass
        }
    }
    operator fun get(className: ClassName): XdClass? {
        val canonicalName = className.canonicalName
        return this[canonicalName]
    }
    operator fun get(canonicalName: String): XdClass? {
        return Store.transactional {
            XdClass.query(XdClass::canonicalName eq canonicalName).firstOrNull()
        }
    }
    operator fun get(hashCode: Int): XdClass? {
        return Store.transactional {
            XdClass.query(XdClass::nodeHashCode eq hashCode).firstOrNull()
        }
    }

    fun findBySimpleName(simpleName: String): List<XdClass> {
        return Store.transactional {
            XdClass.query(XdClass::simpleName eq simpleName).toList()
        }
    }
    fun findByCanonicalName(canonicalName: String): XdClass? {
        return Store.transactional {
            XdClass.query(XdClass::canonicalName eq canonicalName).firstOrNull()
        }
    }

    fun isClassEligibleForIndexing(className: ClassName, nodeHash: Int, newClassCanBeWidget: Boolean): Boolean {
        // check if class already is registered, and if so if a widget has been associated to old one
        return Store.transactional {
            if (this[nodeHash] != null) {
                false
            } else {
                val existingClass = this[className]
                val eligible = existingClass?.widget == null
                        || newClassCanBeWidget && (existingClass.widget as Widget).source == XdWidgetSource.Android
                if (eligible && existingClass != null) {
                    existingClass.file.classes.remove(existingClass)
                    existingClass.xdPackage.classes.remove(existingClass)
                    if (existingClass.widgetAsBeingView != null) {
                        val widget = existingClass.widgetAsBeingView as Widget
                        widget.delete()
                        existingClass.widgetAsBeingView = null

                    }
                    if (existingClass.widgetAsBeingLayoutParams != null) {
                        val widget = existingClass.widgetAsBeingLayoutParams as Widget
                        widget.layoutParamClasses.remove(existingClass)
                        existingClass.widgetAsBeingLayoutParams = null
                    }
                    existingClass.delete()
                }
                eligible
            }
        }
    }

    fun assignClassToWidgetAsView(xdClass: XdClass, widget: Widget) {
        Store.transactional {
            widget.viewClass = xdClass
        }
    }
    fun assignClassToWidgetAsLayoutParams(xdClass: XdClass, widget: Widget) {
        Store.transactional {
            widget.layoutParamClasses.add(xdClass)
        }
    }
    fun unassignClassFromWidget(xdClass: XdClass) {
        Store.transactional {
            val widget = Widget.query(
                    (Widget::viewClass eq xdClass)
                    or
                    (Widget::layoutParamClasses contains xdClass)
            ).firstOrNull() ?: throw IllegalArgumentException("Indexed class ${xdClass.targetClassName} with file ${xdClass.file.url} has no associated widget")
            if (widget.viewClass == xdClass) {
                widget.viewClass = null
            } else {
                widget.layoutParamClasses.remove(xdClass)
            }
            if (widget.viewClass == null && widget.layoutParamClasses.isEmpty) {
                widget.delete()
            }
        }
    }
    fun assignClassCategory(xdClass: XdClass, classCategory: ClassCategory) {
        Store.transactional {
            when (classCategory) {
                ClassCategory.View -> xdClass.classCategory = XdClassCategory.View
                ClassCategory.LayoutParams -> xdClass.classCategory = XdClassCategory.LayoutParams
            }
        }
    }
    fun assignViewGroupFlag(xdClass: XdClass, isViewGroup: Boolean) {
        Store.transactional {
            xdClass.isViewGroup = isViewGroup
        }
    }
    fun assignSuperClasses(xdClass: XdClass, superClasses: List<XdClass>) {
        Store.transactional {
            xdClass.superClasses.clear()
            xdClass.superClasses.addAll(superClasses)
        }
    }

    fun getSuperClasses(xdClass: XdClass): List<XdClass> {
        return Store.transactional {
            xdClass.superClasses.toList()
        }
    }

    fun assignResolvementStatus(xdClass: XdClass, resolvedClassSymbol: Boolean, resolvedWidgetSymbol: Boolean) {
        return Store.transactional {
            xdClass.resolvedClassSymbol = resolvedClassSymbol
            xdClass.resolvedWidgetSymbol = resolvedWidgetSymbol
        }
    }


    fun getPotentialWidgetClasses(): List<XdClass> {
        return Store.transactional {
            XdClass.query(
                    (XdClass::widgetAsBeingLayoutParams ne null) or (XdClass::widgetAsBeingView ne null)
            ).toList()
        }
    }

    fun isClassSymbolResolved(xdClass: XdClass): Boolean {
        return Store.transactional {
            xdClass.resolvedClassSymbol
        }
    }

    fun isClassResolvedAsWidget(xdClass: XdClass): Boolean {
        return Store.transactional {
            xdClass.resolvedWidgetSymbol
        }
    }
    fun getClassSymbolDescription(xdClass: XdClass): ClassSymbolDescription? {
        return if (Store.transactional { xdClass.resolvedWidgetSymbol }) {
            getResolvedClassSymbolDescription(xdClass)
        } else {
            val resolvedClassAndSuperClasses = classSymbolResolver.resolve(xdClass)
            if (resolvedClassAndSuperClasses.isNotEmpty()) {
                resolvedClassAndSuperClasses.first()
            } else {
                null
            }
        }
    }
    fun getResolvedClassSymbolDescription(xdClass: XdClass): ClassSymbolDescription {
        return Store.transactional {
            if (!xdClass.resolvedClassSymbol)
                throw IllegalArgumentException("Class is not resolved")
            val classCategory = xdClass.classCategory ?: throw IllegalArgumentException("Class is flagged as resolved but has no class category")

            ClassSymbolDescription(
                    xdClass.targetClassName,
                    xdClass.widget,
                    classCategory.toEnum(),
                    xdClass.isViewGroup,
                    xdClass.classOrInterfaceDeclarationProvider,
                    xdClass.superClasses.toList().map { indexedClass ->
                        indexedClass.targetClassName
                    },
                    xdClass
            )
        }
    }

    fun getHashCodeForNode(classDeclaration: ClassOrInterfaceDeclaration, xdPackage: XdPackage, xdFile: XdFile): Int {
        return Store.transactional {
            getHashCodeForNode(
                    classDeclaration,
                    xdPackage.packageName,
                    xdFile.urlAsString
            )
        }
    }
    fun getHashCodeForNode(classDeclaration: ClassOrInterfaceDeclaration, packageName: String, filePathAsString: String): Int {
        return Objects.hash(
                classDeclaration.nameAsString,
                classDeclaration.begin.get().line,
                classDeclaration.end.get().line,
                packageName,
                filePathAsString
        )
    }

    fun getClassInfo(xdClass: XdClass): ClassInfo? {
        val resolvedClass = getClassSymbolDescription(xdClass)
        if (resolvedClass == null) {
            println("\tXX -> ${xdClass.targetClassName} is not an eligible class symbol. Skipping it")
            return null
        }
        return classBodyPeeker.peek(resolvedClass)
    }

    fun resolveAnyNewIdentifiedWidgetClasses() {
        classSymbolResolver.resolve(
                ClassRegistry.getUnresolvedPotentialWidgetClasses()
        )
    }
    private fun getUnresolvedPotentialWidgetClasses(): List<XdClass> {
        return Store.transactional {
            XdClass.query(
                    ((XdClass::widgetAsBeingLayoutParams ne  null)
                            or
                            (XdClass::widgetAsBeingView ne null))
                            and
                            (XdClass::resolvedClassSymbol eq false)
            ).toList()
        }
    }

    private val typedArrayMethodsPerSdk: MutableMap<Int, TypedArrayInfo> = mutableMapOf()
    fun getTypedArrayInfo(sdkStructure: AndroidSdkStructure): TypedArrayInfo {
        if (!typedArrayMethodsPerSdk.containsKey(sdkStructure.apiLevel)) {
            val typedArrayCu = JavaParser.parse(sdkStructure.getSourceFileContent(TypedArrayPeeker.typedArrayClassType))
            val typedArrayPeeker = TypedArrayPeeker(typedArrayCu)
            typedArrayMethodsPerSdk[sdkStructure.apiLevel] = typedArrayPeeker.peek()

        }
        return typedArrayMethodsPerSdk[sdkStructure.apiLevel] as TypedArrayInfo
    }

}

