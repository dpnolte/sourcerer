package com.laidpack.sourcerer.generator.kapt

import java.io.File

object AttributesFileWriter {
    fun writeImports(file: File, imports: String) {
        if (file.exists()) {
            val currentContent = file.readText()
            val pos = currentContent.indexOf(typesTag)
            val typesAndElementsContent = if (pos != -1) {
                currentContent.substring(pos)
            } else currentContent
            file.writeText(imports + typesAndElementsContent)
        } else {
            file.writeText(imports)
        }
    }
    fun writeTypes(file: File, types: String) {
        if (file.exists()) {
            val currentContent = file.readText()
            val pos = currentContent.indexOf(elementsTag)
            val elementsContent = if (pos != -1) {
                currentContent.substring(pos)
            } else currentContent
            val pos2 = currentContent.indexOf(typesTag)
            val importsContent = when {
                pos2 != -1 -> currentContent.substring(0, pos2)
                pos == -1 -> currentContent
                else -> ""
            }
            file.writeText( importsContent + typesTag + "\n" + types + elementsContent)
        } else {
            file.writeText(typesTag + "\n" + types)
        }
    }
    fun writeElements(file: File, elements: String) {
        if (file.exists()) {
            val currentContent = file.readText()
            val pos = currentContent.indexOf(elementsTag)
            val importAndTypesContent = if (pos != -1) {
                currentContent.substring(0, pos)
            } else currentContent
            file.writeText(importAndTypesContent + elementsTag + "\n" + elements)
        } else {
            file.writeText(elementsTag + "\n" + elements)
        }
    }

    fun writeFlags(file: File, flags: String, nameSpace: String) {

    }
    private const val typesTag = "// types"
    private const val elementsTag = "// elements"
}