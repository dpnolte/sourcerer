package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.peeker.IndexedClass
import com.laidpack.sourcerer.generator.peeker.IndexedPackage
import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import kotlinx.dnq.query.*


fun main(args: Array<String>) {
    val env = SourcererEnvironment(args)
    Store.init(env)
    val processedLibs = mutableSetOf<String>()
    Store.transactional {
        val androidXPackages = IndexedPackage.query(
                (IndexedPackage::packageName startsWith "androidx")
        ).toList()
        for (androidXPackage in androidXPackages) run {
            val splitName = androidXPackage.packageName.split(".")
            val name = splitName[0] + "." + splitName[1]
            if (!processedLibs.contains(name)) {
                val indexedClass = IndexedClass.query(
                        (IndexedClass::indexedPackage eq androidXPackage)
                        and
                        (
                                (IndexedClass::widgetAsBeingView ne null)
                                or
                                (IndexedClass::widgetAsBeingLayoutParams ne null)
                        )
                ).firstOrNull()
                if (indexedClass != null) {
                    processedLibs.add(name)

                }
            }
        }
    }
    println("=================")
    for (lib in processedLibs) {
        println(lib)
    }
    println("=================")
}