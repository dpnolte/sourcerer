package com.laidpack.sourcerer.generator.resources.widgets

import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.resources.*
import java.net.URL
import java.nio.file.Path

class MaterialDesignWidgetConnoisseur : WidgetConnoisseur {
    override val priority: Int = 2
    override val sourceProvider = {
        Store.transactional {
            XdWidgetSource.MaterialDesign
        }
    }
    override lateinit var paths: Set<Path>
    override fun setScanPaths(env: SourcererEnvironment) {
        paths = setOf(env.materialDesignSourcePath)
    }
    override fun getAttributesFileUrl(filePath: Path, env: SourcererEnvironment): URL {
        var componentPath = filePath.parent
        while(componentPath.getName(componentPath.nameCount - 2).toString() != lastPackageDirName && componentPath != paths) {
            componentPath = componentPath.parent
        }
        return componentPath.resolve("res/values/attrs.xml").toUri().toURL()
    }
    override fun isValidWidgetPath(path: Path): Boolean {
        val pathAsString = path.toString()
        return !WidgetUtils.isInExcludedPath(pathAsString) && !pathAsString.endsWith("CircularRevealGridLayout.java")
        // CircularRevealGridLayout is not jetified via transform?
    }

    override fun getModuleName(widget: Widget): String {
        return "generated-material-components"
    }

    override fun getDependencies(widget: Widget): List<String> {
        return listOf(
                "com.google.android.material:material:\$materialDesign",
                "project(\":services\")",
                "project(\":generated\")",
                "project(\":generated-appcompat\")",
                "project(\":generated-cardview\")",
                "project(\":generated-coordinatorlayout\")"
        )
    }

    override fun isTemplateWithinScope(moduleName: String, templateName: String): Boolean {
        return false
    }

    companion object {
        private const val lastPackageDirName = "material"
        /*private val ignorePathWithDirName = listOf(
                /* not yet in release -> https://issuetracker.google.com/issues/113676493 */
                "CircularRevealGridLayout"

        )
        private val ignorePathWithDirRegex = Regex(".*(${ignorePathWithDirName.joinToString("|"){ "/$it/"}}).*")*/
    }
}