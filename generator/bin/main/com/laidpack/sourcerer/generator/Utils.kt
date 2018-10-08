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

fun generateMultiFormatFiles(targetPath: Path) {
    val targetDir = targetPath.toFile()

    println("=================================")
    val file = MultiFormatGenerator().generateFile()
    file.writeTo(targetDir)

    val file2 = FormatEnumGenerator().generateFile()
    file2.writeTo(targetDir)

    //ApiGenerator(targetPath, env.apiSourcePath).writeFiles()
    println("Created multi-format class and format enum...")
    println("=================================")
}

fun generateAttributeFile(targetDir: File, result: SourcererResult) {
    val file = AttributesGenerator(
            result.targetClassName,
            result.superClassName,
            result.attributes,
            result.classCategory,
            result.isViewGroup,
            result.defaultLayoutParamsClassName
    ).generateFile()
    file.writeTo(targetDir)
}


fun generateFactoryFiles(targetDir: File, result: SourcererResult, minSdkVersion: Int) {
    if (result.classCategory == ClassCategory.View) {
        val fileSpec = ViewFactoryGenerator(
                result.targetClassName,
                result.superClassName,
                result.fallbackClassName,
                result.constructorExpression as ViewConstructorExpression,
                result.numberOfTypeVariables,
                result.codeBlocks,
                result.minimumApiLevel,
                minSdkVersion
        ).generateFile()
        fileSpec.writeTo(targetDir)
    } else {
        val fileSpec = LayoutParamsFactoryGenerator(
                result.targetClassName,
                result.superClassName,
                result.fallbackClassName,
                result.constructorExpression as LayoutParamsConstructorExpression,
                result.numberOfTypeVariables,
                result.codeBlocks,
                result.minimumApiLevel,
                minSdkVersion
        ).generateFile()
        fileSpec.writeTo(targetDir)
    }
}

fun deleteOldGeneratedFiles(env: SourcererEnvironment) {
    println("=================================")
    for (file in env.rootPath.toFile().listFiles()) {
        if (file.isDirectory && file.name.startsWith("generated")) {
            val sourceDir = file.toPath().resolve("src/main/kotlin").toFile()
            if (sourceDir.exists() && sourceDir.isDirectory) {
                for (generatedFile in sourceDir.listFiles()) {
                    file.deleteRecursively()
                }
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