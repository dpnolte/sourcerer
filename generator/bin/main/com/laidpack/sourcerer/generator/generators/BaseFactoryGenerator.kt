package com.laidpack.sourcerer.generator.generators

import android.content.Context
import android.os.Build
import com.laidpack.sourcerer.generator.target.CodeBlock
import com.laidpack.sourcerer.generator.peeker.ClassCategory
import com.laidpack.sourcerer.generator.generators.delegates.MultiAttributesAndMultiSettersGenerator
import com.laidpack.sourcerer.generator.generators.delegates.MultiAttributesAndSingleSetterGenerator
import com.laidpack.sourcerer.generator.generators.delegates.SingleAttributeAndMultiSettersGenerator
import com.laidpack.sourcerer.generator.generators.delegates.SingleAttributeAndSingleSetterGenerator
import com.laidpack.sourcerer.generator.lint.ApiDetector
import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import kotlin.reflect.KClass

abstract class BaseFactoryGenerator(
        protected val targetClassName: ClassName,
        protected val superClassName: ClassName?,
        private val baseClassType: KClass<*>,
        private val codeBlocks: List<CodeBlock>,
        classCategory: ClassCategory,
        numberOfTypeVariables: Int,
        private val minimumApiLevel: Int,
        private val minSdkVersion: Int,
        private val isFinal: Boolean
) {
    private val hasSuperClass = superClassName != null
    private val attributesClassName = AttributesGenerator.getAttributesClassName(targetClassName, classCategory)
    private val attributesTypeVariable = TypeVariableName("TAttributes", attributesClassName)
    protected val viewClassName = if (numberOfTypeVariables > 0) {
        val stars = mutableListOf<TypeName>()
        for(i in 1..numberOfTypeVariables) {
            stars.add(WildcardTypeName.STAR)
        }
        targetClassName.parameterizedBy(*stars.toTypedArray())
    } else targetClassName
    private val viewClazzClassName
        get() = ClassName("java.lang", "Class")
            .parameterizedBy(viewTypeVariable)
    private val attributesClazzClassName = ClassName("java.lang", "Class")
            .parameterizedBy(attributesTypeVariable)
    private val elementName = if (classCategory == ClassCategory.View) {
        targetClassName.simpleName.decapitalize()
    } else targetClassName.canonicalName



    private val factoryClassName
        get() = getFactoryClassName(targetClassName)
    private val baseFactoryClassName : TypeName
        get() {
            return if (superClassName != null) {
                getFactoryClassName(superClassName).parameterizedBy(
                        if (isFinal) viewClassName else viewTypeVariable,
                        attributesTypeVariable
                )
            } else {
                rootFactoryClassName.parameterizedBy(
                        if (isFinal) viewClassName else viewTypeVariable,
                        attributesTypeVariable
                )
            }
        }

    abstract fun getFactoryClassName(originalClassName: ClassName): ClassName
    abstract val rootFactoryClassName: ClassName
    abstract val viewTypeVariable: TypeVariableName
    abstract val viewParam : ParameterSpec

    protected val attributesParam = ParameterSpec.builder("attributes", attributesTypeVariable).build()
    protected val contextParam = ParameterSpec.builder("context", Context::class).build()

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
                .addSuperClassParameters()
                .superclass(this.baseFactoryClassName)
                .addTypeVariables(getClassTypeVariables())
                .addProperty(
                        PropertySpec.builder(
                                "elementName",
                                String::class.asTypeName(),
                                KModifier.OVERRIDE
                        ).initializer("%S", elementName)
                                .build()
                )
                .addFunction(generateCreateInstanceFunc())
                .addInitFuncIfRequired()
                .addType(getCompanionSpec())
    }

    open fun getCompanionSpec(): TypeSpec {
        val invokeFuncStmtArgs = mutableListOf<Any>()
        val invokeFunStmtExpression = if (isFinal) {
            invokeFuncStmtArgs.add(factoryClassName)
            invokeFuncStmtArgs.add(attributesTypeVariable)
            "return %T(%T::class.java)"
        } else {
            invokeFuncStmtArgs.add(factoryClassName)
            invokeFuncStmtArgs.add(viewTypeVariable)
            invokeFuncStmtArgs.add(attributesTypeVariable)
            "return %T(%T::class.java, %T::class.java)"
        }

        val invokeFunc = FunSpec.builder("invoke")
                .addModifiers(KModifier.INLINE, KModifier.OPERATOR)
                .addTypeVariables(getClassTypeVariables(true))
                .addStatement(invokeFunStmtExpression, *invokeFuncStmtArgs.toTypedArray())
                .build()

        val initBlockStmtArgs = mutableListOf<Any>()
        val initBlockStmtExpression = if (isFinal) {
            initBlockStmtArgs.add(inflaterComponentClassName)
            initBlockStmtArgs.add(factoryClassName)
            initBlockStmtArgs.add(attributesClassName)
            "%T.addFactory(%T<%T>())"
        } else {
            initBlockStmtArgs.add(inflaterComponentClassName)
            initBlockStmtArgs.add(factoryClassName)
            initBlockStmtArgs.add(viewClassName)
            initBlockStmtArgs.add(attributesClassName)
            "%T.addFactory(%T<%T, %T>())"
        }
        val initBlock = com.squareup.kotlinpoet.CodeBlock.Builder()
                .addStatement(initBlockStmtExpression, *initBlockStmtArgs.toTypedArray())

        return TypeSpec.companionObjectBuilder()
                .addFunction(invokeFunc)
                .addInitializerBlock(initBlock.build())
                .build()
    }

    open fun getPrimaryConstructor(): FunSpec {
        val funSpec =FunSpec.constructorBuilder()
        if (!isFinal) {
            funSpec.addParameter("instanceType", viewClazzClassName)
        }
        return funSpec.addParameter("attributesType", attributesClazzClassName)
                .build()
    }

    private fun getClassTypeVariables(reified: Boolean = false): Iterable<TypeVariableName> {
        val typeVariables = mutableListOf<TypeVariableName>()
        if (!isFinal) {
            typeVariables.add(viewTypeVariable.reified(reified))
        }
        typeVariables.add(attributesTypeVariable.reified(reified))
        return typeVariables.asIterable()
    }

    private fun TypeSpec.Builder.addSuperClassParameters(): TypeSpec.Builder {
        if (isFinal) {
            this.addSuperclassConstructorParameter("%T::class.java", viewClassName)
        } else {
            this.addSuperclassConstructorParameter("%N", "instanceType")
        }
        this.addSuperclassConstructorParameter("%N", "attributesType")
        return this
    }

    abstract fun generateCreateInstanceFunc(): FunSpec

    protected fun FunSpec.Builder.wrapInMininumApiLevelCheckIfNeeded(
            minApiLevel: Int,
            ifStatements: FunSpec.Builder.() -> Unit,
            elseStatements: (FunSpec.Builder.() -> Unit)? = null,
            operator: String = ">="
    ) : FunSpec.Builder {
        if (minApiLevel > minSdkVersion) {
            this.beginControlFlow("if (%T.VERSION.SDK_INT $operator %L)", Build::class.asTypeName(), minApiLevel)
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
                    .addParameter(viewParam.name, baseClassType.asTypeName())
                    .addParameter(contextParam)
                    .addParameter(attributesParam)
                    .addSuperInitIfRequired()

            if (codeBlocks.isNotEmpty()) {
                funSpec.wrapInMininumApiLevelCheckIfNeeded(minimumApiLevel, ifStatements = {
                    this.wrapInViewIsTypeCheckIfRequired {
                        this.beginControlFlow("%N.%T", viewParam, initTypeName)
                            .addInitializeAttributeCodeBlocks()
                            .endControlFlow()
                    }
                })
            }

            this.addFunction(funSpec.build())
        }
        return this
    }

    private fun FunSpec.Builder.wrapInViewIsTypeCheckIfRequired(ifStatements: FunSpec.Builder.() -> Unit): FunSpec.Builder {
        if (targetClassName != baseClassType.asClassName()) {
            this.beginControlFlow("if (%N is %T)", viewParam, viewClassName)
        }
        this.ifStatements()
        if (targetClassName != baseClassType.asClassName()) {
            this.endControlFlow()
        }
        return this
    }


    private fun FunSpec.Builder.addSuperInitIfRequired(): FunSpec.Builder {
        if (hasSuperClass) {
            this.addStatement("super.init(%N, %N, %N)", viewParam, contextParam, attributesParam)
        }
        return this
    }

    private fun FunSpec.Builder.addInitializeAttributeCodeBlocks(): FunSpec.Builder {
        val codeBlocksPerMinimumApiLevel = codeBlocks.groupBy {
            it.minimumApiLevel
        }.toSortedMap()
        codeBlocksPerMinimumApiLevel.forEach {
            val codeBlockMinimumApiLevel = if (it.key <= minimumApiLevel) {
                ApiDetector.UNKNOWN_OR_VERSION_1 // only add check if code block's minimum api level is higher the class's
            } else it.key
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