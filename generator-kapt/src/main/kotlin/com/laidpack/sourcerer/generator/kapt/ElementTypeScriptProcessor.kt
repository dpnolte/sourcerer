package com.laidpack.sourcerer.generator.kapt

import com.google.auto.service.AutoService
import com.laidpack.generator.api.LayoutParamsElement
import com.laidpack.generator.api.TypeScriptNameProvider
import com.laidpack.generator.api.ViewElement
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.typescript.codegen.BaseTypeScriptProcessor
import com.squareup.kotlinpoet.ClassName
import me.eugeniomarletti.kotlin.processing.KotlinAbstractProcessor
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDateTime
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@Suppress("unused")
@AutoService(Processor::class)
class ElementTypeScriptProcessor : KotlinAbstractProcessor() {
    private lateinit var attributeTypesFileName: String
    private lateinit var elementsFileName: String
    private lateinit var outputDir: String
    private lateinit var indent: String
    private lateinit var moduleName: String

    override fun getSupportedAnnotationTypes() = setOf(viewAnnotation.canonicalName, viewGroupAnnotation.canonicalName)

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latest()

    override fun getSupportedOptions() = setOf(
            BaseTypeScriptProcessor.OPTION_OUTPUTDIR,
            BaseTypeScriptProcessor.OPTION_FILENAME,
            BaseTypeScriptProcessor.OPTION_MODULE,
            BaseTypeScriptProcessor.OPTION_INDENT
    )

    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
        attributeTypesFileName = processingEnv.options[BaseTypeScriptProcessor.OPTION_FILENAME]
            ?: throw IllegalStateException("Missing required kapt argument 'filename'. Argument key: ${BaseTypeScriptProcessor.OPTION_FILENAME}")
        elementsFileName = TypeScriptNameProvider.getElementsFileName(attributeTypesFileName)
        outputDir = processingEnv.options[BaseTypeScriptProcessor.OPTION_OUTPUTDIR]
            ?: throw IllegalStateException("Missing required kapt argument 'outputdir'. Argument key: ${BaseTypeScriptProcessor.OPTION_FILENAME}")
        moduleName = processingEnv.options[BaseTypeScriptProcessor.OPTION_NAMESPACE]
                ?: throw IllegalStateException("Missing required kapt argument 'namespace'. Argument key: ${BaseTypeScriptProcessor.OPTION_NAMESPACE}")
        indent = processingEnv.options[BaseTypeScriptProcessor.OPTION_INDENT]
                ?: "  "
    }

    override fun process(annotations: Set<TypeElement>, roundEnv: RoundEnvironment): Boolean {
        return processViewAttributes(roundEnv)
            && processLayoutParamAttributes(roundEnv)
    }

    private fun processLayoutParamAttributes(roundEnv: RoundEnvironment): Boolean {
        val layoutParamAttributeTypes = mutableListOf<String>()
        val layoutAttributeFiles = mutableSetOf<String>()
        for (element in roundEnv.getElementsAnnotatedWith(layoutParamsAnnotation)) {
            val attributesClassName = getAttributeClassName(element, layoutParamsAnnotation)
            val attributeFileName = TypeScriptNameProvider.getAttributesFileName(attributesClassName.packageName)
            if (!layoutAttributeFiles.contains(attributeFileName)) {
                layoutAttributeFiles.add(attributeFileName)
            }
            layoutParamAttributeTypes.add("$moduleName.${attributesClassName.simpleName}")
        }
        return if (layoutParamAttributeTypes.isNotEmpty()) {
            extendLayoutParamsTypeInIndexFile(layoutParamAttributeTypes, layoutAttributeFiles)
        } else true
    }

    private fun processViewAttributes(roundEnv: RoundEnvironment): Boolean {
        val elements = mutableListOf<String>()
        for (element in roundEnv.getElementsAnnotatedWith(viewGroupAnnotation)) {
            val viewGroupElementAnnotation = element.getAnnotation(viewGroupAnnotation)
            val attributesClassName = getAttributeClassName(element, viewGroupAnnotation)
            elements.add(getViewElement(viewGroupElementAnnotation.elementType, attributesClassName))
        }
        for (element in roundEnv.getElementsAnnotatedWith(viewAnnotation)) {
            val viewGroupElementAnnotation = element.getAnnotation(viewAnnotation)
            val attributesClassName = getAttributeClassName(element, viewAnnotation)
            elements.add(getViewElement(viewGroupElementAnnotation.elementType, attributesClassName))
        }
        return if (elements.isNotEmpty()) {
            writeFile(elements) && extendExportedElementTypeInIndexFile()
        } else true
    }

    private fun getViewElement(elementType: String, attributesClassName: ClassName): String {
        val elementName = elementType.capitalize().split(".").joinToString("") { it.capitalize() }
        val attributesSimpleName = "$moduleName.${attributesClassName.simpleName}"
        return """
            |export const $elementName = (
            |${indent}attributes?: $attributesSimpleName & $layoutParamAttributesSimpleName,
            |${indent}children?: Array<ElementTypes.ElementNode<unknown, $layoutParamAttributesSimpleName>>
            |): ElementTypes.ElementNode<$attributesSimpleName, $layoutParamAttributesSimpleName> => {
            |${indent}return element('$elementType', attributes, children);
            |};
        """.trimMargin()
    }

    private fun getAttributeClassName(element: Element, annotationType: Class<*>): ClassName {
        val memberTypeNames = AnnotationUtils.getTypeNamesFromFirstAnnotationMirrorOfType(
                element, annotationType
        )
        return memberTypeNames["attributesClazz"] as ClassName
    }

    private fun writeFile(definitions: List<String>): Boolean {
        if (!outputDir.endsWith(File.separator))
            outputDir += File.separator

        val path = Paths.get(outputDir)
        if (!Files.exists(path) || !Files.isDirectory(path)) {
            messager.printMessage(Diagnostic.Kind.ERROR, "Path '$outputDir' doesn't exist or is not a directory")
            return false
        }

        val file = File(outputDir, elementsFileName)
        file.createNewFile()
        file.writeText(generateFileContents(definitions))
        messager.printMessage(Diagnostic.Kind.NOTE, "TypeScript definitions saved at $outputDir$elementsFileName")
        return true
    }

    private fun generateFileContents(elements: List<String>): String {
        val timestamp = "/* generated @ ${LocalDateTime.now()} */\n"
        val imports = "import { element } from './element';\n" +
                "/// <reference path='./element.types.d.ts' />\n" +
                "/// <reference path='./layoutparams.types.d.ts' />\n" +
                "/// <reference path='./$attributeTypesFileName' />\n\n"
        val moduleContent = elements.joinToString("\n")

        return timestamp + imports + moduleContent + "\n"
    }

    private fun extendLayoutParamsTypeInIndexFile(
            attributeTypes: List<String>,
            attributeFiles: Set<String>
    ): Boolean {
        val path = Paths.get(outputDir).resolve(TypeScriptNameProvider.layoutParamsDefinitionsFileName)
        if (!Files.exists(path)) {
            messager.printMessage(Diagnostic.Kind.ERROR, "Path '$path' doesn't exist")
            return false
        }

        val file = path.toFile()
        var contents = file.readText()
        for(attributeFile in attributeFiles) {
            //val importStatement = "import * as $moduleName from \"$moduleName\";\n"
            val referencePathStatement = "/// <reference path='./$attributeFile' />\n"
            if (!contents.contains(referencePathStatement)) {
                contents = referencePathStatement + contents
            }
        }
        val match = layoutAttributesTypeRegex.find(contents)
        if (match == null) {
            messager.printMessage(Diagnostic.Kind.NOTE, "$outputDir${TypeScriptNameProvider.layoutParamsDefinitionsFileName} does not contain type LayoutParamAttributes")
            return false
        }
        var types = match.groupValues[1]
        for (attributeType in attributeTypes) {
            if (!types.contains(attributeType)) {
                types += "\n${indent+indent}| $attributeType"
            }
        }
        contents = layoutAttributesTypeRegex.replace(
                contents,
                "\n${indent}export type LayoutParamAttributes = $types;\n"
        )
        file.writeText(contents)
        return true
    }

    private fun extendExportedElementTypeInIndexFile(): Boolean {
        val path = Paths.get(outputDir).resolve(TypeScriptNameProvider.indexFileName)
        if (!Files.exists(path)) {
            messager.printMessage(Diagnostic.Kind.ERROR, "Path '$path' doesn't exist")
            return false
        }

        val file = path.toFile()
        var contents = file.readText()
        val elementsFileNameWithoutExtension = elementsFileName.replace(TypeScriptNameProvider.fileTypeScriptExtension, "")
        val exportStatement = "export * from \"./$elementsFileNameWithoutExtension\";\n"
        if (!contents.contains(exportStatement)) {
            contents = exportStatement + contents
        }
        file.writeText(contents)
        return true
    }

    companion object {
        val viewGroupAnnotation = ViewGroupElement::class.java
        val viewAnnotation = ViewElement::class.java
        val layoutParamsAnnotation = LayoutParamsElement::class.java

        private const val layoutParamAttributesSimpleName = "LayoutParamsTypes.LayoutParamAttributes"
        private val layoutAttributesTypeRegex = Regex("\\s*export type LayoutParamAttributes = (.+)?;\\s*", RegexOption.DOT_MATCHES_ALL)
    }

}