package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.resources.SourcererEnvironment


fun main(args: Array<String>) {
    println("Values in args: " + args.joinToString())
    val env = SourcererEnvironment(args)
    println("=================")
    println("Working Directory: ${env.rootPath}")
    println("Compile sdk version: ${env.compileSdkVersion}")
    println("Sdk dir: ${env.sdkDirString}")
    println("Lib paths: ${env.libPathsAsStrings}" )
    println("AndroidX support lib paths: ${env.supportLibPathsAsStrings}")
    println("Material design lib paths: ${env.materialDesignLibPathAsString}")
    println("Source paths: ${env.sourceArtifactPathsAsStrings}" )
    println("AndroidX support source paths: ${env.supportSourceArtifactPathsAsStrings}")
    println("Material design source paths: ${env.materialDesignSourcePath}")
    println("=================")

}