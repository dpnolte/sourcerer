package com.laidpack.sourcerer.generator.generators

import android.view.View
import android.view.ViewGroup
import com.laidpack.sourcerer.generator.*
import com.laidpack.sourcerer.generator.peeker.ClassCategory
import com.laidpack.sourcerer.generator.peeker.ConstructorExpression
import com.laidpack.sourcerer.generator.peeker.ViewConstructorExpression
import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import com.laidpack.sourcerer.generator.target.CodeBlock
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy


class ViewFactoryGenerator(
        targetClassName: ClassName,
        superClassName: ClassName?,
        private val constructorExpression: ViewConstructorExpression,
        private val defaultLayoutParamsClassName: ClassName?,
        numberOfTypeVariables: Int,
        codeBlocks: List<CodeBlock>
) : BaseFactoryGenerator(targetClassName, superClassName, View::class, codeBlocks, ClassCategory.View, numberOfTypeVariables) {

    private val viewTypeName = View::class.asTypeName()

    override fun getFactoryClassName(originalClassName: ClassName): ClassName {
        return ClassName(SourcererEnvironment.generatedPackageName, "${originalClassName.simpleName}Factory")
    }

    override val rootFactoryClassName: ClassName = rootViewFactoryClassName
    override val layoutTypeVariable: TypeVariableName
        get() = TypeVariableName("TView", viewClassTypeName)
    override val layoutParam: ParameterSpec
        get() = ParameterSpec.builder("v", viewTypeName).build()
    override val layoutVariableName: String
        get() = "view"

    override fun generateCreateInstanceFunc(): FunSpec {
        val funSpec = FunSpec.builder("createInstance")
                .addModifiers(KModifier.OVERRIDE)
                .addParameter(contextParam)
                .returns(viewTypeName)

        when (constructorExpression) {
            ViewConstructorExpression.ContextOnly ->
                funSpec.addStatement("return %T(%N)", targetClassName, contextParam)
            ViewConstructorExpression.ContextAndAttrs ->
                funSpec.addStatement("return %T(%N, null)", targetClassName, contextParam)
            ViewConstructorExpression.ContextAttrsAndDefStyleAttr ->
                funSpec.addStatement("return %T(%N, null, 0)", targetClassName, contextParam)
            ViewConstructorExpression.AbstractView ->
                funSpec.addStatement("throw IllegalStateException(%S)", "$targetClassName is abstract and cannot be instantiated")
        }

        return funSpec.build()
    }

    override fun getClassSpec(): TypeSpec.Builder {
        val classSpec = super.getClassSpec()
        val factoryClassName = if (defaultLayoutParamsClassName != null) {
            LayoutParamsFactoryGenerator.getFactoryClassName(
                    defaultLayoutParamsClassName
            ).parameterizedBy(
                    defaultLayoutParamsClassName,
                    AttributesGenerator.getAttributesClassName(defaultLayoutParamsClassName, ClassCategory.LayoutParams)
            )
        } else viewGroupLpFactoryClassName
        classSpec.addProperty(
                PropertySpec.builder("defaultLayoutParamsFactory", layoutParamFactoryComponentClassName, KModifier.OVERRIDE)
                        .getter(FunSpec.getterBuilder()
                                .addStatement("return %T()", factoryClassName)
                                .build())
                        .build()
        )
        return classSpec
    }

    companion object {
        val rootViewFactoryClassName = ClassName(SourcererEnvironment.serviceApiPackageName, "BaseViewFactory")
        val layoutParamFactoryComponentClassName =
                ClassName(SourcererEnvironment.serviceApiPackageName, "LayoutParamsFactoryComponent")
                        .parameterizedBy(WildcardTypeName.STAR, WildcardTypeName.STAR)
        val viewGroupLpClassName = ViewGroup.LayoutParams::class.asClassName()
        val viewGroupLpFactoryClassName = LayoutParamsFactoryGenerator.getFactoryClassName(
                viewGroupLpClassName
        ).parameterizedBy(
                viewGroupLpClassName,
                AttributesGenerator.getAttributesClassName(viewGroupLpClassName, ClassCategory.LayoutParams)
        )
    }
}