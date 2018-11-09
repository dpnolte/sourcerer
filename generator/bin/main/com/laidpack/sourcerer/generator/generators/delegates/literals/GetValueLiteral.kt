package com.laidpack.sourcerer.generator.generators.delegates.literals

import com.laidpack.sourcerer.generator.generators.delegates.DelegateGeneratorBase
import com.laidpack.sourcerer.generator.resources.StyleableAttributeFormat
import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.AttributeTypesForSetter
import com.laidpack.sourcerer.generator.target.Getter
import com.squareup.kotlinpoet.ParameterSpec

class GetValueLiteral(private val delegate: DelegateGeneratorBase, private val attributesParam: ParameterSpec) {

    data class ValueLiteralWrapper(val expression: String, val args: MutableList<Any>)
    fun asMultiFormattedValue(attribute: Attribute, typesForThisSetter: AttributeTypesForSetter, format: StyleableAttributeFormat): ValueLiteralWrapper {
        val valueProvider = {a: MutableList<Any> ->
            a.add(attributesParam.name)
            a.add(attribute.name)
            a.add(format.name.decapitalize())
            "%N.%N.%N"
        }
        val args = mutableListOf<Any>()
        val expression = delegate.addTransformingMethodIfNeeded(valueProvider, attribute, typesForThisSetter, format, args)
        return ValueLiteralWrapper(expression, args)
    }

    fun asSingleFormattedValue(attribute: Attribute, typesForThisSetter: AttributeTypesForSetter, format: StyleableAttributeFormat): ValueLiteralWrapper {
        val valueProvider = {a: MutableList<Any> ->
            if (typesForThisSetter.hasEnumAsAttributeType || typesForThisSetter.hasFlagsAsAttributeType) {
                a.add(attributesParam.name)
                a.add(attribute.name)
                "%L.%L.value"
            } else {
                a.add(attributesParam.name)
                a.add(attribute.name)
                "%L.%L"
            }
        }
        val args = mutableListOf<Any>()
        val expression = delegate.addTransformingMethodIfNeeded(valueProvider, attribute, typesForThisSetter, format, args)
        return ValueLiteralWrapper(expression, args)
    }

    fun asValuePerLiteral(valueLiteral: String, attribute: Attribute, typesForThisSetter: AttributeTypesForSetter, format: StyleableAttributeFormat): ValueLiteralWrapper {
        val valueProvider = {a: MutableList<Any> ->
            a.add(valueLiteral)
            "%L"
        }
        val args = mutableListOf<Any>()
        val expression = delegate.addTransformingMethodIfNeeded(
                valueProvider,
                attribute,
                typesForThisSetter,
                typesForThisSetter.formats.first(),
                args
        )
        return ValueLiteralWrapper(expression, args)
    }
}