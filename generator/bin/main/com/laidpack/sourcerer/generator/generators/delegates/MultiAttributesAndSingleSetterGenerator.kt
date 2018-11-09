package com.laidpack.sourcerer.generator.generators.delegates

import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.CodeBlock
import com.laidpack.sourcerer.generator.generators.delegates.statements.*
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec

class MultiAttributesAndSingleSetterGenerator(
        attributesParam: ParameterSpec,
        private val contextParam: ParameterSpec,
        private val codeBlock: CodeBlock
) : DelegateGeneratorBase(attributesParam, contextParam) {
    private val attributes = codeBlock.attributes
    private val setter = codeBlock.setter
    private val setterHashCode = setter.hashCode()
    private val delegate = this
    private val attributesToParameters = setter.callSignatureMaps.getCallSignatureMapUsedByAnOfTheseAttributes(attributes.values)

    fun addCodeBlockToBuilder(builder: FunSpec.Builder){
        builder.beginFlowIfAnyValueIsSet()
        builder.addLocalVars()
        builder.beginFlowIfAnyValueIsNotDefault()
        builder.addSetter()
        if (attributes.values.any { it.resolvedTypesPerSetter(setterHashCode).hasMatchedGetter }) {
            builder.endControlFlow()
        }
        builder.endControlFlow()
    }

    private fun FunSpec.Builder.beginFlowIfAnyValueIsSet(): FunSpec.Builder {
        val conditionCode = mutableListOf<String>()
        val args = mutableListOf<Any>()
        attributes.values.forEach { attr ->
            val condition = if (attr.formatsUsedBySetters.size > 1) {
                BeginIfAnyFormatIsSet(delegate, attributesParam)
                        .getCondition(attr, attr.resolvedTypesPerSetter(setterHashCode))
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
            val typesPerSetter = attr.resolvedTypesPerSetter(setterHashCode)
            if (attr.formatsUsedBySetters.size > 1) {
                DeclareMultiFormatVariable(delegate, attributesParam)
                        .addDeclaredVar(this, attr, typesPerSetter, defaultToGetterValue = typesPerSetter.hasMatchedGetter)
            } else {
                DeclareSingleFormatVariable(delegate, attributesParam)
                        .addDeclaredVar(
                                this,
                                attr,
                                typesPerSetter,
                                defaultToGetterValue = typesPerSetter.hasMatchedGetter
                        )
            }
        }
        return this
    }

    private fun FunSpec.Builder.beginFlowIfAnyValueIsNotDefault(): FunSpec.Builder {
        BeginIfAnyValueIsDifferentThanCurrent(delegate, attributesParam)
                .addAsIf(this, attributes.values.toList(), setter, attributes.values.map { it.localVariableName })
       return this
    }

    private fun FunSpec.Builder.addSetter(): FunSpec.Builder {
        InvokeSetter(delegate, contextParam)
                .addAsStatement(
                        this,
                        setter,
                        attributes,
                        attributes.mapValues { it.value.localVariableName }
                )

        return this
    }
}