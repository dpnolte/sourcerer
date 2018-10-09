package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.resources.*
import kotlinx.dnq.query.*


fun main(args: Array<String>) {
    val env = SourcererEnvironment(args)
    val widgetRegistry = getWidgetRegistry(env)
    val moduleManager = GradleModuleManager(env, widgetRegistry)
    Store.init(env)
    deleteOldGeneratedFiles(env)
    for (sourcererResult in getSourcererResults()) {
        val moduleDir = moduleManager.getModuleDirForWidget(getWidget(sourcererResult))
        generateAttributeFile(moduleDir, sourcererResult)
        generateFactoryFiles(moduleDir, sourcererResult, env.minSdkVersion)
        println("-- moved ${sourcererResult.targetClassName} to module ${moduleDir.name} @ $moduleDir")
    }
    deleteTempFiles(env)
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
