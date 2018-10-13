package com.laidpack.sourcerer.generator.generators.delegates

import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.AttributeTypesForSetter
import com.laidpack.sourcerer.generator.target.CodeBlock
import com.laidpack.sourcerer.generator.target.Setter
import com.laidpack.sourcerer.generator.generators.delegates.statements.*
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec

class MultiAttributesAndMultiSettersGenerator(attributesParam: ParameterSpec, contextParam: ParameterSpec, private val codeBlock: CodeBlock) : DelegateGeneratorBase(attributesParam, contextParam) {
    private val attributes = codeBlock.attributes
    private val variableNames = mutableMapOf<Int /* setter hash code */, MutableMap<String /* attr name */, String /* variable name */>>()
    private val attrToVarNames = mutableMapOf<String /* attr name */, MutableSet<String> /* var name */>()
    private val processedVariables = mutableSetOf<String>()
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
        val sortedSetters = codeBlock.setters.values.sortedWith(compareBy(
                { it.parameters.size },
                { // is transforming method required?
                    attributes.values.any { attr ->
                        val tps = attr.typesPerSetter[it.hashCode()]
                        tps != null && tps.requiresTransformMethod
                    }
                }
        ))
        sortedSetters.forEach { setter ->
                    var requiresSetter = false
                    val setterHashCode = setter.hashCode()
                    for (attribute in attributes.values) {
                        if (attribute.setterHashCodes.contains(setterHashCode)) {
                            val tps = attribute.typesPerSetter[setterHashCode] as AttributeTypesForSetter
                            for (format in tps.formats) {
                                val id = attribute.name + format.name
                                if (!identifiedAttributes.contains(id)) {
                                    identifiedAttributes.add(id)
                                    requiresSetter = true
                                }
                            }
                        }
                    }
                    if (requiresSetter) {
                        selectedSetters.add(setter)
                    }
                }
        return selectedSetters
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

        for(attrName in getAttributesToParameters(setter).keys) {
            val attr = attributes[attrName] as Attribute
            if (variableNames[setterHashCode] == null) {
                variableNames[setterHashCode] = mutableMapOf()
            }
            val typesPerSetter = attr.typesPerSetter[setterHashCode] as AttributeTypesForSetter
            for (format in typesPerSetter.formats) {
                val suffix = format.name
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
        getAttributesToParameters(setter).keys.forEach { attrName ->
            if (!processedVariables.contains(attrName)) {
                val attr = attributes[attrName] as Attribute
                selectedAttrs.add(attr)
                val variableName = setterVariableNames[attrName] as String
                valueLiterals.add(variableName)
                processedVariables.add(variableName)
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
            val attributeToParameters =  getAttributesToParameters(setter)
                    .filter { it.value == index }
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

    private fun getAttributesToParameters(setter: Setter): Map<String, Int> {
        return setter.callSignatureMaps.getCallSignatureMapUsedByAnOfTheseAttributes(attributes.values)
    }
}