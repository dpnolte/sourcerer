package com.laidpack.sourcerer.generator.resources.widgets

import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.resources.*
import java.net.URL
import java.nio.file.Path

class AndroidWidgetConnoisseur : WidgetConnoisseur {
    override val priority: Int = 3
    override lateinit var paths: Set<Path>
    private lateinit var lastPathName: String
    override val sourceProvider = {
        Store.transactional {
            XdWidgetSource.Android
        }
    }

    override fun setScanPaths(env: SourcererEnvironment) {
        val p = mutableSetOf(env.sdkStructure.sourcePath.resolve("android"))
        // env.sdkStructure.sourcePath.resolve("com/android")
        val layoutLibPath = env.sdkStructure.sourcePath.resolve("com/android/layoutlib")
        if (layoutLibPath.toFile().exists()) {
            p.add(layoutLibPath)
        }
        paths = p

        lastPathName = "android"
    }
    override fun getAttributesFileUrl(filePath: Path, env: SourcererEnvironment): URL {
        return env.sdkStructure.attributesFilePath.toUri().toURL()
    }

    override fun isValidWidgetPath(path: Path): Boolean {
        if (WidgetUtils.isInExcludedPath(path)) return false

        loop@ for (i in path.nameCount - 1 downTo 0) {
            val name = path.getName(i).toString()
            when (name) {
                "widget", "view" -> return path.getName(i - 1).toString() == "android"
                lastPathName -> break@loop
            }
        }
        return false
    }

    override fun getModuleName(widget: Widget): String {
        return "generated"
    }

    override fun getDependencies(widget: Widget): List<String> {
        return listOf(
                "project(\":services\")"
        )
    }
    override fun isTemplateWithinScope(moduleName: String, templateName: String): Boolean {
        return false
    }
}