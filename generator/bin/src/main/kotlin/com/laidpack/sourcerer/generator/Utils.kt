package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.generators.*
import com.laidpack.sourcerer.generator.peeker.ClassCategory
import com.laidpack.sourcerer.generator.peeker.ClassRegistry
import com.laidpack.sourcerer.generator.peeker.LayoutParamsConstructorExpression
import com.laidpack.sourcerer.generator.peeker.ViewConstructorExpression
import com.laidpack.sourcerer.generator.resources.AndroidSdkStructure
import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import java.io.File
import java.nio.file.Path

fun initParserAndStore(env: SourcererEnvironment) {
    setUpJavaParser(env)
    Store.init(env)
}

fun generateHelperFiles(targetPath: Path, env: SourcererEnvironment) {
    val targetDir = targetPath.toFile()

    println("=================================")
    val file = MultiFormatGenerator().generateFile()
    file.writeTo(targetDir)

    val file2 = FormatEnumGenerator().generateFile()
    file2.writeTo(targetDir)

    ApiGenerator(targetPath, env.apiSourcePath).writeFiles()
    println("Created helper classes...")
    println("=================================")
}

fun generateAttributeFile(targetDir: File, result: SourcererResult) {
    val file = AttributesGenerator(
            result.targetClassName,
            result.superClassName,
            result.attributes,
            result.classCategory
    ).generateFile()
    file.writeTo(targetDir)
}


fun generateFactoryFiles(targetDir: File, result: SourcererResult) {
    if (result.classCategory == ClassCategory.View) {
        val fileSpec = ViewFactoryGenerator(
                result.targetClassName,
                result.superClassName,
                result.constructorExpression as ViewConstructorExpression,
                result.defaultLayoutParamsClassName,
                result.numberOfTypeVariables,
                result.codeBlocks
        ).generateFile()
        fileSpec.writeTo(targetDir)
    } else {
        val fileSpec = LayoutParamsFactoryGenerator(
                result.targetClassName,
                result.superClassName,
                result.constructorExpression as LayoutParamsConstructorExpression,
                result.numberOfTypeVariables,
                result.codeBlocks
        ).generateFile()
        fileSpec.writeTo(targetDir)
    }
}

fun deleteOldGeneratedFiles(env: SourcererEnvironment) {
    println("=================================")
    for (file in env.rootPath.toFile().listFiles()) {
        if (file.isDirectory && file.name.startsWith("generated")) {
            val sourcePath = file.toPath().resolve("src/main/kotlin")
            for (generatedFile in sourcePath.toFile().listFiles()) {
                println(generatedFile)
                //file.deleteRecursively()
            }
        }
    }
    println("Deleted old generated files")
    println("=================================")
}

fun deleteTempFiles(env: SourcererEnvironment) {
    println("=================================")
    for (file in env.stubAppGeneratedPackageDir.listFiles()) {
        file.deleteRecursively()
    }
    println("Deleted temp classes in stub app @ ${env.stubAppGeneratedPackageDir}")
    println("=================================")
}