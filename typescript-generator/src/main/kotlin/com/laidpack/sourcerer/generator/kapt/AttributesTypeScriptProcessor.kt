package com.laidpack.sourcerer.generator.kapt

import com.google.auto.service.AutoService
import com.laidpack.generator.api.TypeScriptNameProvider
import com.laidpack.typescript.codegen.BaseTypeScriptProcessor
import com.laidpack.typescript.codegen.Nullability
import com.laidpack.typescript.codegen.TypeTransformer
import com.laidpack.typescript.codegen.moshi.ITargetType
import com.squareup.kotlinpoet.ClassName
import java.io.File
import javax.annotation.processing.Processor

@Suppress("unused")
@AutoService(Processor::class)
class AttributesTypeScriptProcessor : BaseTypeScriptProcessor() {

    override val constrainToCurrentModulePackage = true
    override val exportDefinitions = true
    override val inAmbientDefinitionFile = false

    override fun writeFile(outputDir: String, fileName: String, content: String) {
        val file = File(outputDir, fileName)
        AttributesFileWriter.writeImports(file, imports.joinToString("\n") + "\n")
        AttributesFileWriter.writeTypes(file, content)
    }

    override val customTransformers = listOf(
        TypeTransformer(
                { t ->
                    t.annotations.contains("$servicesApiPackageName.MultiFormatQualifier")
                },
                {t ->
                    val annotation = t.annotations["$servicesApiPackageName.MultiFormatQualifier"] as Map<String, String>
                    val types = mutableSetOf<String>()
                    var unknownFormat = false
                    val formatValueString = annotation["formats"] as String
                    val formats = formatValueString.substring(1, formatValueString.lastIndex)
                            .split(",")
                            .map { it.trim() }

                    loop@ for (format in formats) {
                        when {
                            !formatToType.containsKey(format) -> {
                                unknownFormat = true
                                break@loop
                            }
                            format == enumFormat -> {
                                val enumType = annotation["enumType"] as String
                                val enumClassName = ClassName.bestGuess(
                                        enumType.replace(".class", "")
                                )
                                types.add(enumClassName.simpleName)
                            }
                            format == flagsFormat -> {
                                val flagsType = annotation["flagsType"] as String
                                val flagsClassName = ClassName.bestGuess(
                                        flagsType.replace(".class", "")
                                )
                                types.add("${flagsClassName.simpleName}[]")
                            }
                            else -> types.add(formatToType[format] as String)
                        }
                    }
                    if (unknownFormat) {
                        "any"
                    } else types.joinToString(" | ")
                },
                Nullability.Null
        ),
        TypeTransformer(
                { t -> t.annotations.contains("$servicesApiPackageName.FlagsQualifier")},
                { t ->
                    val annotation = t.annotations["$servicesApiPackageName.FlagsQualifier"] as Map<String, String>
                    val flagsType = annotation["flagsType"] as String
                    val flagsClassName = ClassName.bestGuess(
                            flagsType.replace(".class", "")
                    )
                    "${flagsClassName.simpleName}[]"
                },
                Nullability.Null
        ),
        TypeTransformer({ t -> t.annotations.contains("$servicesApiPackageName.ColorQualifier")}, "string", Nullability.NoTransform),
        TypeTransformer({ t -> t.annotations.contains("$servicesApiPackageName.DimensionQualifier")}, "string", Nullability.NoTransform),
        TypeTransformer({ t -> t.annotations.contains("$servicesApiPackageName.ReferenceQualifier")}, "string", Nullability.NoTransform)

    )
    override val filePreProcessors = listOf(this::getImports)

    override val superTypeTransformer = {className: ClassName, currentModuleName: String ->
        val superTypeModuleName = TypeScriptNameProvider.getNamespace(className.packageName)
        if (superTypeModuleName != currentModuleName) {
            "$superTypeModuleName.${className.simpleName}"
        } else className.simpleName
    }

    private fun getImports(targetTypes: HashMap<String, ITargetType>, rootPackageNames: Set<String>, packageNames: Set<String>): String {
        if (importWithinModule) {
            getImportsFromWithModule(targetTypes, rootPackageNames)
        } else {
            getImportsExternallyFromModule(targetTypes, rootPackageNames)
        }
        return ""
    }
    private fun getImportsFromWithModule(targetTypes: HashMap<String, ITargetType>, rootPackageNames: Set<String>) {
        imports.add("import { ElementNode, element } from './element';")
        imports.add("import { LayoutParamAttributes } from './layoutparams';")
        val processedImports = mutableSetOf<String>()
        for (targetType in targetTypes.values) {
            for(superType in targetType.superTypes) {
                if (!rootPackageNames.contains(superType.className.packageName)
                        && rootPackageNames.all { !superType.className.packageName.startsWith("$it.") }) {
                    val dependentNamespace = TypeScriptNameProvider.getNamespace(superType.className.packageName)
                    if (!processedImports.contains(dependentNamespace)) {
                        val typeFileName = TypeScriptNameProvider.getAttributesFileName(superType.className.packageName)
                                .replace(TypeScriptNameProvider.fileTypeScriptExtension, "")
                        processedImports.add(dependentNamespace)
                        imports.add("import { $dependentNamespace } from './$typeFileName';")
                        //result += "/// <reference path='./$typeFileName' />\n"
                    }
                }
            }
        }
    }
    private fun getImportsExternallyFromModule(targetTypes: HashMap<String, ITargetType>, rootPackageNames: Set<String>) {
        val importItems = mutableSetOf(
                "ElementNode",
                "element",
                "LayoutParamAttributes"
        )
        val processedImports = mutableSetOf<String>()
        for (targetType in targetTypes.values) {
            for(superType in targetType.superTypes) {
                if (!rootPackageNames.contains(superType.className.packageName)
                        && rootPackageNames.all { !superType.className.packageName.startsWith("$it.") }) {
                    val dependentNamespace = TypeScriptNameProvider.getNamespace(superType.className.packageName)
                    if (!processedImports.contains(dependentNamespace)) {
                        processedImports.add(dependentNamespace)
                        importItems.add(dependentNamespace)
                    }
                }
            }
        }
        imports.add("import { ${importItems.joinToString(", ")} } from 'sourcerer-android';")
    }

    private val imports = mutableSetOf<String>()

    companion object {
        private const val servicesPackageName = "com.laidpack.sourcerer.services"
        private const val servicesApiPackageName = "$servicesPackageName.api"
        private const val enumFormat = "$servicesApiPackageName.Format.Enum"
        private const val flagsFormat = "$servicesApiPackageName.Format.Flags"
        private val formatToType = mapOf(
                "$servicesApiPackageName.Format.Boolean" to "boolean",
                "$servicesApiPackageName.Format.Color" to "string",
                "$servicesApiPackageName.Format.String" to "string",
                "$servicesApiPackageName.Format.Dimension" to "string",
                "$servicesApiPackageName.Format.Reference" to "string",
                flagsFormat to "string",
                enumFormat to "number",
                "$servicesApiPackageName.Format.Float" to "number",
                "$servicesApiPackageName.Format.Integer" to "number",
                "$servicesApiPackageName.Format.Fraction" to "number",
                "$servicesApiPackageName.Format.Unspecified" to "number"

        )
    }
}