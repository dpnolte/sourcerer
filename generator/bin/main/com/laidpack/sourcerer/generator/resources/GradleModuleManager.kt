package com.laidpack.sourcerer.generator.resources

import com.laidpack.sourcerer.generator.getExistingOrCreateFile
import com.laidpack.sourcerer.generator.resources.templates.getAndroidManifestTemplate
import com.laidpack.sourcerer.generator.resources.templates.getBuildGradleContent
import com.laidpack.sourcerer.generator.resources.templates.getProguardRulesTemplate
import com.laidpack.sourcerer.generator.resources.templates.getStringXmlTemplate
import java.io.File
import java.nio.file.Path

class GradleModuleManager(private val env: SourcererEnvironment, private val widgetRegistry: WidgetRegistry) {
    private val updatedModules = mutableSetOf<String /* module name */>()

    fun getModuleDirForWidget(widget: Widget): File {
        val parser = widgetRegistry[widget]
        val moduleName = parser.getModuleName(widget)
        val sourcePath = env.rootPath.resolve("$moduleName/src/main/kotlin")
        val flagFilePath = env.rootPath.resolve("$moduleName/$generatedFlagFileName")
        val packageName = widgetRegistry.getPackageName(widget)
        if (!sourcePath.toFile().exists()) {
            generateModule(
                    moduleName,
                    parser.getDependencies(widget),
                    packageName
            )
            updatedModules.add(moduleName)
        } else if (!updatedModules.contains(moduleName)) {
            // always update the following:
            val modulePath = getModulePath(moduleName)
            generateBuildGradleFile(modulePath, parser.getDependencies(widget), packageName)
            generateProguardRulesFile(modulePath)
            val mainPath = modulePath.resolve("src/main")
            generateAndroidManifest(mainPath, packageName)
            updatedModules.add(moduleName)
        }
        // always create flag file
        flagFilePath.getExistingOrCreateFile()
        return sourcePath.toFile()
    }


    fun generateModule(
            moduleName: String,
            extraDependencies: List<String>,
            packageName: String,
            generateGradleFile: (modulePath: Path, extraDependencies: List<String>, packageName: String) -> Unit = this::generateBuildGradleFile
    ) {
        val modulePath = getModulePath(moduleName)
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
        generateGradleFile(modulePath, extraDependencies, packageName)
        generateProguardRulesFile(modulePath)
        generateAndroidManifest(mainPath, packageName)
        generateStringXmlFile(paths["valuesPath"] as Path)
        addModuleToGradleSettings(moduleName)
    }

    private fun getModulePath(moduleName: String): Path {
        return env.rootPath.resolve(moduleName)
    }

    private fun generateGitIgnoreFile(modulePath: Path) {
        val gitIgnoreFilePath = modulePath.resolve(".gitignore")
        val file = gitIgnoreFilePath.getExistingOrCreateFile()
        file.writeText("/build\n")
    }

    private fun generateBuildGradleFile(modulePath: Path, extraDependencies: List<String>, packageName: String) {
        val path = modulePath.resolve("build.gradle")
        val file = path.getExistingOrCreateFile()
        file.writeText(
                getBuildGradleContent(
                        packageName,
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
        val file = path.getExistingOrCreateFile()
        file.writeText(getProguardRulesTemplate())
    }

    private fun generateAndroidManifest(mainPath: Path, packageName: String) {
        val path = mainPath.resolve("AndroidManifest.xml")
        val file = path.getExistingOrCreateFile()
        file.writeText(getAndroidManifestTemplate(packageName))
    }

    private fun generateStringXmlFile(valuesPath: Path) {
        val path = valuesPath.resolve("strings.xml")
        val file = path.getExistingOrCreateFile()
        file.writeText(getStringXmlTemplate())
    }

    private fun addModuleToGradleSettings(moduleName: String) {
        val settingsGradleFile = env.rootPath.resolve("settings.gradle").toFile()
        val content = settingsGradleFile.readText().trim()
        val moduleEntry = "':$moduleName'"
        if (!content.contains(moduleEntry)) {
            settingsGradleFile.writeText("$content,\n\t\t$moduleEntry")
        }
    }

    companion object {
        const val generatedFlagFileName = ".generated"
    }

}