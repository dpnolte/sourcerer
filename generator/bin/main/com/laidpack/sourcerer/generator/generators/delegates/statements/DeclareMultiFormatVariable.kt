package com.laidpack.sourcerer.generator.generators.delegates.statements

import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.AttributeTypesForSetter
import com.laidpack.sourcerer.generator.generators.delegates.DelegateGeneratorBase
import com.laidpack.sourcerer.generator.generators.delegates.literals.GetGetterLiteral
import com.laidpack.sourcerer.generator.generators.delegates.literals.GetValueLiteral
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec

class DeclareMultiFormatVariable(private val delegate: DelegateGeneratorBase, private val attributesParam: ParameterSpec) {

    fun addDeclaredVar(
            builder: FunSpec.Builder,
            attribute: Attribute,
            typesForThisSetter: AttributeTypesForSetter,
            defaultToGetterValue: Boolean = false,
            variableName: String? = null
        ) {
        builder.addLocalMultiFormatVar(attribute, typesForThisSetter, defaultToGetterValue, variableName)
    }
    private fun FunSpec.Builder.addLocalMultiFormatVar(
            attribute: Attribute,
            typesForThisSetter: AttributeTypesForSetter,
            defaultToGetterValue: Boolean = false,
            variableName: String? = null
    ): FunSpec.Builder {
        // all possible formats should be converted to same type, the parameter type..
        if (typesForThisSetter.formats.size == 1) {
            this.addLocalVarForSetterWithOneFormat(
                    attribute,
                    typesForThisSetter,
                    defaultToGetterValue,
                    variableName
            )
        } else {
            this.addVarForSetterWithMultipleFormats(
                    attribute,
                    typesForThisSetter,
                    defaultToGetterValue,
                    variableName
            )
        }

        return this
    }

    private fun FunSpec.Builder.addLocalVarForSetterWithOneFormat(
            attribute: Attribute,
            typesForThisSetter: AttributeTypesForSetter,
            defaultToGetterValue: Boolean = false,
            variableName: String? = null
    ): FunSpec.Builder {
        val format = typesForThisSetter.formats.first()
        val valueLiteralWrapper = GetValueLiteral(delegate, attributesParam).asMultiFormattedValue(
                attribute,
                typesForThisSetter,
                format
        )
        if (defaultToGetterValue) {
            val getterLiteralWrapper = GetGetterLiteral(delegate)
                    .asName(attribute, typesForThisSetter)
            this.addStatement("val %L = if (%N.%N.%N) ${valueLiteralWrapper.expression} else ${getterLiteralWrapper.expression}",
                    variableName ?: attribute.localVariableName,
                    attributesParam.name,
                    attribute.name,
                    "has${format.name}",
                    *valueLiteralWrapper.args.toTypedArray(),
                    *getterLiteralWrapper.args.toTypedArray()
            )
        } else {
            this.addStatement("val %L = ${valueLiteralWrapper.expression}",
                    variableName ?: attribute.localVariableName,
                    *valueLiteralWrapper.args.toTypedArray()
            )
        }
        return this
    }

    private fun FunSpec.Builder.addVarForSetterWithMultipleFormats(
            attribute: Attribute,
            typesForThisSetter: AttributeTypesForSetter,
            defaultToGetterValue: Boolean = false,
            variableName: String? = null
    ): FunSpec.Builder {

        this.beginControlFlow("val %L = when", variableName ?: attribute.localVariableName)
        typesForThisSetter.formats.forEachIndexed { index, format ->
            val valueLiteralWrapper = GetValueLiteral(delegate, attributesParam).asMultiFormattedValue(
                    attribute,
                    typesForThisSetter,
                    format
            )
            val isLastFormat = typesForThisSetter.formats.size == index + 1
            val args = mutableListOf<Any>()
            val condition = if (!isLastFormat || defaultToGetterValue) {
                args.add(attributesParam.name)
                args.add(attribute.name)
                args.add("has${format.name}")
                "%N.%N.%N"
            } else {
                "else"
            }
            args.addAll(valueLiteralWrapper.args)
            this.addStatement("$condition -> ${valueLiteralWrapper.expression}", *args.toTypedArray())
        }

        if (defaultToGetterValue) {
            val getterLiteralWrapper = GetGetterLiteral(delegate)
                    .asName(attribute, typesForThisSetter)
            this.addStatement("else -> ${getterLiteralWrapper.expression}", *getterLiteralWrapper.args.toTypedArray())

        }
        this.endControlFlow()

        return this
    }

}