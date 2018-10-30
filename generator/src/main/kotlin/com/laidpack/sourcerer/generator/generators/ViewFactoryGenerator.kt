package com.laidpack.sourcerer.generator.generators

import android.view.View
import com.laidpack.sourcerer.generator.index.ViewConstructorExpression
import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import com.laidpack.sourcerer.generator.target.CodeBlock
import com.squareup.kotlinpoet.*


class ViewFactoryGenerator(
        targetPackageName: String,
        private val targetClassName: ClassName,
        private val viewClassName: TypeName,
        factoryClassName: ClassName,
        private val superFactoryClassName: ClassName?,
        attributesClassName: ClassName,
        private val constructorExpression: ViewConstructorExpression,
        codeBlocks: List<CodeBlock>,
        private val minimumApiLevel: Int,
        minSdkVersion: Int,
        isFinal: Boolean,
        elementType: String
) : BaseFactoryGenerator(
        targetPackageName,
        targetClassName,
        viewClassName,
        factoryClassName,
        superFactoryClassName,
        attributesClassName,
        View::class,
        codeBlocks,
        minimumApiLevel,
        minSdkVersion,
        isFinal,
        elementType
) {
    private val viewTypeName = View::class.asTypeName()

    override val rootFactoryClassName: ClassName = rootViewFactoryClassName
    override val viewTypeVariable: TypeVariableName
        get() = TypeVariableName("TView", viewClassName)
    override val viewParam: ParameterSpec
        get() = ParameterSpec.builder("view", viewTypeVariable).build()

    override fun generateCreateInstanceFunc(): FunSpec? {
        if (superFactoryClassName == null || constructorExpression != ViewConstructorExpression.AbstractView) {
            val funSpec = FunSpec.builder("createInstance")
                    .addModifiers(KModifier.OVERRIDE)
                    .addParameter(contextParam)
                    .returns(viewTypeName)

            funSpec.wrapInMininumApiLevelCheckIfNeeded(
                    minimumApiLevel,
                    ifStatements = {
                        when (constructorExpression) {
                            ViewConstructorExpression.ContextOnly ->
                                this.addStatement("return %T(%N)", targetClassName, contextParam)
                            ViewConstructorExpression.ContextAndAttrs ->
                                this.addStatement("return %T(%N, null)", targetClassName, contextParam)
                            ViewConstructorExpression.ContextAttrsAndDefStyleAttr ->
                                this.addStatement("return %T(%N, null, 0)", targetClassName, contextParam)
                            ViewConstructorExpression.AbstractView -> {
                                this.addComment("${targetClassName.simpleName} is abstract")
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

        return null
    }

    companion object {
        val rootViewFactoryClassName = ClassName(SourcererEnvironment.servicesApiPackageName, "BaseViewFactory")
        fun getFactoryClassName(targetPackageName: String, originalClassName: ClassName): ClassName {
            return ClassName(targetPackageName, "${originalClassName.simpleName}Factory")
        }

    }
}