package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.lint.ApiDetector
import com.laidpack.sourcerer.generator.peeker.ClassInfo
import com.laidpack.sourcerer.generator.peeker.ClassRegistry
import com.laidpack.sourcerer.generator.resources.*
import com.squareup.kotlinpoet.ClassName
import kotlinx.dnq.query.*


fun main(args: Array<String>) {
    val env = SourcererEnvironment(args)

    val widgetRegistry = getWidgetRegistry(env)
    val moduleManager = GradleModuleManager(env, widgetRegistry)
    Store.init(env)
    //val lintResult = LintResultsParser(env.lintReportsFilePath).parse()
    //var totalCounter = 0
    deleteOldGeneratedFiles(env)
    for (sourcererResult in getSourcererResults()) {
        /*var counter = 0
        val issues = lintResult.issues[sourcererResult.targetClassName.simpleName]
        if (issues != null) {
            for (issue in issues.values) {
                val relevantCodeBlocks = if (issue.methodName == LintResultsParser.ALL_METHODS) {
                    sourcererResult.codeBlocks
                } else {
                    sourcererResult.codeBlocks.filter {
                        it.setters.any { setter -> setter.name == issue.methodName }
                        || it.attributes.values.any { attr -> attr.getters.any { getter -> getter.name == issue.methodName } }
                    }
                }
                if (relevantCodeBlocks.isEmpty()) continue //throw IllegalStateException("Issue ${issue.id} - ${issue.message} is not targeting one of our methods, Method name ${issue.name}")
                relevantCodeBlocks.forEach { codeBlock ->
                    if (codeBlock.minimumApiLevel == 0 || issue.minimumApiLevel < codeBlock.minimumApiLevel) {
                        codeBlock.minimumApiLevel = issue.minimumApiLevel
                    }
                }
                counter += 1
                totalCounter += 1
            }
        }*/
        val moduleDir = moduleManager.getModuleDirForWidget(getWidget(sourcererResult))
        generateAttributeFile(moduleDir, sourcererResult)
        generateFactoryFiles(moduleDir, sourcererResult, env.minSdkVersion)
        //println("-- Updated ${sourcererResult.targetClassName.simpleName}Factory with $counter minimum api level checks")
        println("-- moved ${sourcererResult.targetClassName} to module ${moduleDir.name} @ $moduleDir")
    }
    //println("Fixed $totalCounter minimum api level requirement issues")
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

fun runLint(env: SourcererEnvironment) {
    initParserAndStore(env)
    val classRegistry = ClassRegistry()
    val classInfo = classRegistry[ClassName.bestGuess("android.widget.Chronometer")] as ClassInfo
    val apiDetector = ApiDetector(env.rootPath.resolve("stub-app").toFile())

    val classApi = apiDetector.getClassVersion(classInfo.targetClassName)
    println("android.widget.Chronometer: $classApi")

    val methodInfo = classInfo.methodDeclarations["setCountDown"]!!.first()
    val api = apiDetector.getMemberVersion(classInfo.targetClassName, methodInfo)
    println("setCountDown: $api")

    val methodInfo2 = classInfo.methodDeclarations["setOnChronometerTickListener"]!!.first()
    val api2 = apiDetector.getMemberVersion(classInfo.targetClassName, methodInfo2)
    println("setOnChronometerTickListener: $api2")

    val api3 = apiDetector.getFieldVersion(classInfo.targetClassName, "X")
    println("X: $api3")
}

