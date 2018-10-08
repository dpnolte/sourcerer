package com.laidpack.sourcerer.generator.generators

import android.content.Context
import android.os.Build
import com.laidpack.sourcerer.generator.target.CodeBlock
import com.laidpack.sourcerer.generator.peeker.ClassCategory
import com.laidpack.sourcerer.generator.generators.delegates.MultiAttributesAndMultiSettersGenerator
import com.laidpack.sourcerer.generator.generators.delegates.MultiAttributesAndSingleSetterGenerator
import com.laidpack.sourcerer.generator.generators.delegates.SingleAttributeAndMultiSettersGenerator
import com.laidpack.sourcerer.generator.generators.delegates.SingleAttributeAndSingleSetterGenerator
import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import kotlin.reflect.KClass

abstract class BaseFactoryGenerator(
        protected val targetClassName: ClassName,
        protected val superClassName: ClassName?,
        fallbackClassName: ClassName?,
        private val baseClassType: KClass<*>,
        private val codeBlocks: List<CodeBlock>,
        classCategory: ClassCategory,
        numberOfTypeVariables: Int,
        private val minimumApiLevel: Int,
        private val minSdkVersion: Int
) {
    private val hasSuperClass = superClassName != null
    private val attributesClassName = AttributesGenerator.getAttributesClassName(targetClassName, classCategory)
    private val attributesTypeVariable = TypeVariableName("TAttributes", attributesClassName)
    protected val viewClassTypeName = if (numberOfTypeVariables > 0) {
        var stars = mutableListOf<TypeName>()
        for(i in 1..numberOfTypeVariables) {
            stars.add(WildcardTypeName.STAR)
        }
        targetClassName.parameterizedBy(*stars.toTypedArray())
    } else targetClassName
    private val layoutTypeClassName
        get() = ClassName("java.lang", "Class")
            .parameterizedBy(layoutTypeVariable)
    private val attributesTypeClassName = ClassName("java.lang", "Class")
            .parameterizedBy(attributesTypeVariable)
    private val elementName = if (classCategory == ClassCategory.View) {
        targetClassName.simpleName.decapitalize()
    } else targetClassName.canonicalName
    private val fallBackElementName = when {
        fallbackClassName != null && classCategory == ClassCategory.View -> "\"${fallbackClassName.simpleName}\""
        fallbackClassName != null -> "\"${fallbackClassName.canonicalName}\""
        else -> "null"
    }



    private val factoryClassName
        get() = getFactoryClassName(targetClassName)
    private val baseFactoryClassName : TypeName
        get() {
            return if (superClassName != null) {
                getFactoryClassName(superClassName).parameterizedBy(
                        layoutTypeVariable,
                        attributesTypeVariable
                )
            } else {
                rootFactoryClassName.parameterizedBy(
                        layoutTypeVariable,
                        attributesTypeVariable
                )
            }
        }

    abstract fun getFactoryClassName(originalClassName: ClassName): ClassName
    abstract val rootFactoryClassName: ClassName
    abstract val layoutTypeVariable: TypeVariableName
    abstract val layoutParam : ParameterSpec
    abstract val layoutVariableName: String

    protected val attributesParam = ParameterSpec.builder("attributes", attributesTypeVariable).build()
    protected val contextParam = ParameterSpec.builder("context", Context::class).build()
    private val resolvedLayoutVariableName : String
        get() = if (targetClassName.canonicalName != baseClassType.java.canonicalName) layoutVariableName else layoutParam.name

    fun generateFile(): FileSpec {

        val file = FileSpec.builder(factoryClassName.packageName, factoryClassName.simpleName)
        file.addType(
                getClassSpec()
                .build()
        )
        return file.build()
    }

    open fun getClassSpec(): TypeSpec.Builder {
        return TypeSpec.classBuilder(factoryClassName)
                .addModifiers(KModifier.OPEN)
                .primaryConstructor(getPrimaryConstructor())
                .addSuperclassConstructorParameter("%N", "instanceType")
                .addSuperclassConstructorParameter("%N", "attributesType")
                .superclass(this.baseFactoryClassName)
                .addTypeVariables(listOf(layoutTypeVariable, attributesTypeVariable))
                .addProperty(
                        PropertySpec.builder(
                                "elementName",
                                String::class.asTypeName(),
                                KModifier.OVERRIDE
                        ).initializer("%S", elementName)
                                .build()
                )
                .addProperty(
                        PropertySpec.builder(
                                "fallBackElementName",
                                String::class.asTypeName().asNullable(),
                                KModifier.OVERRIDE
                        ).initializer("%L", fallBackElementName)
                                .build()
                )
                .addProperty(
                        PropertySpec.builder(
                                "minimumApiLevel",
                                Int::class.asTypeName(),
                                KModifier.OVERRIDE
                        ).initializer("%L", minimumApiLevel)
                                .build()
                )
                .addFunction(generateCreateInstanceFunc())
                .addInitFuncIfRequired()
                .addType(getCompanionSpec())
    }

    open fun getCompanionSpec(): TypeSpec {
        val invokeFunc = FunSpec.builder("invoke")
                .addModifiers(KModifier.INLINE, KModifier.OPERATOR)
                .addTypeVariables(listOf(
                        layoutTypeVariable.reified(true),
                        attributesTypeVariable.reified(true)
                ))
                .addStatement(
                        "return %T(%T::class.java, %T::class.java)",
                        factoryClassName,
                        layoutTypeVariable,
                        attributesTypeVariable
                )
                .build()
        val initBlock = com.squareup.kotlinpoet.CodeBlock.Builder()
                .addStatement(
                        "%T.addFactory(%T<%T, %T>())",
                        inflaterComponentClassName,
                        factoryClassName,
                        viewClassTypeName,
                        attributesClassName
                )

        return TypeSpec.companionObjectBuilder()
                .addFunction(invokeFunc)
                .addInitializerBlock(initBlock.build())
                .build()
    }

    open fun getPrimaryConstructor(): FunSpec {
        return FunSpec.constructorBuilder()
                .addParameter("instanceType", layoutTypeClassName)
                .addParameter("attributesType", attributesTypeClassName)
                .build()
    }

    abstract fun generateCreateInstanceFunc(): FunSpec

    protected fun FunSpec.Builder.wrapInMininumApiLevelCheckIfNeeded(
            minApiLevel: Int,
            ifStatements: FunSpec.Builder.() -> Unit,
            elseStatements: (FunSpec.Builder.() -> Unit)? = null
    ) : FunSpec.Builder {
        if (minApiLevel > minSdkVersion) {
            this.beginControlFlow("if (%T.VERSION.SDK_INT >= %L)", Build::class.asTypeName(), minApiLevel)
        }
        this.ifStatements()
        if (minApiLevel > minSdkVersion) {
            this.endControlFlow()
            if (elseStatements != null) {
                this.beginControlFlow("else")
                this.elseStatements()
                this.endControlFlow()
            }
        }
        return this
    }
    private fun TypeSpec.Builder.addInitFuncIfRequired() : TypeSpec.Builder {
        if (codeBlocks.isNotEmpty() || !hasSuperClass) {
            val funSpec = FunSpec.builder("init")
                    .addModifiers(KModifier.OVERRIDE)
                    .addParameter(layoutParam)
                    .addParameter(contextParam)
                    .addParameter(attributesParam)
                    .addSuperInitIfRequired()

            if (codeBlocks.isNotEmpty()) {
                funSpec.beginControlFlow("%N.%T", layoutParam.name, initTypeName)
                        .addInitializeAttributeCodeBlocks()
                        .endControlFlow()
            }

            this.addFunction(funSpec.build())
        }
        return this
    }

    private fun FunSpec.Builder.addLocalVar(): FunSpec.Builder {
        if (targetClassName.canonicalName != baseClassType.java.canonicalName) {
            this.addStatement("val %N = %N as %T", layoutVariableName, layoutParam, viewClassTypeName)
        }
        return this
    }

    private fun FunSpec.Builder.addSuperInitIfRequired(): FunSpec.Builder {
        if (hasSuperClass) {
            this.addStatement("super.init(%N, %N, %N)", layoutParam, contextParam, attributesParam)
        }
        return this
    }

    private fun FunSpec.Builder.addInitializeAttributeCodeBlocks(): FunSpec.Builder {
        val codeBlocksPerMinimumApiLevel = codeBlocks.groupBy {
            it.minimumApiLevel
        }.toSortedMap()
        codeBlocksPerMinimumApiLevel.forEach {
            val codeBlockMinimumApiLevel = it.key
            val codeBlocks = it.value
            this.wrapInMininumApiLevelCheckIfNeeded(codeBlockMinimumApiLevel, {
                codeBlocks.forEach { codeBlock ->
                    when {
                        codeBlock.attributes.size == 1 && codeBlock.setters.size == 1 -> {
                            val delegate = SingleAttributeAndSingleSetterGenerator(attributesParam, contextParam, codeBlock)
                            delegate.addCodeBlockToBuilder(this)
                        }
                        codeBlock.attributes.size == 1 && codeBlock.setters.size > 1 -> {
                            val delegate = SingleAttributeAndMultiSettersGenerator(attributesParam, contextParam, codeBlock)
                            delegate.addCodeBlockToBuilder(this)
                        }
                        codeBlock.attributes.size > 1 && codeBlock.setters.size == 1 -> {
                            val delegate = MultiAttributesAndSingleSetterGenerator(attributesParam, contextParam, codeBlock)
                            delegate.addCodeBlockToBuilder(this)
                        }
                        else -> {
                            val delegate = MultiAttributesAndMultiSettersGenerator(attributesParam, contextParam, codeBlock)
                            delegate.addCodeBlockToBuilder(this)
                        }
                    }
                }
            })
        }

        return this
    }

    companion object {
        private val initTypeName = ClassName(SourcererEnvironment.serviceApiPackageName, "init")
        private val inflaterComponentClassName = ClassName(SourcererEnvironment.servicePackageName, "InflaterComponent")
    }

}