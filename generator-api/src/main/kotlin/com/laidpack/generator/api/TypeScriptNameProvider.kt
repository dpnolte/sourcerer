package com.laidpack.generator.api

object TypeScriptNameProvider {
    fun getAttributesFileName(packageName: String): String {
        val fileBase = getModuleName(packageName).toLowerCase().replace("types", "")

        return "$fileBase.types$fileTypeDefinitionsExtension"
    }
    fun getElementsFileName(attributesFileName: String): String {
        return attributesFileName.replace(".types$fileTypeDefinitionsExtension", fileTypeScriptExtension)
    }
    fun getModuleName(packageName: String): String {
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
    const val fileTypeDefinitionsExtension = ".d.ts"
    const val fileTypeScriptExtension = ".ts"
    const val layoutParamsDefinitionsFileName = "layoutparams.types.d.ts"
    const val indexFileName = "index.ts"
    private const val generatedPackageSimpleName = "Generated"
}