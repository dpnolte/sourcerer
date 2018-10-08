package com.laidpack.sourcerer.generator.generators.delegates

import com.laidpack.sourcerer.generator.target.AttributeTypesForSetter
import com.laidpack.sourcerer.generator.target.CodeBlock
import com.laidpack.sourcerer.generator.target.Setter
import com.laidpack.sourcerer.generator.generators.delegates.statements.BeginIfAnyFormatIsSet
import com.laidpack.sourcerer.generator.generators.delegates.statements.DeclareMultiFormatVariable
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec

class SingleAttributeAndMultiSettersGenerator(attributesParam: ParameterSpec, contextParam: ParameterSpec, private val codeBlock: CodeBlock) : DelegateGeneratorBase(attributesParam, contextParam) {
    private val attribute = codeBlock.attributes.values.first()
    private val isMultiFormatted = attribute.formats.size > 1
    private val delegate = this

    fun addCodeBlockToBuilder(builder : FunSpec.Builder) {
        if (codeBlock.setters.size < 2 && codeBlock.attributes.values.size == 1) throw IllegalStateException("Delegate is only suitable for multiple setters and single attribute")
        if (!isMultiFormatted) {
            println("Only multi formatted attributes can have multiple setters. Attribute '${attribute.name}' is not multi-formatted.. SKipping it!")
            return
        }

        builder.beginControlFlow("when")
        for(setterHashCode in attribute.setterHashCodes) {
            val typesForThisSetter = attribute.typesPerSetter[setterHashCode] as AttributeTypesForSetter
            val setter = codeBlock.setters.first { it.hashCode() == setterHashCode }
            builder.addWhenEntry(setter, typesForThisSetter)

        }
        builder.endControlFlow() // ends when

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
                val attributeParameterIndex = setter.attributeToParameter[attribute.name]
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
}