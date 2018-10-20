package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.resources.*

fun main(args: Array<String>) {
    val env = SourcererEnvironment(args)
    Store.init(env)
    val styleableAttributeManager = StyleableAttributeManager(env)
    val widgetRegistry = WidgetRegistry.create(env)
    val formats = styleableAttributeManager.getAllPossibleFormats(widgetRegistry)

    println("Possible formats:")
    println("=================")
    formats.forEach {
        println(it)
    }
    println("=================")
}
