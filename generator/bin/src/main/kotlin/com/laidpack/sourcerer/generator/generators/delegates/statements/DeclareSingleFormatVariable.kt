package com.laidpack.sourcerer.generator.generators.delegates.statements

import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.AttributeTypesForSetter
import com.laidpack.sourcerer.generator.generators.delegates.DelegateGeneratorBase
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec

class DeclareSingleFormatVariable(private val delegate: DelegateGeneratorBase, private val attributesParam: ParameterSpec) {
    fun addDeclaredVar(
            builder: FunSpec.Builder,
            attribute: Attribute,
            typesForThisSetter: AttributeTypesForSetter,
            valueLiteral: String? = null,
            defaultToGetterValue: Boolean = false,
            variableName: String? = null
        ) {
        builder.addLocalVar(
                attribute,
                typesForThisSetter,
                valueLiteral,
                defaultToGetterValue,
                variableName
        )
    }

    private fun FunSpec.Builder.addLocalVar(
            attribute: Attribute,
            typesForThisSetter: AttributeTypesForSetter,
            valueLiteral: String? = null,
            defaultToGetterValue: Boolean = false,
            variableName: String? = null
    ): FunSpec.Builder {
        val valueFormat = if (valueLiteral != null) {
            delegate.getValuePerLiteral(valueLiteral, attribute, typesForThisSetter, typesForThisSetter.formats.first())
        } else delegate.getSingleFormattedValue(attribute, typesForThisSetter, typesForThisSetter.formats.first())

        if (defaultToGetterValue) {
            this.addStatement("val %N = ${valueFormat.expression} ?: %N",
                    variableName ?: attribute.localVariableName,
                    *valueFormat.args.toTypedArray(),
                    attribute.getters.first().propertyName
            )

        } else {
            this.addStatement("val %N = ${valueFormat.expression}",
                    variableName ?: attribute.localVariableName,
                    *valueFormat.args.toTypedArray()
            )
        }

        return this
    }
}