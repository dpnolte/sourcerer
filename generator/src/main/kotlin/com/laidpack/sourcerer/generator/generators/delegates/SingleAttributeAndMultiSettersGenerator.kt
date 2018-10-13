package com.laidpack.sourcerer.generator.generators.delegates

import com.laidpack.sourcerer.generator.generators.delegates.statements.*
import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.AttributeTypesForSetter
import com.laidpack.sourcerer.generator.target.CodeBlock
import com.laidpack.sourcerer.generator.target.Setter
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec

class SingleAttributeAndMultiSettersGenerator(attributesParam: ParameterSpec, contextParam: ParameterSpec, private val codeBlock: CodeBlock) : DelegateGeneratorBase(attributesParam, contextParam) {
    private val attribute = codeBlock.attributes.values.first()
    private val isMultiFormatted = attribute.formats.size > 1
    private val delegate = this

    fun addCodeBlockToBuilder(builder : FunSpec.Builder) {
        if (codeBlock.setters.size < 2 && codeBlock.attributes.values.size == 1) throw IllegalStateException("Delegate is only suitable for multiple setters and single attribute")
        if (!isMultiFormatted && !attribute.oneFormatRequiresMultipleSetters) {
            println("\t\t\t! - Only multi formatted attributes can have multiple setters or it must be explicitly flag as being requiring multiple setters . Attribute '${attribute.name}' is not multi-formatted and attribute.oneFormatRequiresMultipleSetters != true... Skipping it!")
            return
        }

        if (isMultiFormatted) {
            builder.beginControlFlow("when")
            for (setterHashCode in attribute.setterHashCodes) {
                val typesForThisSetter = attribute.typesPerSetter[setterHashCode] as AttributeTypesForSetter
                val setter = codeBlock.setters[setterHashCode] as Setter
                builder.addWhenEntry(setter, typesForThisSetter)

            }
            builder.endControlFlow() // ends when
        } else {
            BeginIfSingleFormatIsSet(delegate, attributesParam)
                    .addAsLet(builder, attribute)

            for (setterHashCode in attribute.setterHashCodes) {
                val typesForThisSetter = attribute.typesPerSetter[setterHashCode] as AttributeTypesForSetter
                val setter = codeBlock.setters[setterHashCode] as Setter
                var valueLiteral = when {
                    typesForThisSetter.hasEnumAsAttributeType -> "it.value"
                    else -> "it"
                }
                if (typesForThisSetter.requiresTransformMethod) {
                    DeclareSingleFormatVariable(this, attributesParam)
                            .addDeclaredVar(builder, attribute, typesForThisSetter, valueLiteral)
                    valueLiteral = attribute.localVariableName
                }
                val getters = attribute.getters.filter { it.setterHashCodes.contains(setterHashCode) }
                BeginIfAnyValueIsDifferentThanCurrent(this, attributesParam)
                        .addAsIf(builder, attribute, setter, typesForThisSetter, valueLiteral, getters)
                builder.addSetter(attribute, setter, valueLiteral)
                builder.endControlFlow() // ends if value not equal to current
            }
            builder.endControlFlow() // ends if not null
        }

    }

    private fun FunSpec.Builder.addWhenEntry(setter: Setter, typesForThisSetter: AttributeTypesForSetter): FunSpec.Builder {
        BeginIfAnyFormatIsSet(delegate, attributesParam)
                .addAsWhenEntry(this, attribute, typesForThisSetter)
        DeclareMultiFormatVariable(delegate, attributesParam)
                .addDeclaredVar(this, attribute, typesForThisSetter)
        this.addSetter(setter)
        return this.endControlFlow()
    }

    private fun FunSpec.Builder.addSetter(setter: Setter): FunSpec.Builder {
        when {
            setter.hasPropertyName -> {
                this.addStatement("%N = %L", setter.propertyName, attribute.localVariableName)
            }
            setter.parameters.size > 1 -> {
                var index = 0
                val attributeParameterIndex = setter.callSignatureMaps[attribute.name]
                val parameters = setter.parameters.joinToString(", ") { parameter ->
                    val result = when {
                        attributeParameterIndex == index -> attribute.localVariableName
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
                this.addStatement("%N(%L)", setter.name, attribute.localVariableName)
            }
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
                val parameterIndex = setter.callSignatureMaps[attribute]
                val parameters = setter.parameters.joinToString(", ") { parameter ->
                    val result = when {
                        parameterIndex == index -> valueLiteral
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