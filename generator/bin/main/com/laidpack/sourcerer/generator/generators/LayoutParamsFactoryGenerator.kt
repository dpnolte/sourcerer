package com.laidpack.sourcerer.generator.generators

import android.view.ViewGroup
import com.laidpack.sourcerer.generator.target.CodeBlock
import com.laidpack.sourcerer.generator.peeker.ClassCategory
import com.laidpack.sourcerer.generator.peeker.LayoutParamsConstructorExpression
import com.laidpack.sourcerer.generator.resources.SourcererEnvironment
import com.squareup.kotlinpoet.*

class LayoutParamsFactoryGenerator(
        targetClassName: ClassName,
        superClassName: ClassName?,
        private val constructorExpression: LayoutParamsConstructorExpression,
        numberOfTypeVariables: Int,
        codeBlocks: List<CodeBlock>,
        private val minimumApiLevel: Int,
        minSdkVersion: Int,
        isFinal: Boolean
) : BaseFactoryGenerator(
        targetClassName,
        superClassName,
        ViewGroup.LayoutParams::class,
        codeBlocks,
        ClassCategory.LayoutParams,
        numberOfTypeVariables,
        minimumApiLevel,
        minSdkVersion,
        isFinal
) {

    override fun getFactoryClassName(originalClassName: ClassName): ClassName {
        return Companion.getFactoryClassName(originalClassName)
    }
    private val layoutParamsTypeName = ViewGroup.LayoutParams::class.asTypeName()
    override val rootFactoryClassName = Companion.rootFactoryClassName
    override val viewTypeVariable: TypeVariableName
        get() = TypeVariableName("TLayoutParams", viewClassName)
    override val viewParam: ParameterSpec
        get() = ParameterSpec.builder("layoutParams", viewTypeVariable).build()

    override fun generateCreateInstanceFunc(): FunSpec {
        val funSpec = FunSpec.builder("createInstance")
                .addModifiers(KModifier.OVERRIDE)
                .addParameter(contextParam)
                .returns(layoutParamsTypeName)

        funSpec.wrapInMininumApiLevelCheckIfNeeded(
                minimumApiLevel,
                ifStatements =  {
                    when (constructorExpression) {
                        LayoutParamsConstructorExpression.Empty ->
                            this.addStatement("return %T()", targetClassName)
                        LayoutParamsConstructorExpression.CopySource ->
                            this.addStatement("return %T(%T(%T.MATCH_PARENT, %T.MATCH_PARENT))", targetClassName, viewGroupLayoutParamsClassName, viewGroupLayoutParamsClassName, viewGroupLayoutParamsClassName)
                        LayoutParamsConstructorExpression.WidthAndHeight ->
                            this.addStatement("return %T(%T.MATCH_PARENT, %T.MATCH_PARENT)", targetClassName, targetClassName, targetClassName)
                        LayoutParamsConstructorExpression.AbstractLayoutParams -> {
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
        val rootFactoryClassName = ClassName(SourcererEnvironment.serviceApiPackageName, "BaseLayoutParamsFactory")
        val viewGroupLayoutParamsClassName = ViewGroup.LayoutParams::class.asClassName()

        fun getFactoryClassName(originalClassName: ClassName): ClassName {
            val splitClassNameString = originalClassName.canonicalName.split(".")
            val simpleName = splitClassNameString[splitClassNameString.lastIndex - 1] + splitClassNameString[splitClassNameString.lastIndex]
            return ClassName(SourcererEnvironment.generatedPackageName, simpleName + "Factory")
        }
    }

}