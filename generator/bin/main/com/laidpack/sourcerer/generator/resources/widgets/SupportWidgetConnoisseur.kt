package com.laidpack.sourcerer.generator.resources.widgets

import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.generators.templates.BehaviorUtilsTemplateProvider
import com.laidpack.sourcerer.generator.index.toJarUri
import com.laidpack.sourcerer.generator.resources.*
import java.lang.IllegalStateException
import java.net.URL
import java.nio.file.Path
import java.nio.file.Paths

class SupportWidgetConnoisseur : WidgetConnoisseur {
    override val priority: Int = 1
    override val sourceProvider = {
        Store.transactional {
            XdWidgetSource.Support
        }
    }
    override lateinit var paths: Set<Path>
    private val lastPathNames = mutableSetOf<String>()
    override fun setScanPaths(env: SourcererEnvironment) {
        paths = env.supportSourceRootDirPaths.toSet()
        paths.forEach {path ->
            if (path.nameCount > 0) {
                val index = if (path.nameCount > 1) path.nameCount - 1 else 0
                lastPathNames.add(path.getName(index).toString())
            }
        }

    }
    override fun getAttributesFileUrl(filePath: Path, env: SourcererEnvironment): URL {
        val splitPathString = filePath.toString().split("!")
        val libDirPath = Paths.get(Paths.get(splitPathString[0]).parent.parent.toString().replace("file:", ""))
        for(file in libDirPath.toFile().walk()) {
            if (file.isFile && file.name.toString().endsWith(".aar")) {
                return URL(file.toPath().toJarUri().toString() + "!/res/values/values.xml")
            }
        }
        throw IllegalStateException("No aar lib file found @ $libDirPath")
    }
    override fun isValidWidgetPath(path: Path): Boolean {
        val pathAsString = path.toString()
        if (WidgetUtils.isInExcludedPath(pathAsString)) return false
        if (pathAsString.endsWith("PinPicker.java") || pathAsString.endsWith("CarToolbar.java")) return false // not yet in release

        loop@ for (i in path.nameCount - 1 downTo 0) {
            val name = path.getName(i).toString()
            when {
                name == "widget" -> return true
                lastPathNames.contains(name) -> break@loop
            }
        }
        return false
    }

    override fun getModuleName(widget: Widget): String {
        return "generated-${getLibModuleAndName(widget).name}"
    }

    override fun getDependencies(widget: Widget): List<String> {
        val libInfo = getLibModuleAndName(widget)
        val version = when (libInfo.name) {
            "car" -> "androidXcar"
            "media-widget" -> "androidXmediaWidget"
            else -> "androidX"
        }
        val dependencies = mutableListOf(
                "${libInfo.module}:${libInfo.name}:\$$version",
                "project(\":services\")",
                "project(\":generated\")"
        )
        when (libInfo.name) {
            "leanback" -> dependencies.add("project(\":generated-recyclerview\")")
            "wear" -> dependencies.add("project(\":generated-recyclerview\")")
            "emoji-appcompat" -> dependencies.add("project(\":generated-appcompat\")")
            "car" -> dependencies.add("project(\":generated-cardview\")")
        }

        return dependencies
    }

    private data class LibInfo(val module: String, val name: String)
    private fun getLibModuleAndName(widget: Widget): LibInfo {
        val libDirPath = Store.transactional { widget.file.filePath }
        val matchResult = libRegex.find(libDirPath.toString())
                ?: throw IllegalStateException("No lib name found via regex in '$libDirPath'")
        return LibInfo(
                matchResult.groupValues[1],
                matchResult.groupValues[2]
        )
    }

    override fun isTemplateWithinScope(moduleName: String, templateName: String): Boolean {
        return moduleName == "generated-coordinatorlayout" && templateName == BehaviorUtilsTemplateProvider.templateName
    }

    companion object {
        private val libRegex = Regex(".*[\\\\/](androidx\\..+?)[\\\\/](.+?)[\\\\/].*")
    }
}