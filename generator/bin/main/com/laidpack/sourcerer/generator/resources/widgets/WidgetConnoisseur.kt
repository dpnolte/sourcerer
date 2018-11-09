package com.laidpack.sourcerer.generator.resources.widgets

import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import com.laidpack.sourcerer.generator.resources.Widget
import java.net.URL
import java.nio.file.Path

interface WidgetConnoisseur {
    val priority: Int
    val paths : Set<Path>
    val sourceProvider: () -> XdWidgetSource
    fun setScanPaths(env: SourcererEnvironment)
    fun isValidWidgetPath(path: Path): Boolean
    fun getAttributesFileUrl(filePath: Path, env: SourcererEnvironment): URL
    fun getModuleName(widget: Widget): String
    fun getDependencies(widget: Widget): List<String>
    fun isTemplateWithinScope (moduleName: String, templateName: String): Boolean

}