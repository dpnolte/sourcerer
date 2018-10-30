package com.laidpack.sourcerer.generator.resources

import java.io.File
import java.nio.file.Path
import java.nio.file.Paths


class SourcererEnvironment(args: Array<String>) {
    val rootPath : Path = Paths.get(args[0])
    val compileSdkVersion = args[1].toInt()
    val sdkDirString = args[2]
    val libPathsAsStrings = args[3].substring(1, args[3].length - 1)
            .split(",")
            .map { it.trim() }
    val sourceArtifactPathsAsStrings = args[4].substring(1, args[4].length - 1)
            .split(",")
            .map { it.trim() }
    val minSdkVersion = args[5].toInt()

    val supportLibPathsAsStrings = libPathsAsStrings.filter { it.endsWith(".aar") && it.contains("/androidx.") }
    val supportLibPaths = supportLibPathsAsStrings.map { Paths.get(it) }
    val materialDesignLibPathAsString = libPathsAsStrings.first { it.contains("/com.google.android.material/") }
    val materialDesignLibPath = Paths.get(materialDesignLibPathAsString)


    val supportSourceArtifactPathsAsStrings = sourceArtifactPathsAsStrings.filter { it.contains("/androidx.") }
    val supportSourceArtifactPaths = supportSourceArtifactPathsAsStrings.map { Paths.get(it) }
    val supportSourceRootDirPaths = supportSourceArtifactPaths

    val sdkStructure = AndroidSdkStructure.get(compileSdkVersion, sdkDirString)

    val generatedPath : Path = rootPath.resolve("generated/src/main/kotlin")
    val generatedDir : File = generatedPath.toFile()
    val generatedPackageDir = generatedPath.resolve(
            generatedPackageName.replace(".", File.separator)
    ).toFile()
    val stubModuleProjectDir = rootPath.resolve("stub-module").toFile()
    val stubModulePath = rootPath.resolve("stub-module/src/main/kotlin")
    val stubModuleDir = stubModulePath.toFile()
    val stubModuleGeneratedPackageDir = stubModulePath.resolve(
            generatedPackageName.replace(".", File.separator)
    ).toFile()
    val lintReportsFilePath : Path = rootPath.resolve("stub-app/build/reports/lint-results.xml")
    val lintReportsFile : File = lintReportsFilePath.toFile()

    val generatorPath = rootPath.resolve("generator")
    val materialDesignPath = rootPath.resolve("source-material-components")
    val materialDesignSourcePath = materialDesignPath.resolve("lib/java")

    val indexedPath : Path = generatorPath.resolve("indexed")
    val indexedDir = indexedPath.toFile()

    val servicesPath = rootPath.resolve("services/src/main/kotlin")
    val servicesDir = servicesPath.toFile()

    val androidResourceManager = AndroidResourceManager(this)

    companion object {
        var printFlowInterpreterTrace = false
        const val rootPackageName = "com.laidpack.sourcerer"
        const val servicePackageName =  "$rootPackageName.services"
        const val servicesApiPackageName = "$servicePackageName.api"
        const val servicesAdaptersPackageName = "$servicePackageName.adapters"
        const val generatedPackageName = "$rootPackageName.generated"
        val generatedPackagePathAsString = generatedPackageName.replace(".", File.separator)
    }

}
