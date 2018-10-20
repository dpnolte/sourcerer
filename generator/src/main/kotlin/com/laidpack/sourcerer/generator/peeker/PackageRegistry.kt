package com.laidpack.sourcerer.generator.peeker

import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
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

    fun findClassInPackage(cu: CompilationUnit, classOrInterfaceType: ClassOrInterfaceType): List<XdClass> {
        if (cu.packageDeclaration.isPresent) {
            val currentPackageName = cu.packageDeclaration.get().nameAsString
            val simpleName = classOrInterfaceType.name.identifier
            return Store.transactional {

                val indexedPackage = XdPackage.query(XdPackage::packageName eq currentPackageName).firstOrNull()
                val result = indexedPackage?.classes?.query(
                        XdClass::simpleName eq simpleName
                ) ?: return@transactional listOf()

                val resultList = result.toList()
                when {
                    classOrInterfaceType.scope.isPresent -> {
                        val scope = classOrInterfaceType.scope.get().nameAsString
                        resultList.filter {indexedClass ->
                            indexedClass.canonicalName.startsWith("$currentPackageName.$scope")
                        }
                    }
                    !classOrInterfaceType.scope.isPresent && resultList.size > 1 -> {
                        resultList.filter {indexedClass ->
                            if (!indexedClass.isNestedType) {
                                indexedClass.canonicalName == "$currentPackageName.$simpleName"
                            } else true
                        }
                    }
                    else -> resultList
                }
            }
        }
        return listOf()
    }

    fun findClassInPackage(packageName: String, simpleName: String): XdClass? {
        var result : XdClass? = null
        Store.transactional {
            val nullableIndexedPackage = XdPackage.query(XdPackage::packageName eq packageName).firstOrNull()
            nullableIndexedPackage?.let { indexedPackage ->
                result = indexedPackage.classes.query(
                        XdClass::simpleName eq simpleName
                ).firstOrNull()
            }
        }
        return result
    }

    fun findDirectlyNestedClass(
            cu: CompilationUnit,
            classDeclaration: ClassOrInterfaceDeclaration,
            classOrInterfaceType: ClassOrInterfaceType
    ): XdClass? {
        if (cu.packageDeclaration.isPresent) {
            val packageName = cu.packageDeclaration.get().nameAsString
            val className = classDeclaration.nameAsString
            val nestedClassName = classOrInterfaceType.nameAsString
            return Store.transactional {
                XdClass.query(
                        XdClass::canonicalName eq  "$packageName.$className.$nestedClassName"
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

    fun findClassInAndroidX(simpleName: String): List<XdClass> {
        if (androidXPackages.isEmpty()) {
            retrieveAndroidXPackages()
        }
        return Store.transactional {
            val matchedClasses = mutableListOf<XdClass>()
            for (androidXPackage in androidXPackages) run {
                matchedClasses.addAll(XdClass.query(
                        (XdClass::xdPackage eq androidXPackage)
                                and
                            (XdClass::simpleName eq simpleName)
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
            xdClass: XdClass,
            packageName: String,
            simpleName: String
    ): Boolean {
        val regex = Regex(".*?${Regex.escape(packageName)}.\\w+\\.${Regex.escape(simpleName)}.*?")
        return regex.matches(xdClass.targetClassName.canonicalName)
    }
}