package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.peeker.ClassIndexer
import com.laidpack.sourcerer.generator.peeker.ClassRegistry
import com.laidpack.sourcerer.generator.resources.*
import com.github.javaparser.ast.Node as JavaNode


fun main(args: Array<String>) {
    val env = SourcererEnvironment(args)
    initParserAndStore(env)

    val widgetRegistry = getWidgetRegistry(env)
    ClassIndexer.scan(widgetRegistry, reindexAll = false)

    val classRegistry = ClassRegistry()
    classRegistry.resolveAnyNewIdentifiedWidgetClasses()

    generateMultiFormatFiles(env.servicePath)
    deleteTempFiles(env)

    val sourcerer = Sourcerer(env, classRegistry)
    sourcerer.generateFactoriesForAllWidgets()


}

