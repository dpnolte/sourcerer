package com.laidpack.sourcerer.generator.kapt

import com.google.auto.service.AutoService
import com.laidpack.generator.api.*
import com.laidpack.typescript.codegen.BaseTypeScriptProcessor
import com.squareup.kotlinpoet.ClassName
import me.eugeniomarletti.kotlin.processing.KotlinAbstractProcessor
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@Suppress("unused")
@AutoService(Processor::class)
class ElementTypeScriptProcessor : KotlinAbstractProcessor() {
    private lateinit var attributeTypesFileName: String
    private lateinit var outputDir: String
    private lateinit var indent: String
    private lateinit var namespace: String
    private var importWithinModule = false

    override fun getSupportedAnnotationTypes() = setOf(viewAnnotation.canonicalName, viewGroupAnnotation.canonicalName)

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latest()

    override fun getSupportedOptions() = setOf(
            BaseTypeScriptProcessor.OPTION_OUTPUTDIR,
            BaseTypeScriptProcessor.OPTION_FILENAME,
            BaseTypeScriptProcessor.OPTION_MODULE,
            BaseTypeScriptProcessor.OPTION_INDENT,
            BaseTypeScriptProcessor.OPTION_IMPORT_WITHIN_MODULE
    )

    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
        attributeTypesFileName = processingEnv.options[BaseTypeScriptProcessor.OPTION_FILENAME]
            ?: throw IllegalStateException("Missing required kapt argument 'filename'. Argument key: ${BaseTypeScriptProcessor.OPTION_FILENAME}")
        outputDir = processingEnv.options[BaseTypeScriptProcessor.OPTION_OUTPUTDIR]
            ?: throw IllegalStateException("Missing required kapt argument 'outputdir'. Argument key: ${BaseTypeScriptProcessor.OPTION_FILENAME}")
        namespace = processingEnv.options[BaseTypeScriptProcessor.OPTION_NAMESPACE]
                ?: throw IllegalStateException("Missing required kapt argument 'namespace'. Argument key: ${BaseTypeScriptProcessor.OPTION_NAMESPACE}")
        indent = processingEnv.options[BaseTypeScriptProcessor.OPTION_INDENT]
                ?: "  "
        importWithinModule = processingEnv.options[BaseTypeScriptProcessor.OPTION_IMPORT_WITHIN_MODULE]?.toBoolean()
                ?: false
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
            layoutParamAttributeTypes.add("$namespace.${attributesClassName.simpleName}")
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
            appendToFile(elements) && extendExportedElementTypeInIndexFile()
        } else true
    }

    private fun getViewElement(elementType: String, attributesClassName: ClassName): String {
        val elementName = elementType.capitalize().split(".").joinToString("") { it.capitalize() }
        val attributesSimpleName = "$namespace.${attributesClassName.simpleName}"
        return """
            |export const $elementName = (
            |${indent}attributes?: $attributesSimpleName & $layoutParamAttributesSimpleName,
            |${indent}children?: Array<ElementNode<unknown, $layoutParamAttributesSimpleName>>
            |): ElementNode<$attributesSimpleName, $layoutParamAttributesSimpleName> => {
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

    private fun appendToFile(elements: List<String>): Boolean {
        if (!outputDir.endsWith(File.separator))
            outputDir += File.separator

        val path = Paths.get(outputDir)
        if (!Files.exists(path) || !Files.isDirectory(path)) {
            messager.printMessage(Diagnostic.Kind.ERROR, "Path '$outputDir' doesn't exist or is not a directory")
            return false
        }

        val file = File(outputDir, attributeTypesFileName)
        AttributesFileWriter.writeElements(file, elements.joinToString("\n") + "\n")
        messager.printMessage(Diagnostic.Kind.NOTE, "TypeScript elements saved at $file")
        return true
    }

    private fun extendLayoutParamsTypeInIndexFile(
            attributeTypes: List<String>,
            attributeFiles: Set<String>
    ): Boolean {
        if (importWithinModule) {
            val path = Paths.get(outputDir).resolve(TypeScriptNameProvider.layoutParamsFileName)
            if (!Files.exists(path)) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Path '$path' doesn't exist")
                return false
            }

            val file = path.toFile()
            var contents = file.readText()
            for (attributeFile in attributeFiles) {
                val fileNameWithoutExtension = attributeFile
                        .replace(TypeScriptNameProvider.fileTypeScriptExtension, "")
                val importStatement = "import { $namespace } from './$fileNameWithoutExtension';\n"
                //val referencePathStatement = "/// <reference path='./$attributeFile' />\n"
                if (!contents.contains(importStatement)) {
                    contents = importStatement + contents
                }
            }
            val match = layoutAttributesTypeRegex.find(contents)
            if (match == null) {
                messager.printMessage(Diagnostic.Kind.NOTE, "$outputDir${TypeScriptNameProvider.layoutParamsFileName} does not contain type LayoutParamAttributes")
                return false
            }
            var types = match.groupValues[1]
            for (attributeType in attributeTypes) {
                if (!types.contains(attributeType)) {
                    types += "\n${indent + indent}| $attributeType"
                }
            }
            contents = layoutAttributesTypeRegex.replace(
                    contents,
                    "\nexport type LayoutParamAttributes = $types;\n"
            )
            file.writeText(contents)
        }
        return true
    }

    private fun extendExportedElementTypeInIndexFile(): Boolean {
        if (importWithinModule) {
            val path = Paths.get(outputDir).resolve(TypeScriptNameProvider.indexFileName)
            if (!Files.exists(path)) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Path '$path' doesn't exist")
                return false
            }

            val file = path.toFile()
            var contents = file.readText()
            val fileNameWithoutExtension = attributeTypesFileName
                    .replace(TypeScriptNameProvider.fileTypeScriptExtension, "")
            val exportStatement = "export * from './$fileNameWithoutExtension';\n"
            if (!contents.contains(exportStatement)) {
                contents = exportStatement + contents
            }
            file.writeText(contents)
        }
        return true
    }

    companion object {
        val viewGroupAnnotation = ViewGroupElement::class.java
        val viewAnnotation = ViewElement::class.java
        val layoutParamsAnnotation = LayoutParamsElement::class.java

        private const val layoutParamAttributesSimpleName = "LayoutParamAttributes"
        private val layoutAttributesTypeRegex = Regex("\\s*export type LayoutParamAttributes = (.+)?;\\s*", RegexOption.DOT_MATCHES_ALL)
    }

}