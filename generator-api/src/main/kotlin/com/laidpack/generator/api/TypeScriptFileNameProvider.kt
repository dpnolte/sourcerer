package com.laidpack.generator.api

object TypeScriptFileNameProvider {
    fun getAttributesFileName(packageName: String): String {
        val fileBase = packageName.substring(packageName.lastIndexOf(".generated") + 1)
                .replace(".", "-")

        return "$fileBase.attributes$fileExtension"
    }
    fun getElementsFileName(attributesFileName: String): String {
        return attributesFileName.replace(".attributes.", ".elements.")
    }
    const val fileExtension = ".d.ts"
}