package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.flow.BaseNodeHandler
import com.laidpack.sourcerer.generator.generators.ProjectGeneratorManager
import com.laidpack.sourcerer.generator.index.TypeIndexer
import com.laidpack.sourcerer.generator.index.DeclaredTypeRegistry
import com.laidpack.sourcerer.generator.resources.*
import java.io.BufferedReader
import java.io.InputStreamReader
import com.github.javaparser.ast.Node as JavaNode

fun main(args: Array<String>) {
    val env = SourcererEnvironment(args)
    initParserAndStore(env)

    val widgetRegistry = WidgetRegistry.create(env)
    TypeIndexer.scan(widgetRegistry, reindexAll = false)
    DeclaredTypeRegistry.resolveAnyNewIdentifiedWidgetClasses()

    ProjectGeneratorManager.generateMultiFormatFiles(env.servicesDir)
    ProjectGeneratorManager.deleteTempFiles(env)

    val sourcerer = Sourcerer(env, widgetRegistry = widgetRegistry)
    Store.deleteSourcererResults()
    sourcerer.generateFactoriesForAllWidgets()
    BaseNodeHandler.printExecutionTimes()
}

