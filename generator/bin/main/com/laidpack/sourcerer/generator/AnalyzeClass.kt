@file:Suppress("SENSELESS_COMPARISON")

package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.peeker.*
import com.laidpack.sourcerer.generator.resources.*
import com.github.javaparser.ast.Node as JavaNode


fun main(args: Array<String>) {
    val input = "ViewStub" // canonical name or simple name like 'TextView'
    //val input = "androidx.media.widget.VideoView2"
    SourcererEnvironment.printSourceTreeVisitOutput = true
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
    when {
        indexedClasses.size > 1 -> {
            val classNames = Store.transactional {
                indexedClasses.joinToString("\n") { "- ${it.canonicalName}" }
            }
            throw IllegalArgumentException("Multiple classes with simple name '$input' indexed.\nClasses:\n$classNames")
        }
        indexedClasses.size == 1 -> {
            val indexedClass = indexedClasses.first()

            val classesInFile = FileRegistry.getClassesInFile(indexedClass)
            for (classInFile in classesInFile) {
                println(classInFile.entityId.toString() + ": " + classInFile.targetClassName)
            }
            // remove class index to force re-index
            ClassRegistry.assignResolvementStatus(indexedClass, false, false)
            FileRegistry.deleteFileIndexByClass(indexedClass)
            //Store.deleteSourcererResult(simpleName)
        }
    }

    ClassIndexer.scan(widgetRegistry, reindexAll = false)
    val targetClass = when {
        input.contains(".") -> {
            ClassRegistry[input] ?: throw IllegalStateException("Class '$input' not found")
        }
        else -> ClassRegistry.findBySimpleName(input).first()
    }
    sourcerer.generateFactoriesForClass(targetClass)

}

