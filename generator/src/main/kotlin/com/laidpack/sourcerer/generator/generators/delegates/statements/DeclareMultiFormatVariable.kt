package com.laidpack.sourcerer.generator.generators.delegates.statements

import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.AttributeTypesForSetter
import com.laidpack.sourcerer.generator.generators.FormatEnumGenerator
import com.laidpack.sourcerer.generator.generators.delegates.DelegateGeneratorBase
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
        val valueForFormat = delegate.getMultiFormattedValue(
                attribute,
                typesForThisSetter,
                format
        )
        if (defaultToGetterValue) {
            this.addStatement("val %L = if (%N.%N.%N) ${valueForFormat.expression} else %N",
                    variableName ?: attribute.localVariableName,
                    attributesParam.name,
                    attribute.name,
                    "has${format.name}",
                    *valueForFormat.args.toTypedArray(),
                    attribute.getters.first().propertyName
            )
        } else {
            this.addStatement("val %L = ${valueForFormat.expression}",
                    variableName ?: attribute.localVariableName,
                    *valueForFormat.args.toTypedArray()
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
            val valueForFormat = delegate.getMultiFormattedValue(
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
            args.addAll(valueForFormat.args)
            this.addStatement("$condition -> ${valueForFormat.expression}", *args.toTypedArray())
        }

        if (defaultToGetterValue) {
            this.addStatement("else -> %N", attribute.getters.first().propertyName)

        }
        this.endControlFlow()

        return this
    }


    companion object {
        private val formatEnumClassName = FormatEnumGenerator.formatEnumClassName
    }
}