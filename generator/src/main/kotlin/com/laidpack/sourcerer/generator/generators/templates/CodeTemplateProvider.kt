package com.laidpack.sourcerer.generator.generators.templates

interface CodeTemplateProvider {
    val templateName: String
    val fileName: String
    fun getTemplate(packageName: String): String
}