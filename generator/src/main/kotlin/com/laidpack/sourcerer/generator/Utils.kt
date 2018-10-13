package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.generators.*
import com.laidpack.sourcerer.generator.peeker.ClassCategory
import com.laidpack.sourcerer.generator.peeker.ClassRegistry
import com.laidpack.sourcerer.generator.peeker.LayoutParamsConstructorExpression
import com.laidpack.sourcerer.generator.peeker.ViewConstructorExpression
import com.laidpack.sourcerer.generator.resources.AndroidSdkStructure
import com.laidpack.sourcerer.generator.resources.GradleModuleManager
import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import java.io.File
import java.nio.file.Path

fun initParserAndStore(env: SourcererEnvironment) {
    JavaParserContext.init(env)
    Store.init(env)
}


fun String.toCamelCase(): String {
    return this.split('_').joinToString("") {
        it.capitalize() }
}