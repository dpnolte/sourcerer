package com.laidpack.sourcerer.generator.peeker

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.github.javaparser.JavaParser
import com.github.javaparser.ast.body.*
import com.github.javaparser.ast.body.Parameter
import com.github.javaparser.ast.expr.AnnotationExpr
import com.github.javaparser.javadoc.Javadoc
import com.github.javaparser.javadoc.description.JavadocDescription
import com.laidpack.sourcerer.generator.*
import com.laidpack.sourcerer.generator.resources.StyleableAttributeManager


class SourcePeeker(
        private val attrsXmlManager: StyleableAttributeManager
) {
    fun peek(resolvedClass: SymbolResolvedClass, abortOnClassNotFound: Boolean = true): ClassInfo? {
        val methodLikeWithAttributeSetParamKeys = mutableMapOf<String, MutableList<Int>>()
        val methodsWithAttributeBlockTagKeys = mutableMapOf<String, MutableList<Int>>()
        val constructorDeclarations = mutableMapOf<String, MutableList<ConstructorInfo>>()
        val methodDeclarations = mutableMapOf<String, MutableList<MethodInfo>>()
        val fieldDeclarations = mutableMapOf<String, FieldDeclaration>()
        var intDefinedAnnotations = mapOf<String, AnnotationExpr>()

        // only analyze class body if we have an associated widget and any attributes defined
        val classDeclaration = resolvedClass.classDeclarationProvider()
        if (resolvedClass.widget != null ) {
            if (attrsXmlManager.hasAttributesDefined(resolvedClass.targetClassName, resolvedClass.widget)) {
                val bodyDeclarations = classDeclaration.getBodyDeclarationsOfThisClass()
                if (abortOnClassNotFound && bodyDeclarations.isEmpty()) throw IllegalStateException("No body expressions found for class ${resolvedClass.targetClassName}")

                bodyDeclarations.forEach { bodyDeclaration ->
                    when (bodyDeclaration) {
                        is ConstructorDeclaration -> addConstructorDeclaration(
                                bodyDeclaration,
                                constructorDeclarations,
                                methodLikeWithAttributeSetParamKeys
                        )
                        is MethodDeclaration -> addMethodDeclaration(
                                bodyDeclaration,
                                methodDeclarations,
                                methodLikeWithAttributeSetParamKeys,
                                methodsWithAttributeBlockTagKeys
                        )
                        is FieldDeclaration -> {
                            bodyDeclaration.variables.forEach { variableDeclaration ->
                                fieldDeclarations[variableDeclaration.nameAsString] = bodyDeclaration
                            }

                        }
                    }
                }
                intDefinedAnnotations = getIntDefinedAnnotations(classDeclaration)

            }

            return ClassInfo(
                    resolvedClass.targetClassName,
                    resolvedClass.widget,
                    resolvedClass.classCategory,
                    resolvedClass.isViewGroup,
                    classDeclaration,
                    resolvedClass.superClassNames.filter {
                        val indexedClass = ClassRegistry[it] as IndexedClass
                        indexedClass.widget != null
                    },
                    methodLikeWithAttributeSetParamKeys,
                    methodsWithAttributeBlockTagKeys,
                    constructorDeclarations,
                    methodDeclarations,
                    fieldDeclarations,
                    getConstructorExpression(resolvedClass.classCategory, classDeclaration, constructorDeclarations),
                    resolvedClass.indexedClass,
                    classDeclaration.annotations.toList(),
                    intDefinedAnnotations
            )
        }

        return null
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


    private fun addConstructorDeclaration(
            constructorDeclaration: ConstructorDeclaration,
            constructorDeclarations: MutableMap<String, MutableList<ConstructorInfo>>,
            methodLikeWithAttributeSetParamKeys: MutableMap<String, MutableList<Int>>
    ) {
        if (constructorDeclaration.parentNode.get() !is ClassOrInterfaceDeclaration) {
            return
        }
        val resolvedConstructorDeclaration = constructorDeclaration.resolve()
        if (!constructorDeclarations.containsKey(constructorDeclaration.nameAsString)) {
            constructorDeclarations[constructorDeclaration.nameAsString] = mutableListOf()
        }

        val constructorNumber = constructorDeclarations[constructorDeclaration.nameAsString]!!.size
        val constructorInfo = ConstructorInfo(resolvedConstructorDeclaration, constructorDeclaration)

        constructorDeclarations[constructorDeclaration.nameAsString]!!.add(constructorInfo)
        for (index in 0 until resolvedConstructorDeclaration.numberOfParams) {
            val parameter = constructorDeclaration.parameters[index]
            if (isParameterTypeNameEqualToAttributeSet(parameter)) {
                val indexedClass = ClassSymbolResolver.resolveClassOrInterfaceType(parameter.type.asClassOrInterfaceType())
                if (indexedClass.targetClassName.canonicalName == attributeSetClassType.canonicalName) {
                    if (!methodLikeWithAttributeSetParamKeys.containsKey(constructorDeclaration.nameAsString)) {
                        methodLikeWithAttributeSetParamKeys[constructorDeclaration.nameAsString] = mutableListOf()
                    }
                    methodLikeWithAttributeSetParamKeys[constructorDeclaration.nameAsString]!!.add(constructorNumber)
                    break
                }
            }
        }
    }

    private fun addMethodDeclaration(
            methodDeclaration: MethodDeclaration,
            methodDeclarations: MutableMap<String, MutableList<MethodInfo>>,
            methodLikeWithAttributeSetParamKeys: MutableMap<String, MutableList<Int>>,
            methodsWithAttributeBlockTagKeys: MutableMap<String, MutableList<Int>>
    ) {
        val resolvedMethodDeclaration = methodDeclaration.resolve()
        if (!methodDeclarations.containsKey(methodDeclaration.nameAsString)) {
            methodDeclarations[methodDeclaration.nameAsString] = mutableListOf()
        }

        val methodDeclarationList = methodDeclarations[methodDeclaration.nameAsString] as MutableList<MethodInfo>
        val methodNumber = methodDeclarationList.size
        val javadoc = if (methodDeclaration.comment.isPresent) {
            JavaParser.parseJavadoc(methodDeclaration.comment.get().toString())
        } else {
            Javadoc(JavadocDescription())
        }

        methodDeclarationList.add(MethodInfo(resolvedMethodDeclaration, methodDeclaration, javadoc))
        for (index in 0 until resolvedMethodDeclaration.numberOfParams) {
            val parameter = methodDeclaration.parameters[index]
            if (isParameterTypeNameEqualToAttributeSet(parameter)) {
                val indexedClass = ClassSymbolResolver.resolveClassOrInterfaceType(parameter.type.asClassOrInterfaceType())
                if (indexedClass.targetClassName.canonicalName == attributeSetClassType.canonicalName) {
                    if (!methodLikeWithAttributeSetParamKeys.containsKey(methodDeclaration.nameAsString)) {
                        methodLikeWithAttributeSetParamKeys[methodDeclaration.nameAsString] = mutableListOf()
                    }
                    methodLikeWithAttributeSetParamKeys[methodDeclaration.nameAsString]!!.add(methodNumber)
                    break
                }
            }
        }

        if (methodDeclaration.parameters.isNonEmpty && javadoc.blockTags.any { it.tagName == "attr"} && javadoc.blockTags.all { it.tagName != "hide" }) {
            if (!methodsWithAttributeBlockTagKeys.containsKey(methodDeclaration.nameAsString)) {
                methodsWithAttributeBlockTagKeys[methodDeclaration.nameAsString] = mutableListOf()
            }
            methodsWithAttributeBlockTagKeys[methodDeclaration.nameAsString]!!.add(methodNumber)
        }
    }

    private fun isParameterTypeNameEqualToAttributeSet(parameter: Parameter): Boolean {
        return parameter.type.isClassOrInterfaceType
                && parameter.type.asClassOrInterfaceType().nameAsString == attributeSetClassType.simpleName
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


