package com.laidpack.sourcerer.generator.index

import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName
import com.github.javaparser.ast.type.ClassOrInterfaceType
import com.laidpack.sourcerer.generator.Store
import kotlinx.dnq.query.*


object PackageRegistry {
    fun findOrCreate(packageName: String): XdPackage {
        return Store.transactional {
            XdPackage.query(XdPackage::packageName eq packageName).firstOrNull() ?:
                    XdPackage.new {
                       this.packageName = packageName
                    }
        }
    }

    fun findDeclaredTypeInPackage(cu: CompilationUnit, nodeWithSimpleName: NodeWithSimpleName<*>): List<XdDeclaredType> {
        if (cu.packageDeclaration.isPresent) {
            val currentPackageName = cu.packageDeclaration.get().nameAsString
            val simpleName = nodeWithSimpleName.name.identifier
            return Store.transactional {

                val indexedPackage = XdPackage.query(XdPackage::packageName eq currentPackageName).firstOrNull()
                val result = indexedPackage?.classes?.query(
                        XdDeclaredType::simpleName eq simpleName
                ) ?: return@transactional listOf()

                val resultList = result.toList()
                when {
                    nodeWithSimpleName is ClassOrInterfaceType && nodeWithSimpleName.scope.isPresent -> {
                        val scope = nodeWithSimpleName.scope.get().nameAsString
                        resultList.filter {indexedClass ->
                            indexedClass.canonicalName.startsWith("$currentPackageName.$scope")
                        }
                    }
                    (nodeWithSimpleName !is ClassOrInterfaceType || !nodeWithSimpleName.scope.isPresent) && resultList.size > 1 -> {
                        resultList.filter {indexedClass ->
                            if (!indexedClass.isNestedType) {
                                indexedClass.canonicalName == "$currentPackageName.$simpleName"
                            } else false
                        }
                    }
                    else -> resultList
                }
            }
        }
        return listOf()
    }

    fun findDeclaredTypeInPackage(packageName: String, simpleName: String): XdDeclaredType? {
        var result : XdDeclaredType? = null
        Store.transactional {
            val nullableIndexedPackage = XdPackage.query(XdPackage::packageName eq packageName).firstOrNull()
            nullableIndexedPackage?.let { indexedPackage ->
                result = indexedPackage.classes.query(
                        XdDeclaredType::simpleName eq simpleName
                ).firstOrNull()
            }
        }
        return result
    }

    fun findDirectlyNestedClass(
            cu: CompilationUnit,
            classDeclaration: ClassOrInterfaceDeclaration,
            classOrInterfaceType: ClassOrInterfaceType
    ): XdDeclaredType? {
        if (cu.packageDeclaration.isPresent) {
            val packageName = cu.packageDeclaration.get().nameAsString
            val className = classDeclaration.nameAsString
            val nestedClassName = classOrInterfaceType.nameAsString
            return Store.transactional {
                XdDeclaredType.query(
                        XdDeclaredType::canonicalName eq  "$packageName.$className.$nestedClassName"
                ).firstOrNull()
            }
        }
        return null
    }



    private val androidXPackages = mutableListOf<XdPackage>()
    private fun retrieveAndroidXPackages() {
        androidXPackages.clear()
        Store.transactional {
            androidXPackages.addAll(XdPackage.query(
                XdPackage::packageName startsWith "androidx"
            ).toList())
        }
    }

    fun findClassInAndroidX(simpleName: String): List<XdDeclaredType> {
        if (androidXPackages.isEmpty()) {
            retrieveAndroidXPackages()
        }
        return Store.transactional {
            val matchedClasses = mutableListOf<XdDeclaredType>()
            for (androidXPackage in androidXPackages) run {
                matchedClasses.addAll(XdDeclaredType.query(
                        (XdDeclaredType::xdPackage eq androidXPackage)
                                and
                            (XdDeclaredType::simpleName eq simpleName)
                ).toList())
            }
            if (matchedClasses.size > 1) {
                matchedClasses.filter { !it.canonicalName.contains("legacy") }
            } else matchedClasses
        }
    }

    operator fun get(packageName: String): XdPackage? {
        return Store.transactional {
            XdPackage.query(XdPackage::packageName eq packageName).firstOrNull()
        }
    }

    private fun isDirectlyNested(
            xdDeclaredType: XdDeclaredType,
            packageName: String,
            simpleName: String
    ): Boolean {
        val regex = Regex(".*?${Regex.escape(packageName)}.\\w+\\.${Regex.escape(simpleName)}.*?")
        return regex.matches(xdDeclaredType.targetClassName.canonicalName)
    }
}