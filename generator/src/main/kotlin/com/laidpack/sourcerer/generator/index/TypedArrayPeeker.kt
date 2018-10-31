package com.laidpack.sourcerer.generator.index

import android.content.res.TypedArray
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.body.MethodDeclaration
import com.laidpack.sourcerer.generator.resources.StyleableAttributeFormat

class TypedArrayPeeker(private val compilationUnit: CompilationUnit) {
    fun peek(): TypedArrayInfo {
        val optionalClassDeclaration = compilationUnit.getClassByName(typedArrayClassType.simpleName)
        if (!optionalClassDeclaration.isPresent) throw IllegalArgumentException("Provided compilation unit contains no class '${typedArrayClassType.qualifiedName}'")

        val classDeclaration = optionalClassDeclaration.get()
        val methods = mutableMapOf<String, TypedArrayMethodInfo>()
        classDeclaration.members.forEach { member ->
            if (member is MethodDeclaration) {
                var anyParamWithStylableResAnnotation = false
                var defaultValueParamIndex = -1
                var valueUnit = ""
                //val hasDefaultValueParam = methodDeclaration.parameters.any()
                member.parameters.forEachIndexed { index, param ->
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
                val isResourceValueGetter = anyParamWithStylableResAnnotation && member.nameAsString.startsWith("get")
                val hasDefaultValueParam = defaultValueParamIndex != -1 && isResourceValueGetter
                var guessedFormat = StyleableAttributeFormat.Unspecified
                for (keyWordForFormat in keyWordToFormat.keys) {
                    if (member.nameAsString.contains(keyWordForFormat)) {
                        guessedFormat = keyWordToFormat[keyWordForFormat] as StyleableAttributeFormat
                        break
                    }
                }

                methods[member.nameAsString] = TypedArrayMethodInfo(
                        member,
                        isResourceValueGetter,
                        hasDefaultValueParam,
                        if (hasDefaultValueParam) defaultValueParamIndex else -1,
                        if (hasDefaultValueParam) valueUnit else "",
                        if (isResourceValueGetter) member.resolve().returnType.describe() else "",
                        guessedFormat
                )
            }
        }

        return TypedArrayInfo(methods)
    }

    companion object {
        val typedArrayClassType = TypedArray::class
        private val keyWordToFormat = enumValues<StyleableAttributeFormat>().associate {
            Pair("get${it.name}", it)
        }
    }
}

