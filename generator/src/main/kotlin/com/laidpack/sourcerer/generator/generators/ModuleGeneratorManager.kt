package com.laidpack.sourcerer.generator.generators

import com.laidpack.sourcerer.generator.resources.widgets.WidgetConnoisseur
import com.laidpack.sourcerer.generator.toCamelCase
import com.squareup.kotlinpoet.ClassName
import java.io.File

class ModuleGeneratorManager(
        private val targetDir: File,
        private val moduleName: String,
        private val targetPackageName: String,
        private val widgetConnoisseur: WidgetConnoisseur,
        private val classGenerators: List<ClassGeneratorManager>
) {
    private val adapterWrappers = classGenerators.map { it.adapterRegistrationWrapper }
    private val viewGroupToLayoutParamsWrappers by lazy {
        val wrappers = mutableListOf<AssociatedViewGroupAndLayoutParamsWrapper>()
        for (classGenerator in classGenerators) {
            if (classGenerator.viewGroupAndLayoutParamsWrapper != null) {
                wrappers.add(classGenerator.viewGroupAndLayoutParamsWrapper)
            }
        }
        wrappers.toList()
    }

    private val factoryWrappers = classGenerators.map { it.factoryWrapper }
    val bootstrapperClassName = ClassName(
            targetPackageName,
            moduleName.replace("-", "_").toCamelCase() + "Bootstrapper"
    )

    fun generateBootstrapModuleFile() {
        val fileSpec = BootstrapModuleGenerator(
            bootstrapperClassName,
                adapterWrappers,
                viewGroupToLayoutParamsWrappers,
                factoryWrappers
        ).generateFile()
        fileSpec.writeTo(targetDir)
    }

    fun generateTemplateFiles() {
        TemplateGenerator().generateTemplates(
                moduleName,
                widgetConnoisseur,
                targetDir,
                targetPackageName
        )
    }
}