package com.laidpack.sourcerer.generator.generators.delegates

import android.content.Context
import com.laidpack.sourcerer.generator.target.CodeBlock
import com.laidpack.sourcerer.generator.target.Setter
import com.laidpack.sourcerer.generator.generators.delegates.statements.*
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec

class SingleAttributeAndSingleSetterGenerator(
        attributesParam: ParameterSpec,
        private val contextParam: ParameterSpec,
        private val codeBlock: CodeBlock
) : DelegateGeneratorBase(attributesParam, contextParam) {
    private val setter = codeBlock.setter
    private val attribute = codeBlock.attributes.values.first()
    private val typesForThisSetter = attribute.typesForFirstSetter
    private val isMultiFormatted = attribute.formatsUsedBySetters.size > 1
    private val delegate = this


    fun addCodeBlockToBuilder(builder : FunSpec.Builder) {
        val typesPerSetter = attribute.resolvedTypesPerSetter(codeBlock.setter.hashCode())
        builder.beginIfAnyValueIsSet()
        var valueLiteral = when {
            isMultiFormatted -> "${attributesParam.name}.${attribute.name}"
            typesPerSetter.hasEnumAsAttributeType || typesPerSetter.hasFlagsAsAttributeType -> "it.value"
            else -> "it"
        }
        if (isMultiFormatted) {
            DeclareMultiFormatVariable(this, attributesParam )
                    .addDeclaredVar(builder, attribute, typesForThisSetter)
            valueLiteral = attribute.localVariableName
        } else if (typesPerSetter.requiresTransformMethod) {
            DeclareSingleFormatVariable(this, attributesParam)
                    .addDeclaredVar(builder, attribute, typesForThisSetter, valueLiteral)
            valueLiteral = attribute.localVariableName
        }
        if (typesPerSetter.hasMatchedGetter) {
            BeginIfAnyValueIsDifferentThanCurrent(this, attributesParam)
                    .addAsIf(builder, attribute, setter, typesForThisSetter, valueLiteral)
        }
        builder.addSetter(setter, valueLiteral)
        if (typesPerSetter.hasMatchedGetter) {
            builder.endControlFlow() // ends if value not equal to current
        }
        builder.endControlFlow() // ends if not null

    }

    private fun FunSpec.Builder.beginIfAnyValueIsSet(): FunSpec.Builder {
        if (isMultiFormatted) {
            BeginIfAnyFormatIsSet(delegate, attributesParam)
                    .addAsIf(this, attribute, typesForThisSetter)
        } else {
            BeginIfSingleFormatIsSet(delegate, attributesParam)
                    .addAsLet(this, attribute)
        }
        return this
    }

    private fun FunSpec.Builder.addSetter(setter: Setter, valueLiteral: String): FunSpec.Builder {
        InvokeSetter(delegate, contextParam)
                .addAsStatement(this, setter, attribute, valueLiteral)
        return this
    }
}