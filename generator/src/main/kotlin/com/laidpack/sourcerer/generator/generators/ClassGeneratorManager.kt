package com.laidpack.sourcerer.generator.generators

import android.view.ViewGroup
import com.laidpack.sourcerer.generator.SourcererResult
import com.laidpack.sourcerer.generator.index.ClassCategory
import com.laidpack.sourcerer.generator.index.LayoutParamsConstructorExpression
import com.laidpack.sourcerer.generator.index.ViewConstructorExpression
import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.WildcardTypeName
import java.io.File

class ClassGeneratorManager(
        private val targetDir: File,
        private val targetPackageName: String,
        targetPackageNameSuperClass: String?,
        private val result: SourcererResult,
        superClassResults: List<SourcererResult>,
        private val minSdkVersion: Int,
        reservedElementTypes: Set<String> = setOf()
) {
    // view class name with projected * if necessary
    private val viewClassName = if (result.numberOfTypeVariables > 0) {
        val stars = mutableListOf<TypeName>()
        for(i in 1..result.numberOfTypeVariables) {
            stars.add(WildcardTypeName.STAR)
        }
        result.targetClassName.parameterizedBy(*stars.toTypedArray())
    } else result.targetClassName

    // attributes class name
    private val attributesClassName = AttributesGenerator.getAttributesClassName(
            targetPackageName,
            result.targetClassName,
            result.classCategory
    )
    private val attributesSuperClassName = if (result.superClassName != null) {
        AttributesGenerator.getAttributesClassName(
                targetPackageNameSuperClass as String,
                result.superClassName,
                result.classCategory
        )
    } else null
    val attributesFileName = attributesClassName.simpleName

    // factory class name
    private val factoryClassName = if (result.classCategory == ClassCategory.View) {
        ViewFactoryGenerator.getFactoryClassName(targetPackageName, result.targetClassName)
    } else LayoutParamsFactoryGenerator.getFactoryClassName(targetPackageName, result.targetClassName)
    private val superFactoryClassName = when {
        result.superClassName != null && result.classCategory == ClassCategory.View -> {
            ViewFactoryGenerator.getFactoryClassName(targetPackageNameSuperClass as String, result.superClassName)
        }
        result.superClassName != null && result.classCategory == ClassCategory.LayoutParams -> {
            LayoutParamsFactoryGenerator.getFactoryClassName(targetPackageNameSuperClass as String, result.superClassName)
        }
        else -> null
    }
    val factoryFileName = factoryClassName.simpleName

    // layout element types
    private val splitPackageName = targetPackageName.split(".")
    private val proposedElementType = result.targetClassName.simpleName.decapitalize()
    val elementType = when {
        result.classCategory == ClassCategory.View && !reservedElementTypes.contains(proposedElementType)
            -> result.targetClassName.simpleName.decapitalize()
        result.classCategory == ClassCategory.View && !reservedElementTypes.contains("${splitPackageName[splitPackageName.lastIndex]}.$proposedElementType")
            -> "${splitPackageName[splitPackageName.lastIndex]}.$proposedElementType"
        result.classCategory == ClassCategory.View && !reservedElementTypes.contains("$targetPackageName.$proposedElementType")
            -> "$targetPackageName.$proposedElementType"
        else -> result.targetClassName.canonicalName
    }
    private val associatedLayoutElementType = if (result.isViewGroup) {
        result.defaultLayoutParamsClassName?.canonicalName ?: viewGroupLayoutParamsElementType
    } else null

    // attributes adapter class name
    private val adapterClassName = ClassName(targetPackageName, attributesClassName.simpleName + "JsonAdapter")

    // wrappers for bootstrapper creation
    val factoryWrapper = FactoryWrapper(
            factoryClassName,
            viewClassName,
            attributesClassName,
            result.isFinal
    )
    val adapterRegistrationWrapper = AdapterRegistrationWrapper(
            result.targetClassName,
            adapterClassName,
            elementType
    )
    val viewGroupAndLayoutParamsWrapper = if (associatedLayoutElementType != null) {
        AssociatedViewGroupAndLayoutParamsWrapper(
                elementType,
                associatedLayoutElementType
        )
    } else null

    val targetPathAsString by lazy {
        val splitDir = targetDir.toString().split("/sourcerer")
        ".${splitDir[splitDir.lastIndex]}${File.separator}${SourcererEnvironment.generatedPackagePathAsString}"
    }

    private val superClassAttributes = superClassResults.associate {
        Pair (
                AttributesGenerator.getAttributesClassName(
                        it.targetPackageName,
                        it.targetClassName,
                        it.classCategory
                ),
                it.attributes
        )
    }
    fun generateAttributeFile()  {
        val file = AttributesGenerator(
                targetPackageName,
                attributesClassName,
                attributesSuperClassName,
                result.attributes,
                superClassAttributes
        ).generateFile()
        file.writeTo(targetDir)
    }


    fun generateFactoryFile() {
        if (result.classCategory == ClassCategory.View) {
            val fileSpec = ViewFactoryGenerator(
                    targetPackageName,
                    result.targetClassName,
                    viewClassName,
                    factoryClassName,
                    superFactoryClassName,
                    attributesClassName,
                    result.constructorExpression as ViewConstructorExpression,
                    result.codeBlocks,
                    result.minimumApiLevel,
                    minSdkVersion,
                    result.isFinal,
                    elementType
            ).generateFile()
            fileSpec.writeTo(targetDir)
        } else {
            val fileSpec = LayoutParamsFactoryGenerator(
                    targetPackageName,
                    result.targetClassName,
                    viewClassName,
                    factoryClassName,
                    superFactoryClassName,
                    attributesClassName,
                    result.constructorExpression as LayoutParamsConstructorExpression,
                    result.codeBlocks,
                    result.minimumApiLevel,
                    minSdkVersion,
                    result.isFinal,
                    elementType
            ).generateFile()
            fileSpec.writeTo(targetDir)
        }
    }

    companion object {
        private val viewGroupLayoutParamsElementType = ViewGroup.LayoutParams::class.java.canonicalName
    }
}