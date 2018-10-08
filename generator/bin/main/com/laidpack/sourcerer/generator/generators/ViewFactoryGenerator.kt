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
        fallbackClassName: ClassName?,
        private val constructorExpression: ViewConstructorExpression,
        numberOfTypeVariables: Int,
        codeBlocks: List<CodeBlock>,
        private val minimumApiLevel: Int,
        minSdkVersion: Int
) : BaseFactoryGenerator(targetClassName, superClassName, fallbackClassName, View::class, codeBlocks, ClassCategory.View, numberOfTypeVariables, minimumApiLevel, minSdkVersion) {

    private val viewTypeName = View::class.asTypeName()

    override fun getFactoryClassName(originalClassName: ClassName): ClassName {
        return ClassName(SourcererEnvironment.generatedPackageName, "${originalClassName.simpleName}Factory")
    }

    override val rootFactoryClassName: ClassName = rootViewFactoryClassName
    override val layoutTypeVariable: TypeVariableName
        get() = TypeVariableName("TView", viewClassTypeName)
    override val layoutParam: ParameterSpec
        get() = ParameterSpec.builder("view", layoutTypeVariable).build()
    override val layoutVariableName: String
        get() = "view"

    override fun generateCreateInstanceFunc(): FunSpec {
        val funSpec = FunSpec.builder("createInstance")
                .addModifiers(KModifier.OVERRIDE)
                .addParameter(contextParam)
                .returns(viewTypeName)

        funSpec.wrapInMininumApiLevelCheckIfNeeded(
                minimumApiLevel,
                ifStatements =  {
                    when (constructorExpression) {
                        ViewConstructorExpression.ContextOnly ->
                            this.addStatement("return %T(%N)", targetClassName, contextParam)
                        ViewConstructorExpression.ContextAndAttrs ->
                            this.addStatement("return %T(%N, null)", targetClassName, contextParam)
                        ViewConstructorExpression.ContextAttrsAndDefStyleAttr ->
                            this.addStatement("return %T(%N, null, 0)", targetClassName, contextParam)
                        ViewConstructorExpression.AbstractView ->
                            this.addStatement("throw IllegalStateException(%S)", "$targetClassName is abstract and cannot be instantiated")
                    }
                },
                elseStatements = {
                    this.addStatement("return super.createInstance(%N)", contextParam.name)
                }
        )

        return funSpec.build()
    }

    companion object {
        val rootViewFactoryClassName = ClassName(SourcererEnvironment.serviceApiPackageName, "BaseViewFactory")

    }
}