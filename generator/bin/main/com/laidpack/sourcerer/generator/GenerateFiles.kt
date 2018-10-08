package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.peeker.*
import com.laidpack.sourcerer.generator.resources.*
import com.github.javaparser.ast.Node as JavaNode


fun main(args: Array<String>) {
    val simpleName= "TextView"
    val env = SourcererEnvironment(args)
    initParserAndStore(env)

    val attrsXmlManager = StyleableAttributeManager()
    val classRegistry = ClassRegistry(attrsXmlManager, classSymbolResolver = ClassSymbolResolver(true))
    val sourcerer = Sourcerer(env, classRegistry)

    sourcerer.generateFactoriesForClass(ClassRegistry.findBySimpleName(simpleName).first())

}

