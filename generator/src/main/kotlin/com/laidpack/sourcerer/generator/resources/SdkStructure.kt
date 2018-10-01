package com.laidpack.sourcerer.generator.resources

import java.io.File
import java.io.FileNotFoundException
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.reflect.KClass

class AndroidSdkStructure private constructor (
        val sdkPath: Path,
        val platformPath: Path,
        val sourcePath: Path,
        val apiLevel: Int
) {
    val dataPath = platformPath.resolve("data")
    val resourcePath = dataPath.resolve("res")
    val widgetFilePath = dataPath.resolve("widgets.txt")
    val valuesPath = resourcePath.resolve("values")
    val attributesFilePath = valuesPath.resolve("attrs.xml")

    fun getSourceFileContent(classType: KClass<*>): String {
        val file = getSourceFile(classType)
        return file.readText()
    }

    fun getSourceFileContent(className: String): String {
        val file = getSourceFile(className)
        return file.readText()
    }

    fun getSourceFile(classType: KClass<*>): File {
        return getSourceFile(classType.java.name)
    }

    fun getSourceFile(providedClassName: String): File {
        val names = providedClassName.split('.')
        val packageNames: List<String> = names.subList(0, names.lastIndex)
        val lastName = names[names.lastIndex]
        val className = if(lastName.contains('$')) {
            val splitLastName = lastName.split('$')
            splitLastName[0]
        } else lastName
        var path = sourcePath.resolve(packageNames.joinToString(File.separator))
        path = path.resolve("$className.java")
        val file = path.toFile()
        if (!file.exists()) {
            throw FileNotFoundException("Class '$className' not found at ${path.toAbsolutePath()}")
        }
        if (!file.canRead()) throw IllegalAccessException("Cannot read class file '$className' content")
        return file
    }

    fun sourceFileExists(classType: KClass<*>): Boolean {
        val names = classType.java.name.split('.')
        val packageNames: List<String> = names.subList(0, names.lastIndex)
        val className = classType.simpleName
        var path = sourcePath.resolve(packageNames.joinToString(File.separator))
        /*packageNames.forEach {
            paths = paths.resolve(it)
        }*/
        path = path.resolve("$className.java")
        val file = path.toFile()
        return file.exists() && file.canRead()
    }

    companion object {
        fun get(apiLevel: Int, providedSdkDir: String? = null): AndroidSdkStructure {
            val sdkDirString = providedSdkDir ?: System.getenv("ANDROID_HOME")
            val sdkDirPath = Paths.get(sdkDirString)
            val sdkDir = sdkDirPath.toFile()
            if (!sdkDir.exists()) {
                throw IllegalArgumentException("Android SDK does not exist at the specified paths: '{$providedSdkDir}' and/or \$ANDROID_HOME is not set ${System.getenv("ANDROID_HOME")}");
            }
            val platformsPath = sdkDirPath.resolve("platforms")
            val sourcesPath = sdkDirPath.resolve("sources")
            //val platformsDirectory = platformsPath.toFile()

            val platformPath = platformsPath.resolve("android-$apiLevel")
            val platformDirectory = platformPath.toFile()
            if (!isValidPlatformDirectory(platformDirectory)) {
                throw java.lang.IllegalStateException("Android $apiLevel platform directory is invalid @ $platformPath")
            }
            val sourcePath = sourcesPath.resolve("android-$apiLevel")
            if (!areSourcesAvailable(sourcePath)) {
                throw java.lang.IllegalStateException("No sources available for android-$apiLevel @ $sourcePath")
            }
            println("Selected platform $apiLevel @ $platformPath")

            return AndroidSdkStructure(sdkDirPath, platformPath, sourcePath, apiLevel)
        }

        fun getLatest(providedSdkDir: String? = null): AndroidSdkStructure {
            val sdkDirString = providedSdkDir ?: System.getenv("ANDROID_HOME")
            val sdkDirPath = Paths.get(sdkDirString)
            val sdkDir = sdkDirPath.toFile()
            if (!sdkDir.exists()) {
                throw IllegalArgumentException("Android SDK does not exist at the specified paths: '{$providedSdkDir}' and/or \$ANDROID_HOME is not set ${System.getenv("ANDROID_HOME")}");
            }
            val platformsPath = sdkDirPath.resolve("platforms")
            val sourcesPath = sdkDirPath.resolve("sources")
            val platformsDirectory = platformsPath.toFile()
            val platformDirectoryStringList = platformsDirectory.list().filter { it.startsWith("android-")}
            var selectedApiLevel = 0
            var selectedPlatformPath: Path? = null
            var selectedSourcePath: Path? = null
            platformDirectoryStringList.forEach {
                val platformPath = platformsPath.resolve(it)
                val platformDirectory = platformPath.toFile()
                var apiLevel = getVersionNumberFromDirectory(it)
                if (apiLevel > selectedApiLevel  && isValidPlatformDirectory(platformDirectory)) {
                    // check if we have sources as well
                    val sourcePath = sourcesPath.resolve("android-$apiLevel")
                    if (areSourcesAvailable(sourcePath)) {
                        selectedPlatformPath = platformPath
                        selectedSourcePath = sourcePath
                        selectedApiLevel = apiLevel
                    }
                }
            }

            if (selectedPlatformPath == null) throw IllegalStateException("The specified directory '$sdkDirString' does not contain any Android SDK sources + platform (which should contain 'android.jar', source directory, and 'data/res' subdirectory)")

            println("Selected platform $selectedApiLevel @ $selectedPlatformPath")

            return AndroidSdkStructure(sdkDirPath, selectedPlatformPath as Path, selectedSourcePath as Path, selectedApiLevel)
        }

        private fun isValidPlatformDirectory(dir: File): Boolean {
            return dir.isDirectory &&
                    dir.list().find { it.endsWith("android.jar") } != null &&
                    Paths.get(dir.path, "data", "res").toFile().isDirectory
        }

        private fun areSourcesAvailable(sourcePath: Path): Boolean {
            return sourcePath.toFile().isDirectory
        }

        private fun getVersionNumberFromDirectory(pathString: String): Int {
            var version = 0
            val splittedString = pathString.split("-")
            if (splittedString.size == 2) {
                val number = splittedString[1].toIntOrNull()
                if (number != null)
                    version = number
            }
            return version
        }
    }
}

