package com.laidpack.sourcerer.generator.generators.delegates

import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.AttributeTypesForSetter
import com.laidpack.sourcerer.generator.target.CodeBlock
import com.laidpack.sourcerer.generator.target.Setter
import com.laidpack.sourcerer.generator.generators.delegates.statements.*
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec

class MultiAttributesAndMultiSettersGenerator(attributesParam: ParameterSpec, contextParam: ParameterSpec, private val codeBlock: CodeBlock) : DelegateGeneratorBase(attributesParam, contextParam) {
    private val attributes = codeBlock.attributes
    private val variableNames = mutableMapOf<Int /* setter hash code */, MutableMap<String /* attr name */, String /* variable name */>>()
    private val attrToVarNames = mutableMapOf<String /* attr name */, MutableSet<String> /* var name */>()
    private val processedAttributes = mutableSetOf<String>()
    private val delegate = this
    private val selectedSetters = selectSetters()

    fun addCodeBlockToBuilder(builder: FunSpec.Builder){
        builder.beginFlowIfAnyValueIsSet()
        for (setter in selectedSetters) {
            builder.addLocalVars(setter)
            builder.beginFlowIfAnyValueIsNotDefault(setter)
            builder.addSetter(setter)
            builder.endControlFlow()
        }
        builder.endControlFlow()
    }

    // deselect setters with same attributes (but different formats)
    // prioritize by parameter count and if transform is required
    private fun selectSetters(): List<Setter> {
        val selectedSetters = mutableListOf<Setter>()
        val identifiedAttributes = mutableSetOf<String>()
        codeBlock.setters
                .sortedBy {
                    val setterHashCode = it.hashCode()
                    val requiresTransformingMethod = attributes.values.any {
                        val  tps = it.typesPerSetter[setterHashCode]
                        tps != null && tps.requiresTransformMethod
                    }
                    it.parameters.size*10 + if (requiresTransformingMethod) 1 else 0
                }
                .forEach { setter ->
                    if (anyAttributeNotYetIdentified(setter, identifiedAttributes)) {
                        selectedSetters.add(setter)
                        val setterHashCode = setter.hashCode()
                        identifiedAttributes.addAll(
                                attributes.values
                                .filter { it.setterHashCodes.contains(setterHashCode) }
                                .map {  it.name }
                        )
                    }
                }
        return selectedSetters
    }

    private fun anyAttributeNotYetIdentified(setter: Setter, identifiedAttributes: Set<String>): Boolean {
        return setter.attributeToParameter.keys.any { attrName ->
            !identifiedAttributes.contains(attrName)
        }
    }

    private fun FunSpec.Builder.beginFlowIfAnyValueIsSet(): FunSpec.Builder {
        val conditionCode = mutableListOf<String>()
        val args = mutableListOf<Any>()
        attributes.values.forEach { attr ->
            val condition = if (attr.formats.size > 1) {
                BeginIfAnyFormatIsSet(delegate, attributesParam)
                        .getCondition(attr)
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

    private fun FunSpec.Builder.addLocalVars(setter: Setter): FunSpec.Builder {
        val setterHashCode = setter.hashCode()
        val attrToVarNameForThisSetter = mutableMapOf<String, String>()
        for(attrName in setter.attributeToParameter.keys) {
            val attr = attributes[attrName] as Attribute
            if (variableNames[setterHashCode] == null) {
                variableNames[setterHashCode] = mutableMapOf()
            }
            val typesPerSetter = attr.typesPerSetter[setterHashCode] as AttributeTypesForSetter
            val suffix = (typesPerSetter.setterType as ClassName).simpleName
            val variableName = attr.localVariableName + suffix
            attrToVarNameForThisSetter[attr.name] = variableName
            if (!attrToVarNames.containsKey(attrName)) {
                attrToVarNames[attrName] = mutableSetOf()
            }
            val varNames = attrToVarNames[attrName] as MutableSet<String>
            if (varNames.contains(variableName)) {
                // don't declare the same var
                continue
            }
            varNames.add(variableName)

            if (attr.formats.size > 1) {
                DeclareMultiFormatVariable(delegate, attributesParam)
                        .addDeclaredVar(
                                this,
                                attr,
                                typesPerSetter,
                                defaultToGetterValue = true,
                                variableName = variableName
                        )
            } else {
                DeclareSingleFormatVariable(delegate, attributesParam)
                        .addDeclaredVar(
                                this,
                                attr,
                                typesPerSetter,
                                defaultToGetterValue = true,
                                variableName = variableName
                        )
            }
        }
        variableNames[setterHashCode] = attrToVarNameForThisSetter
        return this
    }

    private fun FunSpec.Builder.beginFlowIfAnyValueIsNotDefault(setter: Setter): FunSpec.Builder {
        val conditionCode = mutableListOf<String>()
        val args = mutableListOf<Any>()
        val setterHashCode = setter.hashCode()
        val setterVariableNames = variableNames[setterHashCode] as MutableMap<String, String>
        val selectedAttrs = mutableListOf<Attribute>()
        val valueLiterals = mutableListOf<String>()
        setter.attributeToParameter.keys.forEach { attrName ->
            if (!processedAttributes.contains(attrName)) {
                val attr = attributes[attrName] as Attribute
                selectedAttrs.add(attr)
                val variableName = setterVariableNames[attrName] as String
                valueLiterals.add(variableName)
                processedAttributes.add(attrName)
            }
        }
        BeginIfAnyValueIsDifferentThanCurrent(delegate, attributesParam)
                .addAsIf(this, selectedAttrs, setter, valueLiterals)

        return this
    }

    private fun FunSpec.Builder.addSetter(setter: Setter): FunSpec.Builder {
        val setterHashCode = setter.hashCode()
        val setterVariableNames = variableNames[setterHashCode] as MutableMap<String, String>
        var index = 0
        val parameters = setter.parameters.joinToString(", ") { parameter ->
            val attributeToParameters =  setter.attributeToParameter.filter { it.value == index } //codeBlock.attributes.values.find { it.hasParameterIndex && it.resolvedParameterIndex == index}
            val attribute = if (attributeToParameters.isNotEmpty()) {
                codeBlock.attributes[attributeToParameters.keys.first()] as Attribute
            } else null
            val result = when {
                attribute != null -> setterVariableNames[attribute.name] as String
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