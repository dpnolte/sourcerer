package com.laidpack.sourcerer.generator.generators

import com.laidpack.sourcerer.generator.resources.GradleModuleManager
import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import com.squareup.kotlinpoet.ClassName
import java.io.File

class ProjectGeneratorManager(private val targetDir: File, moduleGenerators: List<ModuleGeneratorManager>) {
    private val moduleBootstrapperClassNames = moduleGenerators.map {
        it.bootstrapperClassName
    }
    val servicesBootstrapperClassName = ClassName(
            SourcererEnvironment.servicePackageName, "ServicesBootstrapper"
    )

    fun generateServicesBootstrapper() {
        val fileSpec = BootstrapServicesGenerator(
                servicesBootstrapperClassName,
                moduleBootstrapperClassNames
        ).generateFile()
        fileSpec.writeTo(targetDir)
    }

    companion object {
        fun generateMultiFormatFiles(targetDir: File) {
            println("=================================")
            val file = MultiFormatGenerator().generateFile()
            file.writeTo(targetDir)

            val file2 = FormatEnumGenerator().generateFile()
            file2.writeTo(targetDir)

            //TemplateGenerator(targetDir, env.apiSourcePath).writeFiles()
            println("Created multi-formats class and formats enum...")
            println("=================================")
        }

        fun deleteOldGeneratedFiles(env: SourcererEnvironment) {
            println("=================================")
            for (file in env.rootPath.toFile().listFiles()) {
                if (file.isDirectory && file.listFiles().any { it.name == GradleModuleManager.generatedFlagFileName }) {
                    val sourceDir = file.toPath().resolve("src/main/kotlin").toFile()
                    if (sourceDir.exists() && sourceDir.isDirectory) {
                        for (generatedFile in sourceDir.listFiles()) {
                            generatedFile.deleteRecursively()
                        }
                    }
                }
            }
            println("Deleted old generated files")
            println("=================================")
        }

        fun deleteTempFiles(env: SourcererEnvironment) {
            println("=================================")
            for (file in env.stubModuleGeneratedPackageDir.listFiles()) {
                file.deleteRecursively()
            }
            val dir = ".${env.stubModuleGeneratedPackageDir.toString().replace(env.rootPath.toString(), "")}"
            println("Deleted temp classes in stub module @ $dir")
            println("=================================")
        }
    }
}