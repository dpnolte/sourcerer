package com.laidpack.sourcerer.generator.generators

import android.view.View
import com.laidpack.sourcerer.generator.peeker.ClassCategory
import com.laidpack.sourcerer.generator.peeker.ViewConstructorExpression
import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import com.laidpack.sourcerer.generator.target.CodeBlock
import com.squareup.kotlinpoet.*


class ViewFactoryGenerator(
        targetClassName: ClassName,
        superClassName: ClassName?,
        private val constructorExpression: ViewConstructorExpression,
        numberOfTypeVariables: Int,
        codeBlocks: List<CodeBlock>,
        private val minimumApiLevel: Int,
        minSdkVersion: Int,
        isFinal: Boolean
) : BaseFactoryGenerator(
        targetClassName,
        superClassName,
        View::class,
        codeBlocks,
        ClassCategory.View,
        numberOfTypeVariables,
        minimumApiLevel,
        minSdkVersion,
        isFinal
) {

    private val viewTypeName = View::class.asTypeName()

    override fun getFactoryClassName(originalClassName: ClassName): ClassName {
        return ClassName(SourcererEnvironment.generatedPackageName, "${originalClassName.simpleName}Factory")
    }

    override val rootFactoryClassName: ClassName = rootViewFactoryClassName
    override val viewTypeVariable: TypeVariableName
        get() = TypeVariableName("TView", viewClassName)
    override val viewParam: ParameterSpec
        get() = ParameterSpec.builder("view", viewTypeVariable).build()

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
                        ViewConstructorExpression.AbstractView -> {
                            this.addComment("// ${targetClassName.simpleName} is abstract")
                            this.addStatement("return super.createInstance(%N)", contextParam.name)
                        }
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