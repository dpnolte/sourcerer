package com.laidpack.sourcerer.generator.peeker

import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.type.ClassOrInterfaceType
import com.laidpack.sourcerer.generator.Store
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*
import kotlinx.dnq.query.*


class IndexedPackage(entity: Entity) : XdEntity(entity) {
    companion object : XdNaturalEntityType<IndexedPackage>()
    var packageName by xdRequiredStringProp(unique = true, trimmed = true)
    val classes  by xdLink0_N(IndexedClass::indexedPackage)
}

object PackageRegistry {
    fun findOrCreate(packageName: String): IndexedPackage {
        return Store.transactional {
            IndexedPackage.query(IndexedPackage::packageName eq packageName).firstOrNull() ?:
                    IndexedPackage.new {
                       this.packageName = packageName
                    }
        }
    }

    fun findClassInPackage(cu: CompilationUnit, classOrInterfaceType: ClassOrInterfaceType): List<IndexedClass> {
        if (cu.packageDeclaration.isPresent) {
            val currentPackageName = cu.packageDeclaration.get().nameAsString
            val simpleName = classOrInterfaceType.name.identifier
            return Store.transactional {

                val indexedPackage = IndexedPackage.query(IndexedPackage::packageName eq currentPackageName).firstOrNull()
                var result = indexedPackage?.classes?.query(
                        IndexedClass::simpleName eq simpleName
                )

                if (result != null && classOrInterfaceType.scope.isPresent) {
                    val scope = classOrInterfaceType.scope.get().nameAsString
                    result = result.query(
                            IndexedClass::canonicalName startsWith "$currentPackageName.$scope"
                    )
                }

                result?.toList() ?: listOf()
            }
        }
        return listOf()
    }

    fun findClassInPackage(packageName: String, simpleName: String): IndexedClass? {
        var result : IndexedClass? = null
        Store.transactional {
            val nullableIndexedPackage = IndexedPackage.query(IndexedPackage::packageName eq packageName).firstOrNull()
            nullableIndexedPackage?.let { indexedPackage ->
                result = indexedPackage.classes.query(
                        IndexedClass::simpleName eq simpleName
                ).firstOrNull()
            }
        }
        return result
    }



    private val androidXPackages = mutableListOf<IndexedPackage>()
    private fun retrieveAndroidXPackages() {
        androidXPackages.clear()
        Store.transactional {
            androidXPackages.addAll(IndexedPackage.query(
                    IndexedPackage::packageName startsWith "androidx"
            ).toList())
        }
    }

    fun findClassInAndroidX(simpleName: String): List<IndexedClass> {
        if (androidXPackages.isEmpty()) {
            retrieveAndroidXPackages()
        }
        return Store.transactional {
            val matchedClasses = mutableListOf<IndexedClass>()
            for (androidXPackage in androidXPackages) run {
                matchedClasses.addAll(IndexedClass.query(
                        (IndexedClass::indexedPackage eq androidXPackage)
                                and
                            (IndexedClass::simpleName eq simpleName)
                ).toList())
            }
            matchedClasses
        }
    }

    operator fun get(packageName: String): IndexedPackage? {
        return Store.transactional {
            IndexedPackage.query(IndexedPackage::packageName eq packageName).firstOrNull()
        }
    }
}