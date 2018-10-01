package com.laidpack.sourcerer.generator.resources

import com.laidpack.sourcerer.generator.TypePhilosopher
import java.io.File
import java.net.URI
import java.nio.file.FileSystems
import java.nio.file.Path
import java.nio.file.Paths
import java.util.zip.ZipFile
import jdk.nashorn.internal.objects.NativeArray.forEach
import java.nio.file.Files
import java.util.Collections.emptyMap




class SourcererEnvironment(args: Array<String>) {
    val rootPath : Path = Paths.get(args[0])
    val compileSdkVersion = args[1].toInt()
    val sdkDirString = args[2]
    val libPathsAsStrings = args[3].substring(1, args[3].length - 1)
            .split(",")
            .map { it.trim() }
    val supportLibPathsAsStrings = libPathsAsStrings.filter { it.endsWith(".aar") && it.contains("/androidx.") }
    val supportLibPaths = supportLibPathsAsStrings.map { Paths.get(it) }
    val materialDesignLibPathAsString = libPathsAsStrings.first { it.contains("/com.google.android.material/") }
    val materialDesignLibPath = Paths.get(materialDesignLibPathAsString)

    val sourceArtifactPathsAsStrings = args[4].substring(1, args[4].length - 1)
            .split(",")
            .map { it.trim() }
    val supportSourceArtifactPathsAsStrings = sourceArtifactPathsAsStrings.filter { it.contains("/androidx.") }
    val supportSourceArtifactPaths = supportSourceArtifactPathsAsStrings.map { Paths.get(it) }
    val supportSourceRootDirPaths = supportSourceArtifactPaths

    val sdkStructure = AndroidSdkStructure.get(compileSdkVersion, sdkDirString)

    val generatedPath : Path = rootPath.resolve("generated/src/main/kotlin")
    val generatedDir : File = generatedPath.toFile()
    val generatedPackageDir = generatedPath.resolve(
            TypePhilosopher.generatedPackageName.replace(".", File.separator)
    ).toFile()
    val stubAppPath = rootPath.resolve("stub-app/src/main/kotlin")
    val stubAppDir = stubAppPath.toFile()
    val stubAppGeneratedPackageDir = stubAppPath.resolve(
            TypePhilosopher.generatedPackageName.replace(".", File.separator)
    ).toFile()
    val lintReportsFilePath : Path = rootPath.resolve("stub-app/build/reports/lint-results.xml")
    val lintReportsFile : File = lintReportsFilePath.toFile()

    val generatorPath = rootPath.resolve("generator")
    val apiSourcePath = generatorPath.resolve("src/main/kotlin/com/laidpack/sourcerer/generator/generators/api")

    val materialDesignPath = rootPath.resolve("source-material-components")
    val materialDesignSourcePath = materialDesignPath.resolve("lib/java")
    val supportPath = rootPath.resolve("source-support")
    //val supportSourcePath = supportPath

    val serializedPath : Path = generatorPath.resolve("serialized")
    val serializedDir : File = serializedPath.toFile()
    val indexedPath : Path = generatorPath.resolve("indexed")
    val indexedDir = indexedPath.toFile()

}
