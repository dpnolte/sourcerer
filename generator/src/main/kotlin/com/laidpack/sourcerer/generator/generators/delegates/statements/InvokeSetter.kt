package com.laidpack.sourcerer.generator.generators.delegates.statements

import android.content.Context
import com.laidpack.sourcerer.generator.generators.delegates.DelegateGeneratorBase
import com.laidpack.sourcerer.generator.generators.delegates.SingleAttributeAndSingleSetterGenerator
import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.Setter
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec
import javax.swing.text.View

class InvokeSetter(private val delegate: DelegateGeneratorBase, private val contextParam: ParameterSpec) {

    fun addAsStatement(
            builder: FunSpec.Builder,
            setter: Setter,
            attribute: Attribute,
            valueLiteral: String
    ) {
        return addAsStatement(
                builder,
                setter,
                mapOf(attribute.name to attribute),
                mapOf(attribute.name to valueLiteral)
        )
    }
    fun addAsStatement(
            builder: FunSpec.Builder,
            setter: Setter,
            attributes: Map<String, Attribute>,
            valueLiterals: Map<String, String>
    ) {
        val callSignatureMap = setter.callSignatureMaps.getCallSignatureMap(attributes)
        val reversedMap = callSignatureMap.entries.associateBy({ it.value }) { it.key }
        when {
            attributes.size == 1 && setter.hasPropertyName -> {
                builder.addStatement("%N = %L", setter.propertyName, valueLiterals.values.first())
            }
            setter.isField -> {
                builder.addStatement("%N = %L", setter.name, valueLiterals.values.first())
            }
            setter.parameters.size > 1 -> {
                var index = 0
                val parameters = setter.parameters.joinToString(", ") { parameter ->
                    val attributeName = reversedMap[index]
                    val result = when {
                        attributeName != null -> valueLiterals[attributeName] as String
                        parameter.defaultValue.isNotBlank() -> parameter.defaultValue
                        parameter.isNullable -> "null"
                        parameter.describedType == contextCanonicalName -> contextParam.name
                        parameter.describedType == viewCanonicalName -> "this"
                        else -> ""
                    }
                    index += 1
                    result
                }
                if (setter.isStaticSetter) {
                    builder.addStatement("%T.%N($parameters)", setter.scopeClassName as ClassName, setter.name)
                } else {
                    builder.addStatement("%N($parameters)", setter.name)
                }
            }
            else -> {
                if (setter.isStaticSetter) {
                    builder.addStatement("%T.%N($%L)", setter.scopeClassName as ClassName, setter.name,  valueLiterals.values.first())
                } else {
                    builder.addStatement("%N(%L)", setter.name, valueLiterals.values.first())
                }
            }
        }
    }

    companion object {
        private val contextCanonicalName = Context::class.java.canonicalName
        private val viewCanonicalName = View::class.java.canonicalName
    }
}