package com.laidpack.sourcerer.generator.peeker


import com.github.javaparser.JavaParser
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.XdSourcererResult
import com.laidpack.sourcerer.generator.resources.*
import com.laidpack.sourcerer.generator.target.XdAttribute
import com.squareup.kotlinpoet.ClassName
import kotlinx.dnq.query.*
import java.lang.IllegalArgumentException
import java.util.*

class ClassRegistry(
        private val attrsXmlManager: StyleableAttributeManager,
        private val resolvedClasses: MutableMap<ClassName, SymbolResolvedClass> = mutableMapOf(),
        private val classes: MutableMap<ClassName, ClassInfo> = mutableMapOf(),
        private val classSymbolResolver: ClassSymbolResolver = ClassSymbolResolver()
) {
    private val peeker : SourcePeeker = SourcePeeker(attrsXmlManager)

    fun resolveAnyNewIdentifiedWidgetClasses() {
        classSymbolResolver.resolve(
                ClassRegistry.getUnresolvedPotentialWidgetClasses()
        )
    }

    operator fun get(className: ClassName): ClassInfo? {
        if (!classes.containsKey(className)) {
            val classInfo = getClassInfo(className)
            if (classInfo != null) {
                classes[className] = classInfo
            }
            return classInfo
        }
        return classes[className]
    }
    operator fun get(indexedClass: IndexedClass): ClassInfo? {
        if (!classes.containsKey(indexedClass.targetClassName)) {
            val classInfo = getClassInfo(indexedClass)
            if (classInfo != null) {
                classes[indexedClass.targetClassName] = classInfo
            }
            return classInfo
        }
        return classes[indexedClass.targetClassName]
    }


    private fun getClassInfo(className: ClassName): ClassInfo? {
        val indexedClass = Store.transactional {
            IndexedClass.query(
                    IndexedClass::canonicalName eq className.canonicalName
            ).firstOrNull()
        } ?: throw IllegalArgumentException("No class indexed with $className")
        return getClassInfo(indexedClass)
    }
    private fun getClassInfo(indexedClass: IndexedClass): ClassInfo? {
        val resolvedClass = getResolvedClass(indexedClass)
        if (resolvedClass == null) {
            println("\tXX -> ${indexedClass.targetClassName} is not an eligible class symbol. Skipping it")
            return null
        }
        val classInfo = peeker.peek(resolvedClass)
        if (classInfo == null) {
            println("\tXX -> ${resolvedClass.targetClassName} is not an eligible widget class. Skipping it")
            return null
        }

        if (attrsXmlManager.hasAttributesDefined(classInfo.targetClassName, classInfo.widget)) {
            println("\tYY -> Added class body with specified attributes '${classInfo.targetClassName}'")
            classInfo.specifiedAttributes.putAll(attrsXmlManager.getAttributesFromXml(classInfo))
        } else {
            println("\tYX -> Added class body without any attributes '${classInfo.targetClassName}'")
        }
        return classInfo
    }

    private fun getResolvedClass(indexedClass: IndexedClass): SymbolResolvedClass? {
        return if (resolvedClasses.containsKey(indexedClass.targetClassName)) {
            resolvedClasses[indexedClass.targetClassName] as SymbolResolvedClass
        } else {
            val resolvedClassAndSuperClasses = classSymbolResolver.resolve(indexedClass, resolvedClasses)
            if (resolvedClassAndSuperClasses.isNotEmpty()) {
                resolvedClasses.putAll(
                        resolvedClassAndSuperClasses.associateBy {
                            it.targetClassName
                        }
                )
                resolvedClassAndSuperClasses.first()
            } else {
                null
            }
        }
    }

    fun getSuperClasses(classInfo: ClassInfo): List<ClassInfo> {
        val availableSuperClasses = mutableListOf<ClassInfo>()
        for (superClassType in classInfo.superClassNames) {
            val superClassInfo = this[superClassType]
            if (superClassInfo != null) {
                availableSuperClasses.add(superClassInfo)
            }
        }
        return availableSuperClasses
    }

    fun getClassAndSuperClasses(classInfo: ClassInfo): List<ClassInfo> {
        val classes = mutableListOf<ClassInfo>()
        classes.add(classInfo)
        classes.addAll(getSuperClasses(classInfo))
        return classes
    }

    companion object {
        fun add(className: ClassName, nodeHashCode: Int, indexedFile: IndexedFile, providedPackage: IndexedPackage): IndexedClass {
            return Store.transactional {
                val indexedClass= IndexedClass.new()
                indexedClass.canonicalName = className.canonicalName
                indexedClass.simpleName = className.simpleName
                indexedClass.nodeHashCode = nodeHashCode
                indexedClass.file = indexedFile
                indexedClass.indexedPackage = providedPackage
                indexedClass.resolvedClassSymbol = false
                indexedClass.resolvedWidgetSymbol = false
                indexedClass
            }
        }
        operator fun get(className: ClassName): IndexedClass? {
            val canonicalName = className.canonicalName
            return this[canonicalName]
        }
        operator fun get(canonicalName: String): IndexedClass? {
            return Store.transactional {
                IndexedClass.query(IndexedClass::canonicalName eq canonicalName).firstOrNull()
            }
        }
        operator fun get(hashCode: Int): IndexedClass? {
            return Store.transactional {
                IndexedClass.query(IndexedClass::nodeHashCode eq hashCode).firstOrNull()
            }
        }

        fun findBySimpleName(simpleName: String): List<IndexedClass> {
            return Store.transactional {
                IndexedClass.query(IndexedClass::simpleName eq simpleName).toList()
            }
        }
        fun findByCanonicalName(canonicalName: String): IndexedClass? {
            return Store.transactional {
                IndexedClass.query(IndexedClass::canonicalName eq canonicalName).firstOrNull()
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
                        existingClass.indexedPackage.classes.remove(existingClass)
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

        fun assignWidgetAsBeingViewClass(indexedClass: IndexedClass, widget: Widget) {
            Store.transactional {
                indexedClass.widgetAsBeingView = widget
                widget.viewClass = indexedClass
            }
        }
        fun assignWidgetAsBeingLayoutParamsClass(indexedClass: IndexedClass, widget: Widget) {
            Store.transactional {
                indexedClass.widgetAsBeingLayoutParams = widget
                widget.layoutParamClasses.add(indexedClass)
            }
        }
        fun assignClassCategory(indexedClass: IndexedClass, classCategory: ClassCategory) {
            Store.transactional {
                when (classCategory) {
                    ClassCategory.View -> indexedClass.classCategory = XdClassCategory.View
                    ClassCategory.LayoutParams -> indexedClass.classCategory = XdClassCategory.LayoutParams
                }
            }
        }
        fun assignSuperClasses(indexedClass: IndexedClass, superClasses: List<IndexedClass>) {
            Store.transactional {
                indexedClass.superClasses.clear()
                indexedClass.superClasses.addAll(superClasses)
            }
        }

        fun getSuperClasses(indexedClass: IndexedClass): List<IndexedClass> {
            return Store.transactional {
                indexedClass.superClasses.toList()
            }
        }

        fun assignResolvementStatus(indexedClass: IndexedClass, resolvedClassSymbol: Boolean, resolvedWidgetSymbol: Boolean) {
            return Store.transactional {
                indexedClass.resolvedClassSymbol = resolvedClassSymbol
                indexedClass.resolvedWidgetSymbol = resolvedWidgetSymbol
            }
        }


        fun getPotentialWidgetClasses(): List<IndexedClass> {
            return Store.transactional {
                IndexedClass.query(
                        (IndexedClass::widgetAsBeingLayoutParams ne null) or (IndexedClass::widgetAsBeingView ne null)
                ).toList()
            }
        }

        fun isClassSymbolResolved(indexedClass: IndexedClass): Boolean {
            return Store.transactional {
                indexedClass.resolvedClassSymbol
            }
        }

        fun isClassResolvedAsWidget(indexedClass: IndexedClass): Boolean {
            return Store.transactional {
                indexedClass.resolvedWidgetSymbol
            }
        }

        fun getClassAsResolvedSymbol(indexedClass: IndexedClass): SymbolResolvedClass {
            return Store.transactional {
                if (!indexedClass.resolvedWidgetSymbol) throw IllegalArgumentException("Class is not resolved")
                val classCategory = indexedClass.classCategory ?: throw IllegalArgumentException("Class is flagged as resolved but has no class category")

                SymbolResolvedClass(
                        indexedClass.targetClassName,
                        indexedClass.widget,
                        classCategory.toEnum(),
                        {indexedClass.node},
                        indexedClass.superClasses.toList().map { indexedClass ->
                            indexedClass.targetClassName
                        },
                        indexedClass
                )
            }
        }

        fun getHashCodeForNode(classDeclaration: ClassOrInterfaceDeclaration, indexedPackage: IndexedPackage, indexedFile: IndexedFile): Int {
            return Store.transactional {
                getHashCodeForNode(
                        classDeclaration,
                        indexedPackage.packageName,
                        indexedFile.urlAsString
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

        fun getUnresolvedPotentialWidgetClasses(): List<IndexedClass> {
            return Store.transactional {
                IndexedClass.query(
                        ((IndexedClass::widgetAsBeingLayoutParams ne  null)
                                or
                                (IndexedClass::widgetAsBeingView ne null))
                                and
                                (IndexedClass::resolvedClassSymbol eq false)
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

}

