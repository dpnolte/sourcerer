package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.resources.AndroidSdkStructure
import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import com.laidpack.sourcerer.generator.resources.StyleableAttributeManager
import com.laidpack.sourcerer.generator.resources.getWidgetRegistry

fun main(args: Array<String>) {
    val env = SourcererEnvironment(args)
    Store.init(env)
    val styleableAttributeManager = StyleableAttributeManager()
    val widgetRegistry = getWidgetRegistry(env)
    val formats = styleableAttributeManager.getAllPossibleFormats(widgetRegistry)

    println("Possible formats:")
    println("=================")
    formats.forEach {
        println(it)
    }
    println("=================")
}
