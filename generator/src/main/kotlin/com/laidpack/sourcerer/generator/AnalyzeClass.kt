package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.peeker.*
import com.laidpack.sourcerer.generator.resources.*
import com.github.javaparser.ast.Node as JavaNode


fun main(args: Array<String>) {
    val simpleName= "TextView"

    val env = SourcererEnvironment(args)
    initParserAndStore(env)

    //Store.deleteSourcererResult("ViewStubCompat")

    val widgetRegistry = getWidgetRegistry(env)
    val attrsXmlManager = StyleableAttributeManager()

    val classRegistry = ClassRegistry(attrsXmlManager, classSymbolResolver = ClassSymbolResolver(false))
    val sourcerer = Sourcerer(classRegistry, env)

    val indexedClasses = ClassRegistry.findBySimpleName(simpleName)
    when {
        indexedClasses.size > 1 -> throw IllegalArgumentException("Multiple classes with simple name '$simpleName' indexed")
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
    sourcerer.generateFactoriesForClass(ClassRegistry.findBySimpleName(simpleName).first())

}

