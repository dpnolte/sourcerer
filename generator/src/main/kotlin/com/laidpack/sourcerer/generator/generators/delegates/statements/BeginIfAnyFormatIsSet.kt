package com.laidpack.sourcerer.generator.generators.delegates.statements

import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.AttributeTypesForSetter
import com.laidpack.sourcerer.generator.generators.FormatEnumGenerator
import com.laidpack.sourcerer.generator.generators.delegates.DelegateGeneratorBase
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec

data class Condition(val expression : String, val args: List<Any>)
class BeginIfAnyFormatIsSet (private val delegate: DelegateGeneratorBase, private val attributesParam: ParameterSpec) {

    fun addAsIf(builder: FunSpec.Builder, attribute: Attribute, typesForThisSetter: AttributeTypesForSetter) {
        val condition = getCondition(attribute, typesForThisSetter)
        builder.beginControlFlow("if (${condition.expression})", *condition.args.toTypedArray())
    }

    fun addAsWhenEntry(builder: FunSpec.Builder, attribute: Attribute, typesForThisSetter: AttributeTypesForSetter) {
        val condition = getCondition(attribute, typesForThisSetter)
        builder.beginControlFlow("${condition.expression} -> ", *condition.args.toTypedArray())
    }

    fun getCondition(attribute: Attribute, typesForThisSetter: AttributeTypesForSetter? = null): Condition {
        val args = mutableListOf<Any>()
        val conditions = mutableListOf<String>()
        val formats = typesForThisSetter?.formats ?: attribute.formats
        for (format in formats) {
            conditions.add("%N.%N.%N")
            args.add(attributesParam.name)
            args.add(attribute.name)
            args.add("has${format.name}")
        }

        return Condition(conditions.joinToString(" || "), args)
    }

    companion object {
        private val formatEnumClassName = FormatEnumGenerator.formatEnumClassName
    }
}