package com.laidpack.sourcerer.generator.peeker

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.github.javaparser.ast.body.*
import com.github.javaparser.ast.body.Parameter
import com.github.javaparser.ast.expr.AnnotationExpr
import com.laidpack.sourcerer.generator.*
import com.laidpack.sourcerer.generator.resources.Widget


class ClassBodyPeeker {
    fun peek(classSymbolDescription: ClassSymbolDescription, abortOnClassNotFound: Boolean = true): ClassInfo {
        val memberNameToMethodNumberForCallablesWithAttributeSet = mutableMapOf<Pair<CallableType, String>, MutableList<Int>>()
        val memberNameToMethodNumberForMethodsWithAttributeBlockTag = mutableMapOf<String, MutableList<Int>>()
        val constructorDeclarations = mutableMapOf<String, MutableList<ConstructorInfo>>()
        val methodDeclarations = mutableMapOf<String, MutableList<MethodInfo>>()
        val fieldDeclarations = mutableMapOf<String, FieldDeclaration>()

        val classDeclaration = classSymbolDescription.classDeclarationProvider()
        val members = classDeclaration.members
        if (abortOnClassNotFound && members.isEmpty()) throw IllegalStateException("No members found for class ${classSymbolDescription.targetClassName}")

        for (member in members) {
            when (member) {
                is ConstructorDeclaration -> {
                    val constructorInfo = ConstructorInfo(member)
                    constructorDeclarations.addToValueList(member.nameAsString, constructorInfo)
                    if (hasCallableAnyAttributeSetParameter(member)) {
                        val index = constructorDeclarations[member.nameAsString]?.lastIndex as Int
                        memberNameToMethodNumberForCallablesWithAttributeSet.addToValueList(
                                Pair(CallableType.Constructor, member.nameAsString),
                                index
                        )
                    }
                }
                is MethodDeclaration -> {
                    val methodInfo = MethodInfo(member)
                    methodDeclarations.addToValueList(member.nameAsString, methodInfo)
                    val index = methodDeclarations[member.nameAsString]?.lastIndex as Int
                    if (hasCallableAnyAttributeSetParameter(member)) {
                        memberNameToMethodNumberForCallablesWithAttributeSet.addToValueList(
                                Pair(CallableType.Method, member.nameAsString),
                                index
                        )
                    }
                    if (hasMethodAnyJavaDocAttributeBlockTags(methodInfo)) {
                        memberNameToMethodNumberForMethodsWithAttributeBlockTag.addToValueList(member.nameAsString, index)
                    }
                }
                is FieldDeclaration -> {
                    member.variables.forEach { variableDeclaration ->
                        fieldDeclarations[variableDeclaration.nameAsString] = member
                    }

                }
            }
        }

        return ClassInfo(
                classSymbolDescription.targetClassName,
                classSymbolDescription.widget as Widget,
                classSymbolDescription.classCategory,
                classSymbolDescription.isViewGroup,
                classDeclaration,
                classSymbolDescription.superClassNames.filter {
                    val indexedClass = ClassRegistry[it] as XdClass
                    indexedClass.widget != null
                },
                memberNameToMethodNumberForCallablesWithAttributeSet,
                memberNameToMethodNumberForMethodsWithAttributeBlockTag,
                constructorDeclarations,
                methodDeclarations,
                fieldDeclarations,
                getConstructorExpression(classSymbolDescription.classCategory, classDeclaration, constructorDeclarations),
                classSymbolDescription.xdClass,
                classDeclaration.annotations.toList(),
                getIntDefinedAnnotations(classDeclaration)
        )
    }

    private fun hasCallableAnyAttributeSetParameter(callableDeclaration: CallableDeclaration<*>): Boolean {
        for (parameter in callableDeclaration.parameters) {
            if (hasParameterSimpleNameAttributeSet(parameter) && parameter.describeType() == attributeSetCanonicalName) {
                return true
            }
        }
        return false
    }

    private fun hasMethodAnyJavaDocAttributeBlockTags(methodInfo: MethodInfo): Boolean {
        return methodInfo.methodDeclaration.parameters.isNonEmpty
                && methodInfo.javadoc.blockTags.any { it.tagName == "attr"}
                && methodInfo.javadoc.blockTags.all { it.tagName != "hide" }
    }

    private fun <TKey, TValue> MutableMap<TKey, MutableList<TValue>>.addToValueList(key: TKey, value: TValue) {
        if (!this.containsKey(key)) {
            this[key] = mutableListOf(value)
        } else {
            (this[key] as MutableList<TValue>).add(value)
        }
    }

    private fun hasParameterSimpleNameAttributeSet(parameter: Parameter): Boolean {
        return parameter.type.isClassOrInterfaceType
                && parameter.type.asClassOrInterfaceType().nameAsString == attributeSetClassType.simpleName
    }

    private fun getConstructorExpression(
            classCategory: ClassCategory,
            classDeclaration: ClassOrInterfaceDeclaration,
            constructorDeclarations: MutableMap<String, MutableList<ConstructorInfo>>
    ): ConstructorExpression {
        val constructors = if (constructorDeclarations.isEmpty()) {
            classDeclaration.descendantsOfType(ConstructorDeclaration::class.java)
        } else {
            constructorDeclarations.values.flatten().map { it.constructorDeclaration }
        }
        when (classCategory) {
            ClassCategory.View -> {
                if (classDeclaration.isAbstract) return ViewConstructorExpression.AbstractView
                for (c in constructors.sortedBy { it.parameters.size }) {
                    if (!c.isPublic) continue
                    when {
                        c.parameters.size == 1
                                && c.parameters[0].describeType() == contextCanonicalName
                        -> return ViewConstructorExpression.ContextOnly
                        c.parameters.size == 2
                                && c.parameters[0].describeType() == contextCanonicalName
                                && c.parameters[1].describeType() == attributeSetCanonicalName
                        ->  return ViewConstructorExpression.ContextAndAttrs
                        c.parameters.size == 3
                                && c.parameters[0].describeType() == contextCanonicalName
                                && c.parameters[1].describeType() == attributeSetCanonicalName
                                && intDescribedTypes.contains(c.parameters[2].describeType())
                        -> return ViewConstructorExpression.ContextAttrsAndDefStyleAttr
                    }
                }
                throw IllegalStateException("${classDeclaration.nameAsString}'s constructor could not be determined (category: View)")
            }
            ClassCategory.LayoutParams -> {
                if (classDeclaration.isAbstract) return LayoutParamsConstructorExpression.AbstractLayoutParams
                for (c in constructors.sortedByDescending { it.parameters.size }) {
                    if (!c.isPublic) continue
                    when {
                        c.parameters.size == 2
                                && c.parameters.all { p -> intDescribedTypes.contains(p.describeType()) }
                        -> return LayoutParamsConstructorExpression.WidthAndHeight
                        c.parameters.size == 1
                                && c.parameters[0].describeType() == viewGroupLayoutParamCanonicalName
                        -> return LayoutParamsConstructorExpression.CopySource
                        c.parameters.size == 0
                        -> return LayoutParamsConstructorExpression.Empty
                    }
                }
                throw IllegalStateException("LayoutParam ${classDeclaration.nameAsString} class's constructor could not be determined")
            }
        }
    }

    private fun getIntDefinedAnnotations(classDeclaration: ClassOrInterfaceDeclaration): Map<String, AnnotationExpr> {
        val intDefinedAnnotations = mutableMapOf<String, AnnotationExpr>()
        val annotationsExpressions = classDeclaration.descendantsOfType(AnnotationExpr::class.java)
        for (annotationExpression in annotationsExpressions) {
            if (annotationExpression.nameAsString == "IntDef") {
                val declaration = annotationExpression.firstAncestorOfType(AnnotationDeclaration::class.java) as AnnotationDeclaration
                intDefinedAnnotations[declaration.nameAsString] = annotationExpression
            }
        }
        return intDefinedAnnotations
    }


    companion object {
        val attributeSetClassType = AttributeSet::class.java
        private val contextCanonicalName = Context::class.java.canonicalName
        private val attributeSetCanonicalName = attributeSetClassType.canonicalName
        private val viewGroupLayoutParamCanonicalName = ViewGroup.LayoutParams::class.java.canonicalName
        private val intDescribedTypes = setOf(
                "java.lang.Int",
                "int"
        )


    }
}


