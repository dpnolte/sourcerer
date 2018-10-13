@file:Suppress("SENSELESS_COMPARISON")

package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.peeker.*
import com.laidpack.sourcerer.generator.resources.*
import com.laidpack.sourcerer.generator.target.XdAttribute
import kotlinx.dnq.query.inEntities
import kotlinx.dnq.query.query
import kotlinx.dnq.query.toList
import com.github.javaparser.ast.Node as JavaNode


fun main(args: Array<String>) {
    //val input = "CoordinatorLayout" // simple name
    val input = "androidx.recyclerview.widget.RecyclerView" // canonical name
    SourcererEnvironment.printFlowInterpreterTrace = true
    val env = SourcererEnvironment(args)
    initParserAndStore(env)

    val widgetRegistry = getWidgetRegistry(env)
    val sourcerer = Sourcerer(env)

    val indexedClasses = when {
        input.contains(".") ->  {
            val indexedClass = ClassRegistry[input]
            if (indexedClass != null) listOf(indexedClass) else listOf()
        }
        else -> ClassRegistry.findBySimpleName(input)
    }
    var anyDependingDerivedClasses = listOf<IndexedClass>()
    when {
        indexedClasses.size > 1 -> {
            val classNames = Store.transactional {
                indexedClasses.joinToString("\n") { "- ${it.canonicalName}" }
            }
            throw IllegalArgumentException("Multiple classes with (simple) name '$input' indexed.\nClasses:\n$classNames")
        }
        indexedClasses.size == 1 -> {
            val indexedClass = indexedClasses.first()

            val classesInFile = FileRegistry.getClassesInFile(indexedClass)
            for (classInFile in classesInFile) {
                println(classInFile.entityId.toString() + ": " + classInFile.targetClassName)
            }
            // if any attributes used by a derived class, remove cached index from derived class as well
            Store.transactional {
                indexedClass.sourcererResult?.let { xdResult ->
                    val xdAttributesOfThisClass = xdResult.attributes.toList()
                    anyDependingDerivedClasses = XdAttribute.query(
                            XdAttribute::attributeDeclaredInSuperClass.inEntities(xdAttributesOfThisClass)
                    )
                    .toList()
                    .map { it.sourcererResult.targetClass }
                }
            }
            // remove class index to force re-index
            ClassRegistry.assignResolvementStatus(indexedClass, false, false)
            FileRegistry.deleteFileIndexByClass(indexedClass)
            anyDependingDerivedClasses.forEach { derivedClass ->
                ClassRegistry.assignResolvementStatus(derivedClass, false, false)
                FileRegistry.deleteFileIndexByClass(derivedClass)

            }
            //Store.deleteSourcererResult(simpleName)
        }
        else -> throw java.lang.IllegalArgumentException("No class found with (simple) name '$input'")
    }

    ClassIndexer.scan(widgetRegistry, reindexAll = false)
    val targetClass = when {
        input.contains(".") -> {
            ClassRegistry[input] ?: throw IllegalStateException("Class '$input' not found")
        }
        else -> ClassRegistry.findBySimpleName(input).first()
    }
    sourcerer.generateFactoriesForClass(targetClass)

    // process any dependent classes
    if (anyDependingDerivedClasses.isNotEmpty()) {
        SourcererEnvironment.printFlowInterpreterTrace = false
        println("=======================================")
        println("=======================================")
        println("=======================================")
        println("Processing derived depending classes")
        anyDependingDerivedClasses.forEach {
            sourcerer.generateFactoriesForClass(it)
        }
    }
}

