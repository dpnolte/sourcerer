package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.flow.BaseNodeHandler
import com.laidpack.sourcerer.generator.generators.ProjectGeneratorManager
import com.laidpack.sourcerer.generator.peeker.ClassIndexer
import com.laidpack.sourcerer.generator.peeker.ClassRegistry
import com.laidpack.sourcerer.generator.resources.*
import com.github.javaparser.ast.Node as JavaNode


fun main(args: Array<String>) {
    val env = SourcererEnvironment(args)
    initParserAndStore(env)

    val widgetRegistry = WidgetRegistry.create(env)
    ClassIndexer.scan(widgetRegistry, reindexAll = false)
    ClassRegistry.resolveAnyNewIdentifiedWidgetClasses()

    ProjectGeneratorManager.generateMultiFormatFiles(env.servicesDir)
    ProjectGeneratorManager.deleteTempFiles(env)

    val sourcerer = Sourcerer(env, widgetRegistry = widgetRegistry)
    sourcerer.generateFactoriesForAllWidgets()
    BaseNodeHandler.printExecutionTimes()

}

