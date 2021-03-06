package com.laidpack.sourcerer.generator.generators.delegates

import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.CodeBlock
import com.laidpack.sourcerer.generator.generators.delegates.statements.*
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec

class MultiAttributesAndSingleSetterGenerator(attributesParam: ParameterSpec, contextParam: ParameterSpec, private val codeBlock: CodeBlock) : DelegateGeneratorBase(attributesParam, contextParam) {
    private val attributes = codeBlock.attributes
    private val setter = codeBlock.setter
    private val typesPerSetter = attributes.values.first().typesForFirstSetter
    private val delegate = this

    fun addCodeBlockToBuilder(builder: FunSpec.Builder){
        builder.beginFlowIfAnyValueIsSet()
        builder.addLocalVars()
        builder.beginFlowIfAnyValueIsNotDefault()
        builder.addSetter()
        builder.endControlFlow()
        builder.endControlFlow()
    }

    private fun FunSpec.Builder.beginFlowIfAnyValueIsSet(): FunSpec.Builder {
        val conditionCode = mutableListOf<String>()
        val args = mutableListOf<Any>()
        attributes.values.forEach { attr ->
            val condition = if (attr.formats.size > 1) {
                BeginIfAnyFormatIsSet(delegate, attributesParam)
                        .getCondition(attr, typesPerSetter)
            } else {
                BeginIfSingleFormatIsSet(delegate, attributesParam)
                        .getCondition(attr, asLet = false)
            }
            args.addAll(condition.args)
            conditionCode.add(condition.expression)
        }
        val controlFlow = "if (${conditionCode.joinToString(" || ")})"
        return this.beginControlFlow(controlFlow, *args.toTypedArray())
    }

    private fun FunSpec.Builder.addLocalVars(): FunSpec.Builder {
        codeBlock.attributes.values.forEach { attr ->
            if (attr.formats.size > 1) {
                DeclareMultiFormatVariable(delegate, attributesParam)
                        .addDeclaredVar(this, attr, typesPerSetter, defaultToGetterValue = true)
            } else {
                DeclareSingleFormatVariable(delegate, attributesParam)
                        .addDeclaredVar(
                                this,
                                attr,
                                typesPerSetter,
                                defaultToGetterValue = true
                        )
            }
        }
        return this
    }

    private fun FunSpec.Builder.beginFlowIfAnyValueIsNotDefault(): FunSpec.Builder {
        BeginIfAnyValueIsDifferentThanCurrent(delegate, attributesParam)
                .addAsIf(this, attributes.values.toList(), setter)
       return this
    }

    private fun FunSpec.Builder.addSetter(): FunSpec.Builder {
        val setter = codeBlock.setter
        var index = 0
        val parameters = setter.parameters.joinToString(", ") { parameter ->
            val attributeToParameters =  setter.attributeToParameter.filter { it.value == index } //codeBlock.attributes.values.find { it.hasParameterIndex && it.resolvedParameterIndex == index}
            val attribute = if (attributeToParameters.isNotEmpty()) {
                codeBlock.attributes[attributeToParameters.keys.first()] as Attribute
            } else null
            val result = when {
                attribute != null -> attribute.localVariableName
                parameter.defaultValue.isNotBlank() -> parameter.defaultValue
                parameter.isNullable -> "null"
                else -> ""
            }
            index += 1
            result
        }
        return this.addStatement("${setter.name}($parameters)")
    }
}