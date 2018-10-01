package com.laidpack.sourcerer.generator.generators.delegates

import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.CodeBlock
import com.laidpack.sourcerer.generator.target.Setter
import com.laidpack.sourcerer.generator.generators.delegates.statements.*
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec

class SingleAttributeAndSingleSetterGenerator(attributesParam: ParameterSpec, contextParam: ParameterSpec, private val codeBlock: CodeBlock) : DelegateGeneratorBase(attributesParam, contextParam) {
    private val setter = codeBlock.setter
    private val attribute = codeBlock.attributes.values.first()
    private val typesForThisSetter = attribute.typesForFirstSetter
    private val isMultiFormatted = attribute.formats.size > 1
    private val delegate = this


    fun addCodeBlockToBuilder(builder : FunSpec.Builder) {
        val typesPerSetter = attribute.resolvedTypesPerSetter(codeBlock.setter.hashCode())
        builder.beginIfAnyValueIsSet()
        var valueLiteral = when {
            isMultiFormatted -> "${attributesParam.name}.${attribute.name}"
            typesPerSetter.hasEnumAsAttributeType -> "it.value"
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
        BeginIfAnyValueIsDifferentThanCurrent(this, attributesParam)
                .addAsIf(builder, attribute, setter, typesForThisSetter, valueLiteral)
        builder.addSetter(attribute, setter, valueLiteral)
        builder.endControlFlow() // ends if value not equal to current
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

    private fun FunSpec.Builder.addSetter(attribute: Attribute, setter: Setter, valueLiteral: String): FunSpec.Builder {
        when {
            setter.hasPropertyName -> {
                this.addStatement("%N = %L", setter.propertyName, valueLiteral)
            }
            setter.isField -> {
                this.addStatement("%N = %L", setter.name, valueLiteral)
            }
            setter.parameters.size > 1 -> {
                var index = 0
                val attributeParameterIndex = setter.attributeToParameter[attribute.name]
                val parameters = setter.parameters.joinToString(", ") { parameter ->
                    val result = when {
                        attributeParameterIndex == index -> valueLiteral
                        parameter.defaultValue.isNotBlank() -> parameter.defaultValue
                        parameter.isNullable -> "null"
                        else -> ""
                    }
                    index += 1
                    result
                }
                this.addStatement("%N($parameters)", setter.name)
            }
            else -> {
                this.addStatement("%N(%L)", setter.name, valueLiteral)
            }
        }
        return this
    }
}