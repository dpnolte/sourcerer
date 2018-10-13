package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.generators.ClassGeneratorManager
import com.laidpack.sourcerer.generator.generators.ModuleGeneratorManager
import com.laidpack.sourcerer.generator.generators.ProjectGeneratorManager
import com.laidpack.sourcerer.generator.resources.*
import com.squareup.kotlinpoet.ClassName
import kotlinx.dnq.query.*


fun main(args: Array<String>) {
    val env = SourcererEnvironment(args)
    val widgetRegistry = getWidgetRegistry(env)
    val moduleManager = GradleModuleManager(env, widgetRegistry)
    Store.init(env)
    ProjectGeneratorManager.deleteOldGeneratedFiles(env)
    ProjectGeneratorManager.generateMultiFormatFiles(env.servicesDir)
    val resultsPerModule = sortedMapOf<String, MutableList<SourcererResult>>()
    val targetClassNameToPackageName = mutableMapOf<ClassName, String>()
    println("Sorting results...")
    for (sourcererResult in getSourcererResults()) {
        val widget = getWidget(sourcererResult)
        val parser = widgetRegistry[widget]
        val moduleName = parser.getModuleName(widget)
        val packageName = "${SourcererEnvironment.rootPackageName}.${moduleName.replace("-", ".")}"
        targetClassNameToPackageName[sourcererResult.targetClassName] = packageName
        if (resultsPerModule.containsKey(moduleName)) {
            (resultsPerModule[moduleName] as MutableList<SourcererResult>).add(sourcererResult)
        } else {
            resultsPerModule[moduleName] = mutableListOf(sourcererResult)
        }
    }
    val moduleGenerators = mutableListOf<ModuleGeneratorManager>()
    val reservedElementTypes = mutableSetOf<String>()
    for (moduleNameAndResults in resultsPerModule) {
        val moduleName = moduleNameAndResults.key
        val firstResult = moduleNameAndResults.value.first()
        val packageName = targetClassNameToPackageName[firstResult.targetClassName] as String
        val widget = getWidget(firstResult)
        val moduleDir = moduleManager.getModuleDirForWidget(widget)
        val classGenerators = mutableListOf<ClassGeneratorManager>()
        println("Module: $moduleName")
        println("\t-- package: $packageName")
        println("\t-- module dir: .${moduleDir.toString().replace(env.rootPath.toString(), "")}")
        println("========================================")
        for (sourcererResult in moduleNameAndResults.value) {
            val superClassPackageName = if(sourcererResult.superClassName != null) {
                targetClassNameToPackageName[sourcererResult.superClassName]
            } else null
            val classGenerator = ClassGeneratorManager(
                    moduleDir,
                    packageName,
                    superClassPackageName,
                    sourcererResult,
                    env.minSdkVersion,
                    reservedElementTypes
            )
            classGenerator.generateAttributeFile()
            classGenerator.generateFactoryFile()
            classGenerators.add(classGenerator)
            reservedElementTypes.add(classGenerator.elementType)
            println("+ ${classGenerator.attributesFileName}")
            println("+ ${classGenerator.factoryFileName}")
        }
        val moduleGenerator = ModuleGeneratorManager(moduleDir, moduleName, packageName, classGenerators)
        moduleGenerator.generateBootstrapModuleFile()
        moduleGenerators.add(moduleGenerator)
        println("-- added module bootstrapper ${moduleGenerator.bootstrapperClassName.simpleName}")
        println("========================================")

    }
    val projectGenerator = ProjectGeneratorManager(env.servicesDir, moduleGenerators)
    projectGenerator.generateServicesBootstrapper()
    println("-- added services bootstrapper ${projectGenerator.servicesBootstrapperClassName.simpleName}")
    ProjectGeneratorManager.deleteTempFiles(env)
    println("Done!")


}

fun getSourcererResults(): List<SourcererResult> {
    return Store.transactional {
        XdSourcererResult.all().toList().map {
            it.toSnapshot()
        }
    }
}

fun getWidget(sourcererResult: SourcererResult): Widget {
    return Store.transactional {
        Widget.query(
                Widget::file eq sourcererResult.indexedClass.file
        ).firstOrNull() ?: throw IllegalStateException("Widget not found for file ${sourcererResult.indexedClass.file.url}")
    }
}
