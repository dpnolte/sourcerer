package com.laidpack.sourcerer.generator.generators.delegates.statements

import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.AttributeTypesForSetter
import com.laidpack.sourcerer.generator.generators.delegates.DelegateGeneratorBase
import com.laidpack.sourcerer.generator.generators.delegates.literals.GetGetterLiteral
import com.laidpack.sourcerer.generator.generators.delegates.literals.GetValueLiteral
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
        val valueLiteralGetter = GetValueLiteral(delegate, attributesParam)
        val valueLiteralWrapper = if (valueLiteral != null) {
            valueLiteralGetter.asValuePerLiteral(valueLiteral, attribute, typesForThisSetter, typesForThisSetter.formats.first())
        } else valueLiteralGetter.asSingleFormattedValue(attribute, typesForThisSetter, typesForThisSetter.formats.first())

        if (defaultToGetterValue) {
            val getterLiteralWrapper = GetGetterLiteral(delegate)
                    .asName(attribute, typesForThisSetter)
            this.addStatement("val %N = ${valueLiteralWrapper.expression} ?: ${getterLiteralWrapper.expression}",
                    variableName ?: attribute.localVariableName,
                    *valueLiteralWrapper.args.toTypedArray(),
                    *getterLiteralWrapper.args.toTypedArray()
            )

        } else {
            this.addStatement("val %N = ${valueLiteralWrapper.expression}",
                    variableName ?: attribute.localVariableName,
                    *valueLiteralWrapper.args.toTypedArray()
            )
        }

        return this
    }
}