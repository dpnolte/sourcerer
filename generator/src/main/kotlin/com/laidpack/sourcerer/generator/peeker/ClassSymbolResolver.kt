package com.laidpack.sourcerer.generator.peeker

import android.view.View
import android.view.ViewGroup
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.PackageDeclaration
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.expr.Name
import com.github.javaparser.ast.type.ClassOrInterfaceType
import com.github.javaparser.resolution.UnsolvedSymbolException
import com.laidpack.sourcerer.generator.*
import com.laidpack.sourcerer.generator.resources.Widget
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asClassName
import java.lang.IllegalArgumentException

data class SymbolResolvedClass (
        val targetClassName: ClassName,
        val widget: Widget?,
        val classCategory : ClassCategory,
        val isViewGroup: Boolean,
        val classDeclarationProvider: () -> ClassOrInterfaceDeclaration,
        val superClassNames: List<ClassName>,
        val indexedClass: IndexedClass
)
class ClassSymbolResolver(private val useCachedSuperClasses : Boolean = true) {
    // check if node is assignable to View or LayoutParams
    fun resolveAll(): Map<ClassName, SymbolResolvedClass> {
        return resolve(ClassRegistry.getPotentialWidgetClasses())
    }

    fun resolve(indexedClasses: List<IndexedClass>): Map<ClassName, SymbolResolvedClass> {
        val resolvedClasses = mutableMapOf<ClassName, SymbolResolvedClass>()
        for(potentialWidgetClass in indexedClasses) {
            if (isClassAlreadyProcessed(potentialWidgetClass, resolvedClasses)) continue

            resolvedClasses.putAll(
                    resolve(potentialWidgetClass, resolvedClasses).associateBy {
                        it.targetClassName
                    }
            )
        }
        return resolvedClasses
    }

    fun resolveOne(indexedClass: IndexedClass): SymbolResolvedClass? {
        return resolve(indexedClass).firstOrNull()
    }

    fun resolve(indexedClass: IndexedClass, processedClasses : Map<ClassName, SymbolResolvedClass> = mapOf()): List<SymbolResolvedClass> {
        if (ClassRegistry.isClassSymbolResolved(indexedClass)) {
            val storedResolvedClass = getStoredResolvedWidgetClass(indexedClass)
            if (storedResolvedClass != null) {
                return listOf(storedResolvedClass)
            }
            return listOf()
        }

        val superClassNames = resolveSuperClasses(indexedClass)
        val resolvedClasses = mutableListOf<SymbolResolvedClass>()
        val resolved: Boolean
        val isAssignableByView = isAssignableByView(indexedClass.targetClassName, superClassNames)
        if (isAssignableByView || isAssignableByLayoutParams(indexedClass.targetClassName, superClassNames)) {
            val classCategory = if (isAssignableByView) ClassCategory.View else ClassCategory.LayoutParams
            val isViewGroup = if (classCategory == ClassCategory.View) {
                isAssignableByViewGroup(indexedClass.targetClassName, superClassNames)
            } else false
            resolved = true
            val mutableClassList = superClassNames.toMutableList()
            mutableClassList.add(0, indexedClass)
            while (mutableClassList.isNotEmpty()) {
                val selectedClass = mutableClassList.first()
                if (processedClasses.containsKey(selectedClass.targetClassName)) break

                mutableClassList.removeAt(0)
                resolvedClasses.add(SymbolResolvedClass(
                        selectedClass.targetClassName,
                        selectedClass.widget,
                        classCategory,
                        isViewGroup,
                        {selectedClass.node},
                        mutableClassList.map {
                            it.targetClassName
                        },
                        selectedClass
                ))
                ClassRegistry.assignSuperClasses(selectedClass, mutableClassList)
                ClassRegistry.assignClassCategory(selectedClass, classCategory)
                ClassRegistry.assignViewGroupFlag(indexedClass, isViewGroup)
                println("\tY -> Resolved ${selectedClass.targetClassName} as $classCategory class")
            }
        } else {
            resolved = false
            println("\tX -> No view or lp class: ${indexedClass.targetClassName}")
            ClassRegistry.unassignClassFromWidget(indexedClass)
        }
        ClassRegistry.assignResolvementStatus(indexedClass, true, resolved)
        return resolvedClasses
    }

    private fun resolveSuperClasses(indexedClass: IndexedClass): List<IndexedClass> {
        val superClasses = mutableListOf<IndexedClass>()
        try {
            val node = indexedClass.node
            walk(node, superClasses, indexedClass.targetClassName)

            /* it seems that allSuperClasses can be quirky at times.. for now, use our own walker fun
            val superClassTypes = resolvedNode.asClass().allSuperClasses
            // sublist to remove Java object
            superClassTypes.subList(0, superClassTypes.lastIndex).map {
                ClassName.bestGuess(
                        it.canonicalName
                )
            }*/
        } catch(e: UnsolvedSymbolException) {
            println("!X -> Could not resolve symbol for ${indexedClass.targetClassName} -> Original message: ${e.message}")
            //listOf()
        }
        return superClasses
    }


    private fun walk (node: ClassOrInterfaceDeclaration, superClasses: MutableList<IndexedClass>, originalTargetClass: ClassName) {
        if (node.extendedTypes.isNonEmpty) {
            val superClassType = node.extendedTypes.first()
            try {
                val superClass = resolveClassOrInterfaceType(superClassType)
                superClasses.add(superClass)
                if (useCachedSuperClasses && ClassRegistry.isClassSymbolResolved(superClass)) {
                    superClasses.addAll(
                            ClassRegistry.getSuperClasses(superClass)
                    )
                } else if (!rootClassNames.contains(superClass.targetClassName)) {
                    walk(superClass.node, superClasses, originalTargetClass)
                }
            } catch (e: UnsolvedSymbolException) {
                println("!\tX -> Could not resolve super class symbol for ${node.nameAsString}, initial target class: $originalTargetClass, -> Original message: ${e.message}")
            }

        }
    }

    private fun isAssignableByView(className: ClassName, superClassNames: List<IndexedClass>): Boolean {
        return className == rootViewClassName || superClassNames.any {
            it.targetClassName == rootViewClassName
        }
    }
    private fun isAssignableByViewGroup(className: ClassName, superClassNames: List<IndexedClass>): Boolean {
        return className == rootViewGroupClassName || superClassNames.any {
            it.targetClassName == rootViewGroupClassName
        }
    }
    private fun isAssignableByLayoutParams(className: ClassName, superClassNames: List<IndexedClass>): Boolean {
        return className == rootLayoutParamsClassName || superClassNames.any {
            it.targetClassName == rootLayoutParamsClassName
        }
    }

    private fun isClassAlreadyProcessed(indexedClass: IndexedClass, resolvedClasses: MutableMap<ClassName, SymbolResolvedClass>): Boolean {
        if (resolvedClasses.containsKey(indexedClass.targetClassName)) {
            return true
        }
        val storedResolvedClass = getStoredResolvedWidgetClass(indexedClass)
        return if (storedResolvedClass != null) {
            resolvedClasses[storedResolvedClass.targetClassName] = storedResolvedClass
            true
        } else {
            false
        }
    }

    private fun getStoredResolvedWidgetClass(indexedClass: IndexedClass): SymbolResolvedClass? {
        if (ClassRegistry.isClassSymbolResolved(indexedClass)) {
            return if (ClassRegistry.isClassResolvedAsWidget(indexedClass)) {
                val resolvedClass = ClassRegistry.getClassAsResolvedSymbol(indexedClass)
                println("\tCY -> Resolved ${resolvedClass.targetClassName} as ${resolvedClass.classCategory} class")
                resolvedClass
            } else {
                println("\tCX -> No view or lp class: ${indexedClass.targetClassName}")
                null
            }
        }
        return null
    }

    companion object {
        private val rootViewClassName = View::class.asClassName()
        private val rootViewGroupClassName = ViewGroup::class.asClassName()
        private val rootLayoutParamsClassName = ViewGroup.LayoutParams::class.asClassName()
        private val rootClassNames = setOf(rootViewClassName, rootLayoutParamsClassName)
        private const val mockViewCanonicalName = "com.android.layoutlib.bridge.MockView"
        private val oldDesignSupportPackageNames = setOf(
                "android.support.design",
                "android.support.v4",
                "android.support.v7"
        )

        // what should be the logic?
        // find all classes with simple name...
        // if only one result, that is the class..
        // if multiple results, it is either:
        // --- in same package
        // --- imported
        // --- nested class declared in parent's super class

        fun resolveClassOrInterfaceType(classOrInterfaceType: ClassOrInterfaceType): IndexedClass {
            // is full name specified?
            ClassRegistry.findByCanonicalName(classOrInterfaceType.toString())?.let {
                return transformLayoutLibMockView(it)
            }
            // otherwise resolve simple name
            val simpleName = classOrInterfaceType.name.identifier
            val indexedClasses = ClassRegistry.findBySimpleName(simpleName)
            val indexedClass = when (indexedClasses.size) {
                0 -> throw IllegalArgumentException("No class indexed with simple name $simpleName")
                1 -> indexedClasses.first()
                else -> resolveSharedSimpleName(classOrInterfaceType, indexedClasses)
            }
            return transformLayoutLibMockView(indexedClass)
        }

        private fun resolveSharedSimpleName(classOrInterfaceType: ClassOrInterfaceType, indexedClasses: List<IndexedClass>): IndexedClass {
            val cu = classOrInterfaceType.findRootNode() as CompilationUnit
            findAnyNestedClassDeclaredInSuperOrParent(cu, classOrInterfaceType)?.let {
                return it
            }
            findAnyImportedClass(cu, classOrInterfaceType, indexedClasses)?.let {
                return it
            }
            findAnyClassInSamePackage(cu, classOrInterfaceType)?.let {
                return it
            }
            throw UnsolvedSymbolException("!\tX -> Could not resolve super class symbol '${classOrInterfaceType.nameAsString}' with shared name")
        }


        private fun findAnyClassInSamePackage(cu: CompilationUnit, classOrInterfaceType: ClassOrInterfaceType): IndexedClass? {
            /*val name = if (classOrInterfaceType.scope.isPresent) {
                classOrInterfaceType.scope.get().nameAsString + "." + classOrInterfaceType.nameAsString
            } else classOrInterfaceType.nameAsString*/
            val matches = PackageRegistry.findClassInPackage(cu, classOrInterfaceType)
            return when (matches.size) {
                0 -> null
                1 -> Store.transactional { matches.first() }
                else -> null
            }
        }

        private fun findAnyImportedClass(
                cu: CompilationUnit,
                classOrInterfaceType: ClassOrInterfaceType,
                indexedClasses: List<IndexedClass>
        ): IndexedClass? {
            val selectedPart = if (classOrInterfaceType.scope.isPresent) {
                classOrInterfaceType.scope.get().nameAsString
            } else classOrInterfaceType.nameAsString
            val names = selectedPart.split(".")

            for (name in names) {
                // check if it is a named import
                cu.imports.find { importDeclaration ->
                    !importDeclaration.isAsterisk && importDeclaration.name.identifier == name
                }?.let { importDeclaration ->
                    var canonicalName = jetifyTransformName(importDeclaration.name)
                    if (classOrInterfaceType.scope.isPresent) {
                        canonicalName += ".${classOrInterfaceType.name.identifier}"
                    }
                    val matchingClass = Store.transactional {
                        indexedClasses.find { indexedClass ->
                            indexedClass.canonicalName == canonicalName
                        }
                    }
                    if (matchingClass != null) {
                        return matchingClass
                    }
                }
                // check if it is imported via asterisk
                for (import in cu.imports) {
                    if (!import.isAsterisk) continue

                    val packageName = jetifyTransformName(import.name).replace(".*", "")
                    PackageRegistry.findClassInPackage(packageName, name)?.let {
                        return it
                    }
                }
            }

            return null
        }

        private fun findAnyNestedClassDeclaredInSuperOrParent(
                cu: CompilationUnit,
                classOrInterfaceType: ClassOrInterfaceType
        ): IndexedClass? {
            val classDeclaration =
                    classOrInterfaceType.firstAncestorOfType(ClassOrInterfaceDeclaration::class.java) as ClassOrInterfaceDeclaration
            // check if the class type is declared as nested class in current target class
            val directNestedIndexedClass= PackageRegistry.findDirectlyNestedClass(
                    cu,
                    classDeclaration,
                    classOrInterfaceType
            )
            if (directNestedIndexedClass != null) {
                return getIndexedClassFromDeclaration(cu, directNestedIndexedClass.node)
            }

            if (classDeclaration.isNestedType) {
                val parentClassDeclaration = classDeclaration.firstAncestorOfType(ClassOrInterfaceDeclaration::class.java) as ClassOrInterfaceDeclaration
                // since we're indexing depth first.. the subclasses of parent should be known (TBC)
                val parentIndexedClass = getIndexedClassFromDeclaration(cu, parentClassDeclaration)
                for (superClass in ClassRegistry.getSuperClasses(parentIndexedClass)) {
                    val nestedClassesDeclaredInAncestor = superClass.node.descendantsOfType(ClassOrInterfaceDeclaration::class.java)
                    for (nestedClassDeclaration in nestedClassesDeclaredInAncestor) {
                        if (nestedClassDeclaration.name.identifier == classOrInterfaceType.name.identifier) {
                            val ancestorCu = nestedClassDeclaration.findRootNode() as CompilationUnit
                            return getIndexedClassFromDeclaration(ancestorCu, nestedClassDeclaration)
                        }
                    }
                }
            }
            // this class is not nested..
            // but perhaps the class is defined as nested class in the parent's super classes
            // so many opportunities in our world :-)
            val indexClass = getIndexedClassFromDeclaration(cu, classDeclaration)
            for (superClass in ClassRegistry.getSuperClasses(indexClass)) {
                val superClassDeclaration = superClass.node
                val targetClassDeclaration = superClassDeclaration.firstDescendantOfType(
                        ClassOrInterfaceDeclaration::class.java
                ) { c -> c.name.identifier == classOrInterfaceType.name.identifier }
                    ?: continue
                return getIndexedClassFromDeclaration(
                        targetClassDeclaration.findRootNode() as CompilationUnit,
                        targetClassDeclaration
                )
            }

            return null
        }

        private fun jetifyTransformName(name: Name): String {
            val qualifier: String
            val identifier : String
            if (name.qualifier.isPresent) {
                qualifier = name.qualifier.get().asString()
                identifier = name.identifier
            } else {
                val splitName = name.asString().split(".")
                identifier = splitName.last()
                qualifier = splitName.subList(0, splitName.lastIndex).joinToString(".")
            }
            val importFromOldDesignPackage = oldDesignSupportPackageNames.find { qualifier.startsWith(it) }
            return if (importFromOldDesignPackage != null) {
                val matchingClasses = PackageRegistry.findClassInAndroidX(name.identifier)
                return when (matchingClasses.size) {
                    1 -> Store.transactional { matchingClasses.first().canonicalName }
                    else -> {
                        val cropIndex = importFromOldDesignPackage.length + 1 // + 1 is for dot
                        val suffix = if (qualifier.length > cropIndex) "." + qualifier.substring(cropIndex) else ""
                        "androidx.${identifier.toLowerCase()}$suffix.$identifier"
                    }
                }
            } else {
                name.asString()
            }
        }

        private fun transformLayoutLibMockView(indexedClass: IndexedClass): IndexedClass {
            return if (indexedClass.targetClassName.canonicalName == mockViewCanonicalName) {
                ClassRegistry[rootViewClassName] as IndexedClass
            } else indexedClass
        }

        fun determineClassName(node: ClassOrInterfaceDeclaration, packageDeclaration: PackageDeclaration): ClassName? {
            val packageName = packageDeclaration.nameAsString
            return if (node.isNestedType) {
                val parents = node.ancestorsOfTypeUntil(
                        ClassOrInterfaceDeclaration::class.java, packageDeclaration
                ).asReversed()
                if (parents.isEmpty()) {
                    //throw IllegalStateException("Parent classes cannot be empty for nested class ${node.nameAsString} in $indexedPackage")
                    ClassName(packageName, node.nameAsString)
                } else {
                    try {
                        ClassName.bestGuess(
                                "$packageName.${parents.joinToString(".") { it.nameAsString }}.${node.nameAsString}"
                        )
                    } catch(e: IllegalArgumentException) {
                        null
                    }
                }
            } else ClassName(packageName, node.nameAsString)
        }

        private fun getIndexedClassFromDeclaration(
                cu: CompilationUnit,
                classDeclaration: ClassOrInterfaceDeclaration
        ): IndexedClass {
            val targetClassName = ClassSymbolResolver.determineClassName(classDeclaration, cu.packageDeclaration.get())
            val result = if (targetClassName != null) {
                ClassRegistry[targetClassName]
            } else {
                PackageRegistry.findClassInPackage(
                        cu.packageDeclaration.get().nameAsString,
                        classDeclaration.name.identifier
                )
            }
            return result as IndexedClass
        }

    }


}