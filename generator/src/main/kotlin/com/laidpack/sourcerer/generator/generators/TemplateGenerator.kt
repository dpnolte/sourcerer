package com.laidpack.sourcerer.generator.generators

import com.laidpack.sourcerer.generator.generators.templates.BehaviorUtilsTemplateProvider
import com.laidpack.sourcerer.generator.generators.templates.CodeTemplateProvider
import com.laidpack.sourcerer.generator.resources.Widget
import com.laidpack.sourcerer.generator.resources.WidgetConnoisseur
import java.io.File

class TemplateGenerator() {
    fun generateTemplates(
            moduleName: String,
            widgetConnoisseur: WidgetConnoisseur,
            targetDir: File,
            packageName: String
    ) {
        for (template in templates) {
            if (widgetConnoisseur.isTemplateWithinScope(moduleName, template.templateName)) {
                val content = template.getTemplate(packageName)
                val targetFile = targetDir.resolve(
                        packageName.replace(".", File.separator) + File.separator + template.fileName
                )
                targetFile.writeText(content)
            }
        }
    }

    companion object {
        private val templates = listOf<CodeTemplateProvider>(
                BehaviorUtilsTemplateProvider()
        )
    }
}