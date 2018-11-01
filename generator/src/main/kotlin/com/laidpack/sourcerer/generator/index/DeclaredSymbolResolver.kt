package com.laidpack.sourcerer.generator.index

import android.view.View
import android.view.ViewGroup
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.ImportDeclaration
import com.github.javaparser.ast.Node
import com.github.javaparser.ast.PackageDeclaration
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.TypeDeclaration
import com.github.javaparser.ast.expr.Name
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName
import com.github.javaparser.ast.type.ClassOrInterfaceType
import com.github.javaparser.resolution.UnsolvedSymbolException
import com.laidpack.sourcerer.generator.*
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asClassName
import kotlinx.dnq.query.eq
import kotlinx.dnq.query.first
import kotlinx.dnq.query.query
import java.lang.IllegalArgumentException

class DeclaredSymbolResolver(private val useCachedSuperClasses : Boolean = true) {
    // check if getClassOrInterfaceDeclaration is assignable to View or LayoutParams
    fun resolveAll(): Map<ClassName, ClassSymbolDescription> {
        return resolve(DeclaredTypeRegistry.getPotentialWidgetClasses())
    }

    fun resolve(xdDeclaredTypes: List<XdDeclaredType>): Map<ClassName, ClassSymbolDescription> {
        val resolvedClasses = mutableMapOf<ClassName, ClassSymbolDescription>()
        for(potentialWidgetClass in xdDeclaredTypes) {
            if (isClassAlreadyProcessed(potentialWidgetClass, resolvedClasses)) continue

            resolvedClasses.putAll(
                    resolve(potentialWidgetClass, resolvedClasses).associateBy {
                        it.targetClassName
                    }
            )
        }
        return resolvedClasses
    }

    fun resolveOne(xdDeclaredType: XdDeclaredType): ClassSymbolDescription? {
        return resolve(xdDeclaredType).firstOrNull()
    }

    fun resolve(
            xdDeclaredType: XdDeclaredType,
            processedClasses: Map<ClassName, ClassSymbolDescription> = mapOf()

    ): List<ClassSymbolDescription> {
        if (DeclaredTypeRegistry.isClassSymbolResolved(xdDeclaredType)) {
            val storedResolvedClass = getClassSymbolDescription(xdDeclaredType)
            if (storedResolvedClass != null) {
                return listOf(storedResolvedClass)
            }
            return listOf()
        }

        val xdSuperClasses = resolveSuperClasses(xdDeclaredType, useCachedSuperClasses)
        val resolvedClasses = mutableListOf<ClassSymbolDescription>()
        val isAssignableByView = isAssignableByView(xdDeclaredType.targetClassName, xdSuperClasses)
        if (isAssignableByView || isAssignableByLayoutParams(xdDeclaredType.targetClassName, xdSuperClasses)) {
            val classCategory = if (isAssignableByView) ClassCategory.View else ClassCategory.LayoutParams
            val isViewGroup = if (classCategory == ClassCategory.View) {
                isAssignableByViewGroup(xdDeclaredType.targetClassName, xdSuperClasses)
            } else false
            val mutableXdClassList = xdSuperClasses.toMutableList()
            mutableXdClassList.add(0, xdDeclaredType)
            while (mutableXdClassList.isNotEmpty()) {
                val selectedClass = mutableXdClassList.first()
                if (processedClasses.containsKey(selectedClass.targetClassName)) break

                mutableXdClassList.removeAt(0)
                resolvedClasses.add(ClassSymbolDescription(
                        selectedClass.targetClassName,
                        selectedClass.widget,
                        classCategory,
                        isViewGroup,
                        mutableXdClassList.map {
                            it.targetClassName
                        },
                        selectedClass
                ))
                if (mutableXdClassList.isNotEmpty()) {
                    DeclaredTypeRegistry.assignSuperClasses(selectedClass, mutableXdClassList.first(), mutableXdClassList)
                }
                DeclaredTypeRegistry.assignClassCategory(selectedClass, classCategory)
                DeclaredTypeRegistry.assignViewGroupFlag(selectedClass, isViewGroup)
                val resolvedWidgetSymbol = Store.transactional { selectedClass.widget != null }
                DeclaredTypeRegistry.assignResolvementStatus(selectedClass, true, true, resolvedWidgetSymbol)
                println("\tY -> Resolved ${selectedClass.targetClassName} as $classCategory class")
            }
        } else {
            DeclaredTypeRegistry.assignResolvementStatus(xdDeclaredType, true,true, false)
            println("\tX -> No view or lp class: ${xdDeclaredType.targetClassName}")
            DeclaredTypeRegistry.unassignClassFromWidget(xdDeclaredType)
        }
        return resolvedClasses
    }

    private fun isAssignableByView(className: ClassName, superClassNames: List<XdDeclaredType>): Boolean {
        return className == rootViewClassName || superClassNames.any {
            it.targetClassName == rootViewClassName
        }
    }
    private fun isAssignableByViewGroup(className: ClassName, superClassNames: List<XdDeclaredType>): Boolean {
        return className == rootViewGroupClassName || superClassNames.any {
            it.targetClassName == rootViewGroupClassName
        }
    }
    private fun isAssignableByLayoutParams(className: ClassName, superClassNames: List<XdDeclaredType>): Boolean {
        return className == rootLayoutParamsClassName || superClassNames.any {
            it.targetClassName == rootLayoutParamsClassName
        }
    }

    private fun isClassAlreadyProcessed(xdDeclaredType: XdDeclaredType, classSymbolDescriptions: MutableMap<ClassName, ClassSymbolDescription>): Boolean {
        if (classSymbolDescriptions.containsKey(xdDeclaredType.targetClassName)) {
            return true
        }
        val storedResolvedClass = getClassSymbolDescription(xdDeclaredType)
        return if (storedResolvedClass != null) {
            classSymbolDescriptions[storedResolvedClass.targetClassName] = storedResolvedClass
            true
        } else {
            false
        }
    }

    private fun getClassSymbolDescription(xdDeclaredType: XdDeclaredType): ClassSymbolDescription? {
        if (DeclaredTypeRegistry.isClassSymbolResolved(xdDeclaredType)) {
            return if (DeclaredTypeRegistry.isClassResolvedAsWidget(xdDeclaredType)) {
                val resolvedClass = DeclaredTypeRegistry.getResolvedClassSymbolDescription(xdDeclaredType)
                println("\tCY -> Resolved ${resolvedClass.targetClassName} as ${resolvedClass.classCategory} class")
                resolvedClass
            } else {
                println("\tCX -> No view or lp class: ${xdDeclaredType.targetClassName}")
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

        fun resolveSuperClasses(
                xdDeclaredType: XdDeclaredType,
                useCachedSuperClasses: Boolean = true
        ): List<XdDeclaredType> {
            val superClasses = mutableListOf<XdDeclaredType>()
            try {
                val node = xdDeclaredType.getClassOrInterfaceDeclaration()
                walk(
                        node,
                        superClasses,
                        xdDeclaredType.targetClassName,
                        useCachedSuperClasses,
                        canLookUpSuperClasses = false
                )

                /* it seems that allSuperClasses can be quirky at times.. for now, use our own walker fun
                val superClassTypes = resolvedNode.asClass().allSuperClasses
                // sublist to remove Java object
                superClassTypes.subList(0, superClassTypes.lastIndex).map {
                    ClassName.bestGuess(
                            it.canonicalName
                    )
                }*/
            } catch(e: UnsolvedSymbolException) {
                println("!X -> Could not resolve symbol for ${xdDeclaredType.targetClassName} -> Original message: ${e.message}")
                //listOf()
            }
            return superClasses
        }


        private fun walk (
                node: ClassOrInterfaceDeclaration,
                superClasses: MutableList<XdDeclaredType>,
                originalTargetClass: ClassName,
                useCachedSuperClasses: Boolean,
                canLookUpSuperClasses: Boolean
        ) {
            if (node.extendedTypes.isNonEmpty) {
                val superClassType = node.extendedTypes.first()
                try {
                    val superClass = resolveDeclaredType(superClassType, canLookUpSuperClasses)
                    superClasses.add(superClass)
                    if (useCachedSuperClasses && DeclaredTypeRegistry.isClassSymbolResolved(superClass)) {
                        superClasses.addAll(
                                DeclaredTypeRegistry.getSuperClasses(superClass)
                        )
                    } else if (!rootClassNames.contains(superClass.targetClassName)) {
                        walk(
                                superClass.getClassOrInterfaceDeclaration(),
                                superClasses,
                                originalTargetClass,
                                useCachedSuperClasses,
                                canLookUpSuperClasses
                        )
                    }
                } catch (e: UnsolvedSymbolException) {
                    println("!\tX -> Could not resolve super class symbol for ${node.nameAsString}, initial target class: $originalTargetClass, -> Original message: ${e.message}")
                }

            }
        }

        // what should be the logic?
        // find all classes with simple name...
        // if only one result, that is the class..
        // if multiple results, it is either:
        // --- in same package
        // --- imported
        // --- class declared in super class or parent class

        // Note: enums also have nodeWithSimpleName as type
        fun resolveDeclaredType(
                nodeWithSimpleName: NodeWithSimpleName<*>,
                canLookUpSuperClasses: Boolean = true
        ): XdDeclaredType {
            // is full name specified?
            DeclaredTypeRegistry.findByCanonicalName(nodeWithSimpleName.toString())?.let {
                return transformLayoutLibMockView(it)
            }
            // otherwise resolve simple name
            val simpleName = nodeWithSimpleName.name.identifier
            val xdDeclaredTypes = if (nodeWithSimpleName is ClassOrInterfaceType
                    && nodeWithSimpleName.scope.isPresent
            ) {
                val xdDeclaredTypeWithScope = DeclaredTypeRegistry.findBySimpleNameAndScope(simpleName, nodeWithSimpleName.scope.get().nameAsString)
                if (xdDeclaredTypeWithScope.isNotEmpty()) {
                    xdDeclaredTypeWithScope
                } else DeclaredTypeRegistry.findBySimpleName(simpleName)
            } else DeclaredTypeRegistry.findBySimpleName(simpleName)
            val xdDeclaredType = when (xdDeclaredTypes.size) {
                0 -> throw IllegalArgumentException("No declared type indexed with simple name $simpleName")
                1 -> xdDeclaredTypes.first()
                else -> resolveSharedSimpleName(
                        nodeWithSimpleName,
                        xdDeclaredTypes,
                        canLookUpSuperClasses
                )
            }
            return transformLayoutLibMockView(xdDeclaredType)
        }

        private fun resolveSharedSimpleName(
                nodeWithSimpleName: NodeWithSimpleName<*>,
                xdDeclaredTypes: List<XdDeclaredType>,
                canLookUpSuperClasses: Boolean
        ): XdDeclaredType {
            val cu = (nodeWithSimpleName as Node).findCompilationUnit().get()
            findTypeDeclaredInSuperOrParent(cu, nodeWithSimpleName, xdDeclaredTypes, canLookUpSuperClasses)?.let {
                return it
            }
            findAnyImportedType(cu, nodeWithSimpleName, xdDeclaredTypes, canLookUpSuperClasses)?.let {
                return it
            }
            findAnyTypeInSamePackage(cu, nodeWithSimpleName)?.let {
                return it
            }
            throw UnsolvedSymbolException("!\tX -> Could not resolve declared type symbol '${nodeWithSimpleName.nameAsString}' with shared name")
        }


        private fun findAnyTypeInSamePackage(cu: CompilationUnit, classOrInterfaceType: NodeWithSimpleName<*>): XdDeclaredType? {
            val matches = PackageRegistry.findDeclaredTypeInPackage(cu, classOrInterfaceType)
            return when (matches.size) {
                0 -> null
                1 -> Store.transactional { matches.first() }
                else -> null
            }
        }

        private fun findAnyImportedType(
                cu: CompilationUnit,
                classOrInterfaceType: NodeWithSimpleName<*>,
                xdDeclaredTypes: List<XdDeclaredType>,
                canLookUpSuperClasses: Boolean
        ): XdDeclaredType? {
            val selectedPart = if (classOrInterfaceType is ClassOrInterfaceType && classOrInterfaceType.scope.isPresent) {
                classOrInterfaceType.scope.get().nameAsString
            } else classOrInterfaceType.nameAsString
            val names = selectedPart.split(".")

            for (name in names) {
                // check if it is a named import
                cu.imports.find { importDeclaration ->
                    !importDeclaration.isAsterisk && importDeclaration.name.identifier == name
                }?.let { importDeclaration ->
                    var canonicalName = jetifyTransformName(importDeclaration.name)
                    if (classOrInterfaceType is ClassOrInterfaceType && classOrInterfaceType.scope.isPresent) {
                        canonicalName += ".${classOrInterfaceType.name.identifier}"
                    }
                    val matchingClass = Store.transactional {
                        xdDeclaredTypes.find { indexedClass ->
                            indexedClass.canonicalName == canonicalName
                        }
                    }
                    if (matchingClass != null) {
                        return matchingClass
                    }
                    // check if class is defined as nested class in ancestor of imported class
                    if (canLookUpSuperClasses && classOrInterfaceType is ClassOrInterfaceType && classOrInterfaceType.scope.isPresent) {
                        val nestedClassInImportedSuperClass = Store.transactional {
                            val xdImportedClass = XdDeclaredType.query(
                                    XdDeclaredType::canonicalName eq importDeclaration.nameAsString
                            ).first()
                            for (xdSuperClass in DeclaredTypeRegistry.getSuperClasses(xdImportedClass)) {
                                return@transactional xdDeclaredTypes.find { xdFoundClass ->
                                    xdFoundClass.canonicalName == "${xdSuperClass.canonicalName}.${classOrInterfaceType.name.identifier}"
                                } ?: continue
                            }
                            return@transactional null
                        }
                        if (nestedClassInImportedSuperClass != null) {
                            return nestedClassInImportedSuperClass
                        }
                    }
                }
                // check if it is imported via asterisk
                for (import in cu.imports) {
                    if (!import.isAsterisk) continue

                    val packageName = jetifyTransformName(import.name).replace(".*", "")
                    PackageRegistry.findDeclaredTypeInPackage(packageName, name)?.let {
                        return it
                    }
                }
            }

            return null
        }

        private fun findTypeDeclaredInSuperOrParent(
                cu: CompilationUnit,
                nodeWithSimpleName: NodeWithSimpleName<*>,
                xdDeclaredTypes: List<XdDeclaredType>,
                canLookUpSuperClasses: Boolean
        ): XdDeclaredType? {
            val node = nodeWithSimpleName as Node
            val classDeclarationContainingType =
                    node.firstAncestorOfType(ClassOrInterfaceDeclaration::class.java) as ClassOrInterfaceDeclaration

            // check if the class type is declared as nested class in class containing type
            val packageName = cu.packageDeclaration.get().nameAsString
            val className = classDeclarationContainingType.nameAsString
            val nestedClassName = nodeWithSimpleName.nameAsString
            val directNestedClassDeclaration = Store.transactional {
                xdDeclaredTypes.find { xdFoundClass ->
                    xdFoundClass.canonicalName == "$packageName.$className.$nestedClassName"
                }
            }
            if (directNestedClassDeclaration != null) {
                return directNestedClassDeclaration
            }

            // check if the class type is another nested type declared in current cu
            if (classDeclarationContainingType.isNestedType) {
                val parentClassDeclaration = classDeclarationContainingType.firstAncestorOfType(ClassOrInterfaceDeclaration::class.java) as ClassOrInterfaceDeclaration
                val xdParentClass = getIndexedClassFromDeclaration(cu, parentClassDeclaration)
                val nestedClassDeclaredInAncestor = Store.transactional {
                    for (xdParentsSuperClass in DeclaredTypeRegistry.getSuperClasses(xdParentClass)) {
                        return@transactional xdDeclaredTypes.find { xdFoundClass ->
                            xdFoundClass.canonicalName == "${xdParentsSuperClass.canonicalName}.$nestedClassName"
                        } ?: continue
                    }
                    return@transactional null
                }
                if (nestedClassDeclaredInAncestor != null) {
                    return nestedClassDeclaredInAncestor
                }
            }

            // check if class type is defined as nested class in the parent's super classes
            // so many opportunities in our world :-)
            if (canLookUpSuperClasses) {
                val xdClassThatContainsType = getIndexedClassFromDeclaration(cu, classDeclarationContainingType)
                for (xdSuperClass in DeclaredTypeRegistry.getSuperClasses(xdClassThatContainsType)) {
                    val nestedClassDeclaredInSuperClass = Store.transactional {
                        xdDeclaredTypes.find { xdFoundClass ->
                            xdFoundClass.canonicalName == "${xdSuperClass.canonicalName}.$nestedClassName"
                        }
                    }
                    if (nestedClassDeclaredInSuperClass != null) {
                        return nestedClassDeclaredInSuperClass
                    }
                }
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

        private fun transformLayoutLibMockView(xdDeclaredType: XdDeclaredType): XdDeclaredType {
            return if (xdDeclaredType.targetClassName.canonicalName == mockViewCanonicalName) {
                DeclaredTypeRegistry[rootViewClassName] as XdDeclaredType
            } else xdDeclaredType
        }

        fun determineClassName(node: TypeDeclaration<*>, packageDeclaration: PackageDeclaration): ClassName {
            val packageName = packageDeclaration.nameAsString
            return if (node.isNestedType) {
                val parents = node.ancestorsOfTypeUntil(
                        ClassOrInterfaceDeclaration::class.java, packageDeclaration
                ).asReversed()
                when {
                    parents.isEmpty() -> //throw IllegalStateException("Parent classes cannot be empty for nested class ${getClassOrInterfaceDeclaration.nameAsString} in $xdPackage")
                        ClassName(packageName, node.nameAsString)
                    parents.size == 1 -> {
                        ClassName(packageName, parents.first().nameAsString, node.nameAsString)
                    }
                    else -> {
                        ClassName(
                                packageName,
                                parents.first().nameAsString,
                                *parents.subList(1, parents.size).map { it.nameAsString }.toTypedArray(),
                                node.nameAsString
                        )
                    }
                }
            } else ClassName(packageName, node.nameAsString)
        }

        private fun getIndexedClassFromDeclaration(
                cu: CompilationUnit,
                classDeclaration: ClassOrInterfaceDeclaration
        ): XdDeclaredType {
            val targetClassName = DeclaredSymbolResolver.determineClassName(classDeclaration, cu.packageDeclaration.get())
            return DeclaredTypeRegistry[targetClassName] as XdDeclaredType
        }

        fun getCanonicalNameFromImport(importDeclaration: ImportDeclaration): String {
            return jetifyTransformName(importDeclaration.name)
        }

    }
}

