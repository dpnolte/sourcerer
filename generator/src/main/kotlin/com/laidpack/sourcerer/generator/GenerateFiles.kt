package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.peeker.*
import com.laidpack.sourcerer.generator.resources.*
import com.github.javaparser.ast.Node as JavaNode


fun main(args: Array<String>) {
    val simpleName= "TextView"
    val env = SourcererEnvironment(args)
    initParserAndStore(env)

    val sourcerer = Sourcerer(env)
    sourcerer.generateFactoriesForClass(ClassRegistry.findBySimpleName(simpleName).first())

}

