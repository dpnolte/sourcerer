package com.laidpack.generator.api

object TypeScriptNameProvider {
    fun getAttributesFileName(packageName: String): String {
        val fileBase = getNamespace(packageName).toLowerCase().replace("types", "")

        return "$fileBase$fileTypeScriptExtension"
    }
    fun getElementsFileName(attributesFileName: String): String {
        return attributesFileName
    }
    fun getNamespace(packageName: String): String {
        val names = packageName.split(".").toMutableList()
        var phrase = ""
        var selectedName = names.last().capitalize()
        names.removeAt(names.lastIndex)
        while(selectedName != generatedPackageSimpleName ) {
            phrase += selectedName
            selectedName = names.last().capitalize()
            names.removeAt(names.lastIndex)
        }
        return if (phrase.isBlank()) {
            "MainTypes"
        } else "${phrase}Types"
    }
    const val fileTypeScriptExtension = ".ts"
    const val layoutParamsFileName = "layoutparams.ts"
    const val indexFileName = "index.ts"
    private const val generatedPackageSimpleName = "Generated"
}