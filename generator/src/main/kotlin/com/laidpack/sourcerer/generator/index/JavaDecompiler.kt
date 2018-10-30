package com.laidpack.sourcerer.generator.index

import com.laidpack.sourcerer.generator.resources.AndroidResourceManager
import jd.commonide.IdeDecompiler
import jd.commonide.preferences.IdePreferences
import java.nio.file.Path

object JavaDecompiler {
    private const val showDefaultConstructor = true
    private const val realignmentLineNumber = false
    private const val showPrefixThis = true
    private const val mergeEmptyLines = true
    private const val unicodeEscape = true
    private const val showLineNumbers = false
    private const val showMetadata = false

    fun decompileClass(jarPath: String, classPathInSideJar: String): String {
        val preferences = IdePreferences(
                showDefaultConstructor,
                realignmentLineNumber,
                showPrefixThis,
                mergeEmptyLines,
                unicodeEscape,
                showLineNumbers,
                showMetadata
        )
        return IdeDecompiler.decompile(
                preferences,
                jarPath,
                classPathInSideJar
        )
    }
}