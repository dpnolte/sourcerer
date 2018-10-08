package com.laidpack.sourcerer.generator.generators

import com.laidpack.sourcerer.generator.TypePhilosopher
import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import java.io.File
import java.lang.IllegalStateException
import java.nio.file.Path

// simply copies files

class ApiGenerator(targetSourcePath: Path, sourcePath: Path) {
    private val targetPath = targetSourcePath.resolve(packagePathString)
    private val sourceDir = sourcePath.toFile()

    fun writeFiles() {
        for (sourceFile in sourceDir.listFiles()) {
            val content = sourceFile.readText()
            val matchResult = packageRegex.find(content)
                    ?: throw IllegalStateException("Package not found in file $sourceFile")
            val updatedContent =
                    content.replace(matchResult.groupValues[0], "package $packageName")
            val targetFile = targetPath.resolve(sourceFile.name).toFile()
            targetFile.writeText(updatedContent)
        }
    }

    companion object {
        private val packageRegex = Regex("package\\s+(.+\\..+)")
        private const val packageName = SourcererEnvironment.generatedPackageName
        private val packagePathString = packageName.replace(".", File.separator)
    }
}