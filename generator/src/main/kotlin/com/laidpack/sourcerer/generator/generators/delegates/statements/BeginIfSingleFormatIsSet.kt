package com.laidpack.sourcerer.generator.generators.delegates.statements

import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.generators.delegates.DelegateGeneratorBase
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec

class BeginIfSingleFormatIsSet (private val delegate: DelegateGeneratorBase, private val attributesParam: ParameterSpec) {

    fun addAsLet(builder: FunSpec.Builder, attribute: Attribute) {
        val condition = getCondition(attribute, asLet = true)
        builder.beginControlFlow("${condition.expression}?.let", *condition.args.toTypedArray())
    }

    fun getCondition(attribute: Attribute, asLet: Boolean = true): Condition {
        val compareWith = if (asLet) "" else " != null"
        return Condition("%N.%N$compareWith", listOf(attributesParam.name, attribute.name))
    }

}