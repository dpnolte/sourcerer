package com.laidpack.sourcerer.generator.generators.delegates.statements

import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.AttributeTypesForSetter
import com.laidpack.sourcerer.generator.target.Setter
import com.laidpack.sourcerer.generator.generators.delegates.DelegateGeneratorBase
import com.laidpack.sourcerer.generator.generators.delegates.literals.GetGetterLiteral
import com.laidpack.sourcerer.generator.target.Getter
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec

class BeginIfAnyValueIsDifferentThanCurrent (private val delegate: DelegateGeneratorBase, private val attributesParam: ParameterSpec) {

    fun addAsIf(
            builder: FunSpec.Builder,
            attribute: Attribute,
            setter: Setter,
            typesForThisSetter: AttributeTypesForSetter,
            valueLiteral: String? = null,
            getters: List<Getter>? = null
    ) {
        val condition = getCondition(attribute,setter,typesForThisSetter,valueLiteral, getters)
        builder.beginControlFlow("if (${condition.expression})", *condition.args.toTypedArray())
    }
    fun addAsIf(builder: FunSpec.Builder, attributes: List<Attribute>, setter: Setter, valueLiterals: List<String?>? = null) {
        val setterHashCode = setter.hashCode()
        val expressions = mutableListOf<String>()
        val args = mutableListOf<Any>()
        attributes.forEachIndexed { index, attribute ->
            val typesForThisSetter = attribute.typesPerSetter[setterHashCode] as AttributeTypesForSetter
            val literal = if (valueLiterals != null && index < valueLiterals.size) {
                valueLiterals[index]
            } else null
            val condition = getCondition(attribute,setter, typesForThisSetter, literal)
            expressions.add(condition.expression)
            args.addAll(condition.args)
        }
        val expression = expressions.joinToString(" || ")
        builder.beginControlFlow("if ($expression)", *args.toTypedArray())
    }

    private fun getCondition(
            attribute: Attribute,
            setter: Setter,
            typesPerSetter: AttributeTypesForSetter,
            valueLiteral: String? = null,
            providedGetters: List<Getter>? = null
    ): Condition {
        if (!setter.isField) {
            val parameter = setter.parameters[setter.callSignatureMaps[attribute.name]]
            val toList = if (parameter.isVarArgs) "listOf(" else ""
            val toListEnd = if (parameter.isVarArgs) ")" else ""
            val args = mutableListOf<Any>()
            val conditionCode = mutableListOf<String>()
            val getGetterLiteral = GetGetterLiteral(delegate)
            val getters = providedGetters ?: attribute.getters.distinctBy { it.propertyName }
            for(getter in getters) {
                val getterLiteralWrapper = getGetterLiteral.asName(attribute, getter, typesPerSetter)
                args.addAll(getterLiteralWrapper.args)
                val literal = if(valueLiteral != null) {
                    "$toList$valueLiteral$toListEnd"
                } else "$toList${attributesParam.name}.${attribute.name}$toListEnd"
                conditionCode.add("${getterLiteralWrapper.expression} != $literal")
            }
            return Condition(conditionCode.joinToString(" || "), args)
        } else {
            val args = mutableListOf<Any>()
            val conditionCode = mutableListOf<String>()
            for(getter in attribute.getters) {
                if (valueLiteral != null) {
                    args.add(valueLiteral)
                    args.add(getter.propertyName)
                    conditionCode.add("%N != %N")
                } else {
                    args.add(attributesParam.name)
                    args.add(attribute.name)
                    args.add(getter.propertyName)
                    conditionCode.add("%N.%N != %N")
                }
            }
            return Condition(conditionCode.joinToString(" || "), args)
        }
    }
}