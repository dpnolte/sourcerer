package com.laidpack.sourcerer.generator.index

import android.content.res.TypedArray
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.body.MethodDeclaration
import com.laidpack.sourcerer.generator.descendantsOfType

class TypedArrayPeeker(private val compilationUnit: CompilationUnit) {
    fun peek(): TypedArrayInfo {
        val optionalClassDeclaration = compilationUnit.getClassByName(typedArrayClassType.simpleName)
        if (!optionalClassDeclaration.isPresent) throw IllegalArgumentException("Provided compilation unit contains no class '${typedArrayClassType.qualifiedName}'")

        val classDeclaration = optionalClassDeclaration.get()
        val methods = mutableMapOf<String, TypedArrayMethodInfo>()
        classDeclaration.descendantsOfType(MethodDeclaration::class.java).forEach { methodDeclaration ->
            var anyParamWithStylableResAnnotation = false
            var defaultValueParamIndex = -1
            var valueUnit = ""
            //val hasDefaultValueParam = methodDeclaration.parameters.any()
            methodDeclaration.parameters.forEachIndexed { index, param ->
                if (!anyParamWithStylableResAnnotation) {
                    anyParamWithStylableResAnnotation = param.annotations.any { annotationExpr -> annotationExpr.nameAsString == "StyleableRes" }
                }
                if (param.nameAsString == "defValue") {
                    defaultValueParamIndex = index
                    if (param.annotations.isNonEmpty) {
                        valueUnit = param.annotations.joinToString(",") { it.nameAsString }
                    }
                }

            }

            // check if it is a resource value getter by:
            // - a) method starts with get
            // - b) presence of $StyleableRes annotation
            val isResourceValueGetter = anyParamWithStylableResAnnotation && methodDeclaration.nameAsString.startsWith("get")
            val hasDefaultValueParam = defaultValueParamIndex != -1 && isResourceValueGetter


            methods[methodDeclaration.nameAsString] = TypedArrayMethodInfo(
                    methodDeclaration,
                    isResourceValueGetter,
                    hasDefaultValueParam,
                    if (hasDefaultValueParam) defaultValueParamIndex else -1,
                    if (hasDefaultValueParam) valueUnit else "",
                    if (isResourceValueGetter) methodDeclaration.resolve().returnType.describe() else ""
            )
        }

        return TypedArrayInfo(methods)
    }

    companion object {
        val typedArrayClassType = TypedArray::class
    }
}

