package com.laidpack.sourcerer.generator.resources

import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.generators.templates.BehaviorUtilsTemplateProvider
import com.laidpack.sourcerer.generator.peeker.*
import jetbrains.exodus.entitystore.Entity
import kotlinx.dnq.*
import kotlinx.dnq.enum.XdEnumEntityType
import java.io.FileNotFoundException
import java.lang.IllegalStateException
import java.net.URL
import java.nio.file.Path
import java.nio.file.Paths




interface WidgetConnoisseur {
    val paths : Set<Path>
    val sourceProvider: () -> XdWidgetSource
    fun setRootPath(env: SourcererEnvironment)
    fun isValidWidgetPath(path: Path): Boolean
    fun getAttributesFileUrl(filePath: Path, env: SourcererEnvironment): URL
    fun getModuleName(widget: Widget): String
    fun getDependencies(widget: Widget): List<String>
    fun isTemplateWithinScope (moduleName: String, templateName: String): Boolean

}

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

class AndroidWidgetParser : WidgetConnoisseur {
    override lateinit var paths: Set<Path>
    private lateinit var lastPathName: String
    override val sourceProvider = {
        Store.transactional {
            XdWidgetSource.Android
        }
    }

    override fun setRootPath(env: SourcererEnvironment) {
        paths = setOf(
                env.sdkStructure.sourcePath.resolve("android"),
                env.sdkStructure.sourcePath.resolve("com/android")
        )
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

class MaterialDesignWidgetParser : WidgetConnoisseur {
    override val sourceProvider = {
        Store.transactional {
            XdWidgetSource.MaterialDesign
        }
    }
    override lateinit var paths: Set<Path>
    override fun setRootPath(env: SourcererEnvironment) {
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
                "project(\":generated-cardview\")"
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

class SupportWidgetParser : WidgetConnoisseur {
    override val sourceProvider = {
        Store.transactional {
            XdWidgetSource.Support
        }
    }
    override lateinit var paths: Set<Path>
    private val lastPathNames = mutableSetOf<String>()
    override fun setRootPath(env: SourcererEnvironment) {
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
        val libDirPath = Paths.get(Paths.get(splitPathString[0]).parent.parent.toString().replace("file:",""))
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
        if (libInfo.name == "leanback") {
            dependencies.add("project(\":generated-recyclerview\")")
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


class XdWidgetSource(entity: Entity) : XdEnumEntity(entity)  {
    companion object : XdEnumEntityType<XdWidgetSource>() {
        val Android by enumField { presentation = WidgetSource.Android.name }
        val MaterialDesign by enumField { presentation = WidgetSource.MaterialDesign.name }
        val Support by enumField { presentation = WidgetSource.Support.name }
    }
    var presentation by xdRequiredStringProp()
        private set

    fun toEnum(): WidgetSource {
        return Store.transactional {
            enumValueOf(presentation)
        }
    }
}

enum class WidgetSource {
    Android,
    MaterialDesign,
    Support
}

class Widget (entity: Entity) : XdEntity(entity) {
    companion object : XdNaturalEntityType<Widget>()
    var source by xdLink1(XdWidgetSource)
    var viewClass by xdLink0_1(XdClass::widgetAsBeingView)
    val layoutParamClasses by xdLink0_N(XdClass::widgetAsBeingLayoutParams)
    var file : XdFile by xdParent(XdFile::widgets)
    var attributesXmlUrlAsString by xdStringProp()
    val attributesXmlUrl: URL
        get() = Store.transactional {
            URL(attributesXmlUrlAsString)
        }
    val hasAttributesFile: Boolean
        get() = Store.transactional {
            if (attributesXmlUrl.isFileInJar()) {
                try {
                    attributesXmlUrl.openStream().close()
                    true
                } catch (e: FileNotFoundException) {
                    false
                }
            } else {
                attributesXmlUrl.toFile().exists()
            }
        }
}


