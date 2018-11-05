package com.laidpack.sourcerer.generator.kapt

import com.google.auto.service.AutoService
import com.laidpack.generator.api.LayoutParamsElement
import com.laidpack.generator.api.TypeScriptFileNameProvider
import com.laidpack.generator.api.ViewElement
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.typescript.codegen.BaseTypeScriptProcessor
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeName
import me.eugeniomarletti.kotlin.metadata.kaptGeneratedOption
import me.eugeniomarletti.kotlin.processing.KotlinAbstractProcessor
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDateTime
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
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
    private val typeScriptImports = mutableSetOf<String>()

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
        elementsFileName = TypeScriptFileNameProvider.getElementsFileName(attributeTypesFileName)
        outputDir = processingEnv.options[BaseTypeScriptProcessor.OPTION_OUTPUTDIR]
            ?: throw IllegalStateException("Missing required kapt argument 'outputdir'. Argument key: ${BaseTypeScriptProcessor.OPTION_FILENAME}")
        moduleName = processingEnv.options[BaseTypeScriptProcessor.OPTION_MODULE]
                ?: throw IllegalStateException("Missing required kapt argument 'module'. Argument key: ${BaseTypeScriptProcessor.OPTION_MODULE}")
        indent = processingEnv.options[BaseTypeScriptProcessor.OPTION_INDENT]
                ?: "  "
        typeScriptImports.add(attributeTypesFileName.replace(TypeScriptFileNameProvider.fileExtension, ""))
    }

    override fun process(annotations: Set<TypeElement>, roundEnv: RoundEnvironment): Boolean {
        val definitions = mutableListOf<String>()
        for (element in roundEnv.getElementsAnnotatedWith(viewGroupAnnotation)) {
            val viewGroupElementAnnotation = element.getAnnotation(viewGroupAnnotation)
            val memberTypeNames = AnnotationUtils.getTypeNamesFromFirstAnnotationMirrorOfType(
                    element, viewGroupAnnotation
            )
            definitions.add(getViewGroupElementDefinition(viewGroupElementAnnotation, memberTypeNames))
        }
        for (element in roundEnv.getElementsAnnotatedWith(viewAnnotation)) {
            val viewElementAnnotation = element.getAnnotation(viewAnnotation)
            val memberTypeNames = AnnotationUtils.getTypeNamesFromFirstAnnotationMirrorOfType(
                    element, viewAnnotation
            )
            definitions.add(getViewElementDefinition(viewElementAnnotation, memberTypeNames))
        }

        return if (definitions.isNotEmpty()) {
            writeFile(definitions)
        } else true
    }

    private fun getViewGroupElementDefinition(viewGroupElement: ViewGroupElement, memberTypeNames: Map<String, TypeName>): String {
        val attributesClassName = memberTypeNames["attributesClazz"] as ClassName
        val attributesSimpleName = attributesClassName.simpleName
        val layoutParamsClassName = memberTypeNames["layoutParamAttributesClazz"] as ClassName
        val layoutParamAttributesSimpleName = layoutParamsClassName.simpleName
        val lpFileName = TypeScriptFileNameProvider.getAttributesFileName(layoutParamsClassName.packageName).replace(TypeScriptFileNameProvider.fileExtension, "")
        if (!typeScriptImports.contains(lpFileName)) {
            typeScriptImports.add(lpFileName)
        }
        val simpleName = attributesSimpleName.replace("Attributes", "")
        return """
            |${indent}interface Normalized${simpleName}Element<
            |${indent+indent}LayoutAttributes={}
            |$indent> extends NormalizedViewGroupBase<$attributesSimpleName, LayoutAttributes> {
            |${indent+indent}type: "${viewGroupElement.elementType}";
            |$indent}
            |${indent}interface Denormalized${simpleName}Element<
            |${indent+indent}LayoutAttributes={},
            |${indent+indent}ChildrenViewAttributes={}
            |$indent> extends DenormalizedViewGroupBase<$attributesSimpleName, LayoutAttributes, ChildrenViewAttributes, $layoutParamAttributesSimpleName> {
            |${indent+indent}type: "${viewGroupElement.elementType}";
            |$indent}
        """.trimMargin()
    }

    private fun getViewElementDefinition(viewElement: ViewElement, memberTypeNames: Map<String, TypeName>): String {
        val attributesClassName = memberTypeNames["attributesClazz"] as ClassName
        val attributesSimpleName = attributesClassName.simpleName
        val simpleName = attributesSimpleName.replace("Attributes", "")
        return """
            |${indent}interface Normalized${simpleName}Element<LayoutAttributes={}> extends ElementBase<$attributesSimpleName, LayoutAttributes> {
            |${indent+indent}type: "${viewElement.elementType}";
            |$indent}
            |${indent}interface Denormalized${simpleName}Element<LayoutAttributes={}> extends ElementBase<$attributesSimpleName, LayoutAttributes> {
            |${indent+indent}type: "${viewElement.elementType}";
            |$indent}
        """.trimMargin()
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
        file.createNewFile() // overwrite any existing file
        file.writeText(generateFileContents(definitions))
        messager.printMessage(Diagnostic.Kind.OTHER, "TypeScript definitions saved at $outputDir$elementsFileName")
        return true
    }

    private fun generateFileContents(definitions: List<String>): String {
        val timestamp = "/* generated @ ${LocalDateTime.now()} */\n"
        val imports = "import \"./base.elements\";\n" + typeScriptImports.joinToString { "import \"./$it\";\n" }
        val moduleStart = "declare module \"$moduleName\" {\n"
        val moduleContent = definitions.joinToString("\n")
        val moduleEnd = "}\n"

        return timestamp + imports + moduleStart + moduleContent + moduleEnd
    }

    companion object {
        val viewGroupAnnotation = ViewGroupElement::class.java
        val viewAnnotation = ViewElement::class.java
        val layoutParamsAnnotation = LayoutParamsElement::class.java
    }

}