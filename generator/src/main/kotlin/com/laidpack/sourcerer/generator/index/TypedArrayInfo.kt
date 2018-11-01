package com.laidpack.sourcerer.generator.index

import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.expr.MethodCallExpr
import com.laidpack.sourcerer.generator.resources.StyleableAttributeFormat

data class TypedArrayInfo(val methods: Map<String, TypedArrayMethodInfo>) {

    fun getTypedArrayMethod(methodCallExpr: MethodCallExpr): TypedArrayMethodInfo? {
        return methods[methodCallExpr.nameAsString]
    }

    fun isTypedArrayGetter(methodCallExpr: MethodCallExpr): Boolean {
        val method = getTypedArrayMethod(methodCallExpr)
        return method != null
                && method.isResourceValueGetter
    }
    fun isTypedArrayMethod(methodCallExpr: MethodCallExpr): Boolean {
        return methods.containsKey(methodCallExpr.nameAsString)
    }

    fun getTypedArrayGetter(methodCallExpr: MethodCallExpr): TypedArrayMethodInfo {
        if (!methods.containsKey(methodCallExpr.nameAsString)) throw IllegalArgumentException("Method ${methodCallExpr.nameAsString} is not known as typed array method")
        val typedArrayMethod = methods[methodCallExpr.nameAsString] as TypedArrayMethodInfo
        if (!typedArrayMethod.isResourceValueGetter) {
            throw IllegalStateException("Method ${methodCallExpr.nameAsString} is not a resource value getter")
        }
        return typedArrayMethod
    }

    fun getDefaultValueFromTypedArrayGetterCall(methodCallExpr: MethodCallExpr): String? {
        val typedArrayMethod = getTypedArrayGetter(methodCallExpr)
        if (typedArrayMethod.hasDefaultValueParam) {
            if (typedArrayMethod.defaultValueParamIndex < 0 || methodCallExpr.arguments.size < typedArrayMethod.defaultValueParamIndex) {
                throw IndexOutOfBoundsException("Method has no default value parameter at ${typedArrayMethod.defaultValueParamIndex}")
            }
            val argument = methodCallExpr.arguments[typedArrayMethod.defaultValueParamIndex]
            if (argument.isLiteralStringValueExpr) {
                return argument.asLiteralStringValueExpr().value
            }
        }
        return null
    }
}


data class TypedArrayMethodInfo (
        val methodDeclaration: MethodDeclaration,
        val isResourceValueGetter: Boolean,
        val hasDefaultValueParam: Boolean,
        val defaultValueParamIndex: Int = -1,
        val valueUnit: String = "",
        val returnTypeCanonicalName: String = "",
        val returnsGuessedFormat: StyleableAttributeFormat
)
