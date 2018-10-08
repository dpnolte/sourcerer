package com.laidpack.sourcerer.generator.generators.delegates.literals

import com.laidpack.sourcerer.generator.generators.delegates.DelegateGeneratorBase
import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.AttributeTypesForSetter
import com.laidpack.sourcerer.generator.target.Getter

class GetGetterLiteral(private val delegate: DelegateGeneratorBase) {
    data class GetterLiteralWrapper(val expression: String, val args: MutableList<Any>)
    fun asName(
            attribute: Attribute,
            typesForThisSetter: AttributeTypesForSetter
    ): GetterLiteralWrapper {
        val getter = attribute.getters.first() // TODO: handle multiple getters?
        return asName(attribute, getter, typesForThisSetter)
    }
    fun asName(
            attribute: Attribute,
            getter: Getter,
            typesForThisSetter: AttributeTypesForSetter
    ): GetterLiteralWrapper {
        val valueProvider = {a: MutableList<Any> ->
            a.add(getter.propertyName)
            "%N"
        }
        val args = mutableListOf<Any>()
        val expression = delegate.addTransformingMethodIfNeeded(
                valueProvider,
                attribute,
                getter,
                typesForThisSetter,
                args
        )
        return GetterLiteralWrapper(expression, args)
    }
}