package com.laidpack.sourcerer.generator.index


import com.github.javaparser.JavaParser
import com.github.javaparser.ast.body.TypeDeclaration
import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.resources.*
import com.laidpack.sourcerer.generator.resources.widgets.XdWidgetSource
import com.squareup.kotlinpoet.ClassName
import kotlinx.dnq.query.*
import java.util.*
import kotlin.IllegalArgumentException

object DeclaredTypeRegistry {
    private val superClassResolver: DeclaredSymbolResolver = DeclaredSymbolResolver()

    fun add(
            className: ClassName,
            nodeHashCode: Int,
            isNestedType: Boolean,
            xdFile: XdFile,
            providedPackage: XdPackage,
            path: List<Int>,
            isClass: Boolean,
            isInterface: Boolean,
            isEnum: Boolean
    ): XdDeclaredType {
        return Store.transactional {
            val declaredType= XdDeclaredType.new()
            declaredType.canonicalName = className.canonicalName
            declaredType.simpleName = className.simpleName
            declaredType.nodeHashCode = nodeHashCode
            declaredType.isNestedType = isNestedType
            declaredType.file = xdFile
            declaredType.xdPackage = providedPackage
            declaredType.resolvedClassSymbol = false
            declaredType.resolvedWidgetSymbol = false
            declaredType.pathString = path.joinToString(",")
            declaredType.isClass = isClass
            declaredType.isInterface = isInterface
            declaredType.isEnum = isEnum
            declaredType
        }
    }
    operator fun get(className: ClassName): XdDeclaredType? {
        val canonicalName = className.canonicalName
        return this[canonicalName]
    }
    operator fun get(canonicalName: String): XdDeclaredType? {
        return Store.transactional {
            XdDeclaredType.query(XdDeclaredType::canonicalName eq canonicalName).firstOrNull()
        }
    }
    operator fun get(hashCode: Int): XdDeclaredType? {
        return Store.transactional {
            XdDeclaredType.query(XdDeclaredType::nodeHashCode eq hashCode).firstOrNull()
        }
    }

    fun findBySimpleName(simpleName: String): List<XdDeclaredType> {
        return Store.transactional {
            XdDeclaredType.query(XdDeclaredType::simpleName eq simpleName).toList()
        }
    }
    fun findBySimpleNameAndScope(simpleName: String, scope: String): List<XdDeclaredType> {
        return Store.transactional {
            XdDeclaredType.query(
                    XdDeclaredType::simpleName eq simpleName
            )
                    .toList()
                    .filter { xdDeclaredType -> xdDeclaredType.canonicalName.endsWith(scope) }
        }
    }
    fun findByCanonicalName(canonicalName: String): XdDeclaredType? {
        return Store.transactional {
            XdDeclaredType.query(XdDeclaredType::canonicalName eq canonicalName).firstOrNull()
        }
    }

    fun isClassTypeEligibleForIndexing(className: ClassName, nodeHash: Int, newClassCanBeWidget: Boolean): Boolean {
        // check if class already is registered, and if so if a widget has been associated to old one
        return Store.transactional {
            if (this[nodeHash] != null) {
                false
            } else {
                val existingTypeByClassName = this[className]
                val eligible = existingTypeByClassName == null
                        || existingTypeByClassName.widget == null && newClassCanBeWidget
                        || newClassCanBeWidget && (existingTypeByClassName.widget as Widget).source == XdWidgetSource.Android
                if (eligible && existingTypeByClassName != null) {
                    existingTypeByClassName.file.classes.remove(existingTypeByClassName)
                    existingTypeByClassName.xdPackage.classes.remove(existingTypeByClassName)
                    if (existingTypeByClassName.widgetAsBeingView != null) {
                        val widget = existingTypeByClassName.widgetAsBeingView as Widget
                        widget.delete()
                        existingTypeByClassName.widgetAsBeingView = null

                    }
                    if (existingTypeByClassName.widgetAsBeingLayoutParams != null) {
                        val widget = existingTypeByClassName.widgetAsBeingLayoutParams as Widget
                        widget.layoutParamClasses.remove(existingTypeByClassName)
                        existingTypeByClassName.widgetAsBeingLayoutParams = null
                    }
                    existingTypeByClassName.delete()
                }
                eligible
            }
        }
    }

    fun assignClassToWidgetAsView(xdDeclaredType: XdDeclaredType, widget: Widget) {
        Store.transactional {
            widget.viewClass = xdDeclaredType
        }
    }
    fun assignClassToWidgetAsLayoutParams(xdDeclaredType: XdDeclaredType, widget: Widget) {
        Store.transactional {
            widget.layoutParamClasses.add(xdDeclaredType)
        }
    }
    fun unassignClassFromWidget(xdDeclaredType: XdDeclaredType) {
        Store.transactional {
            val widget = Widget.query(
                    (Widget::viewClass eq xdDeclaredType)
                    or
                    (Widget::layoutParamClasses contains xdDeclaredType)
            ).firstOrNull() ?: throw IllegalArgumentException("Indexed class ${xdDeclaredType.targetClassName} with file ${xdDeclaredType.file.url} has no associated widget")
            if (widget.viewClass == xdDeclaredType) {
                widget.viewClass = null
            } else {
                widget.layoutParamClasses.remove(xdDeclaredType)
            }
            if (widget.viewClass == null && widget.layoutParamClasses.isEmpty) {
                widget.delete()
            }
        }
    }
    fun assignClassCategory(xdDeclaredType: XdDeclaredType, classCategory: ClassCategory) {
        Store.transactional {
            when (classCategory) {
                ClassCategory.View -> xdDeclaredType.classCategory = XdClassCategory.View
                ClassCategory.LayoutParams -> xdDeclaredType.classCategory = XdClassCategory.LayoutParams
            }
        }
    }
    fun assignViewGroupFlag(xdDeclaredType: XdDeclaredType, isViewGroup: Boolean) {
        Store.transactional {
            xdDeclaredType.isViewGroup = isViewGroup
        }
    }
    fun assignSuperClasses(xdDeclaredType: XdDeclaredType, superClass: XdDeclaredType, superClasses: List<XdDeclaredType>) {
        Store.transactional {
            xdDeclaredType.superClass = superClass
            xdDeclaredType.superClasses.clear()
            xdDeclaredType.superClasses.addAll(superClasses)
            xdDeclaredType.resolvedSuperClasses = true
        }
    }

    fun getSuperClasses(xdDeclaredType: XdDeclaredType): List<XdDeclaredType> {
        if (!Store.transactional { xdDeclaredType.resolvedSuperClasses }) {
            val superClasses = DeclaredSymbolResolver.resolveSuperClasses(xdDeclaredType)
            assignSuperClasses(xdDeclaredType, superClasses.first(), superClasses)
        }
        return Store.transactional {
            xdDeclaredType.superClasses.toList()
        }
    }

    fun assignResolvementStatus(xdDeclaredType: XdDeclaredType, resolvedSuperClasses: Boolean, resolvedClassSymbol: Boolean, resolvedWidgetSymbol: Boolean) {
        return Store.transactional {
            xdDeclaredType.resolvedSuperClasses = resolvedSuperClasses
            xdDeclaredType.resolvedClassSymbol = resolvedClassSymbol
            xdDeclaredType.resolvedWidgetSymbol = resolvedWidgetSymbol
        }
    }


    fun getPotentialWidgetClasses(): List<XdDeclaredType> {
        return Store.transactional {
            XdDeclaredType.query(
                    (XdDeclaredType::widgetAsBeingLayoutParams ne null) or (XdDeclaredType::widgetAsBeingView ne null)
            ).toList()
        }
    }

    fun isBodyResolved(xdDeclaredType: XdDeclaredType): Boolean {
        return Store.transactional(readonly = true) {
            xdDeclaredType.resolvedBody
        }
    }

    fun isClassSymbolResolved(xdDeclaredType: XdDeclaredType): Boolean {
        return Store.transactional(readonly = true) {
            xdDeclaredType.resolvedClassSymbol
        }
    }

    fun isClassResolvedAsWidget(xdDeclaredType: XdDeclaredType): Boolean {
        return Store.transactional(readonly = true) {
            xdDeclaredType.resolvedWidgetSymbol
        }
    }
    fun getClassSymbolDescription(xdDeclaredType: XdDeclaredType): ClassSymbolDescription? {
        return if (Store.transactional { xdDeclaredType.resolvedWidgetSymbol }) {
            getResolvedClassSymbolDescription(xdDeclaredType)
        } else {
            val resolvedClassAndSuperClasses = superClassResolver.resolve(xdDeclaredType)
            if (resolvedClassAndSuperClasses.isNotEmpty()) {
                resolvedClassAndSuperClasses.first()
            } else {
                null
            }
        }
    }
    fun getResolvedClassSymbolDescription(xdDeclaredType: XdDeclaredType): ClassSymbolDescription {
        return Store.transactional {
            if (!xdDeclaredType.resolvedClassSymbol)
                throw IllegalArgumentException("Class is not resolved")
            val classCategory = xdDeclaredType.classCategory ?: throw IllegalArgumentException("Class is flagged as resolved but has no class category")

            ClassSymbolDescription(
                    xdDeclaredType.targetClassName,
                    xdDeclaredType.widget,
                    classCategory.toEnum(),
                    xdDeclaredType.isViewGroup,
                    xdDeclaredType.superClasses.toList().map { indexedClass ->
                        indexedClass.targetClassName
                    },
                    xdDeclaredType
            )
        }
    }

    fun getHashCodeForNode(node: TypeDeclaration<*>, xdPackage: XdPackage, xdFile: XdFile): Int {
        return Store.transactional {
            getHashCodeForNode(
                    node,
                    xdPackage.packageName,
                    xdFile.urlAsString
            )
        }
    }
    fun getHashCodeForNode(node: TypeDeclaration<*>, packageName: String, filePathAsString: String): Int {
        return Objects.hash(
                node.nameAsString,
                node.begin.get().line,
                node.end.get().line,
                packageName,
                filePathAsString
        )
    }

    fun getClassInfo(xdDeclaredType: XdDeclaredType): ClassInfo? {
        if (!isClassSymbolResolved(xdDeclaredType)) {
            superClassResolver.resolveOne(xdDeclaredType)
        }
        if (isClassResolvedAsWidget(xdDeclaredType)) {
            val classDeclaration = xdDeclaredType.getClassOrInterfaceDeclaration()
            if (!isBodyResolved(xdDeclaredType)) {
                TypeBodyPeeker.peek(classDeclaration, xdDeclaredType)
            }
            return ClassInfo(xdDeclaredType, classDeclaration)
        }
        println("\tXX -> ${xdDeclaredType.targetClassName} is not an eligible class symbol. Skipping it")

        return null
    }

    fun resolveAnyNewIdentifiedWidgetClasses() {
        superClassResolver.resolve(
                DeclaredTypeRegistry.getUnresolvedPotentialWidgetClasses()
        )
    }
    private fun getUnresolvedPotentialWidgetClasses(): List<XdDeclaredType> {
        return Store.transactional {
            XdDeclaredType.query(
                    ((XdDeclaredType::widgetAsBeingLayoutParams ne  null)
                            or
                            (XdDeclaredType::widgetAsBeingView ne null))
                            and
                            (XdDeclaredType::resolvedClassSymbol eq false)
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

