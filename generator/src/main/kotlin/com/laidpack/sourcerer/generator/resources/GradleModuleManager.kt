package com.laidpack.sourcerer.generator.resources

import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.resources.templates.getAndroidManifestTemplate
import com.laidpack.sourcerer.generator.resources.templates.getBuildGradleContent
import com.laidpack.sourcerer.generator.resources.templates.getProguardRulesTemplate
import com.laidpack.sourcerer.generator.resources.templates.getStringXmlTemplate
import java.io.File
import java.nio.file.Path
import kotlin.IllegalStateException

class GradleModuleManager(private val env: SourcererEnvironment, private val widgetRegistry: WidgetRegistry) {

    fun getModuleDirForWidget(widget: Widget): File {
        val parser = widgetRegistry[widget]
        val moduleName = parser.getModuleName(widget)
        val sourcePath = env.rootPath.resolve("$moduleName/src/main/kotlin")
        if (!sourcePath.toFile().exists()) {
            generateModule(moduleName, parser.getDependencies(widget))
        }
        return sourcePath.toFile()
    }

    fun generateModule(moduleName: String, extraDependencies: List<String>) {
        val modulePath = env.rootPath.resolve(moduleName)
        val mainPath = modulePath.resolve("src/main")
        val paths = mapOf(
                "modulePath" to modulePath,
                "libs" to modulePath.resolve("libs"),
                "sourceFolder" to mainPath.resolve("kotlin"),
                "drawablePath" to mainPath.resolve("res/drawable"),
                "valuesPath" to mainPath.resolve("res/values")
        )
        for(path in paths.values) {
            path.toFile().mkdirs()
        }

        generateGitIgnoreFile(modulePath)
        generateBuildGradleFile(modulePath, extraDependencies)
        generateProguardRulesFile(modulePath)
        generateAndroidManifest(mainPath)
        generateStringXmlFile(paths["valuesPath"] as Path)
        addModuleToGradleSettings(moduleName)
    }

    private fun generateGitIgnoreFile(modulePath: Path) {
        val gitIgnoreFilePath = modulePath.resolve(".gitignore")
        val file =  createFile(gitIgnoreFilePath)
        file.writeText("/build\n")
    }

    private fun generateBuildGradleFile(modulePath: Path, extraDependencies: List<String>) {
        val path = modulePath.resolve("build.gradle")
        val file = createFile(path)
        file.writeText(
                getBuildGradleContent(
                        extraDependencies.joinToString("\n") {
                            val dependency = if (it.startsWith("project(")) {
                                it
                            } else "\"$it\""
                            "\timplementation $dependency"
                        }
                )
        )
    }

    private fun generateProguardRulesFile(modulePath: Path) {
        val path = modulePath.resolve("proguard-rules.pro")
        val file = createFile(path)
        file.writeText(getProguardRulesTemplate())
    }

    private fun generateAndroidManifest(mainPath: Path) {
        val path = mainPath.resolve("AndroidManifest.xml")
        val file = createFile(path)
        file.writeText(getAndroidManifestTemplate())
    }

    private fun generateStringXmlFile(valuesPath: Path) {
        val path = valuesPath.resolve("strings.xml")
        val file = createFile(path)
        file.writeText(getStringXmlTemplate())
    }

    private fun createFile(path: Path): File {
        val file = path.toFile()
        val created = file.createNewFile()
        if (!created) throw IllegalStateException("Failed to create file $path")
        return file
    }

    private fun addModuleToGradleSettings(moduleName: String) {
        val settingsGradleFile = env.rootPath.resolve("settings.gradle").toFile()
        val content = settingsGradleFile.readText().trim()
        settingsGradleFile.writeText("$content, ':$moduleName'\n")
    }

}