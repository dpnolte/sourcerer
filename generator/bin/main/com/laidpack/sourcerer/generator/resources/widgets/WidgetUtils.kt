package com.laidpack.sourcerer.generator.resources.widgets

import java.nio.file.Path

object WidgetUtils {
    fun isInExcludedPath(path: Path): Boolean {
        val pathAsString = path.toString()
        return isInExcludedPath(pathAsString)
    }
    fun isInExcludedPath(pathAsString: String): Boolean {
        return excludedDirRegex.matches(pathAsString)
    }
    private val excludedDirectoryNames = listOf(
            "androidTest",
            "testing",
            "test",
            "javatests",
            "internal",
            "samples"
    )
    private val excludedDirRegex = Regex(".*(${excludedDirectoryNames.joinToString("|"){ "/$it/"}}).*")
}