package com.laidpack.sourcerer.generator.index

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.github.javaparser.ast.body.*
import com.github.javaparser.ast.body.Parameter
import com.github.javaparser.ast.expr.AnnotationExpr
import com.github.javaparser.resolution.UnsolvedSymbolException
import com.laidpack.sourcerer.generator.*
import kotlinx.dnq.query.any
import kotlinx.dnq.query.size
import kotlinx.dnq.query.toList
import kotlin.system.measureTimeMillis


object TypeBodyPeeker {
    val attributeSetClassType = AttributeSet::class.java
    private val contextCanonicalName = Context::class.java.canonicalName
    private val attributeSetCanonicalName = attributeSetClassType.canonicalName
    private val viewGroupLayoutParamCanonicalName = ViewGroup.LayoutParams::class.java.canonicalName
    private val intDescribedTypes = setOf(
            "java.lang.Int",
            "int"
    )

    fun peek(
            typeDeclaration: TypeDeclaration<*>,
            xdDeclaredType: XdDeclaredType,
            abortOnClassNotFound: Boolean = true
        ) {
        ensureSuperClassBodiesAreResolvedFirst(xdDeclaredType)
        resolveBody(typeDeclaration, xdDeclaredType, abortOnClassNotFound)
    }

    private fun resolveBody(
            typeDeclaration: TypeDeclaration<*>,
            xdDeclaredType: XdDeclaredType,
            abortOnClassNotFound: Boolean = true
    ) {
        var methodsCount = 0
        var constructorsCount = 0
        var fieldsCount = 0
        var enumEntriesCount = 0
        val elapsedTime = measureTimeMillis {
            clearAnyBodyExpressions(xdDeclaredType)
            val members = typeDeclaration.members
            val constructorDeclarations = mutableListOf<ConstructorDeclaration>()
            val fieldDeclarations = mutableMapOf<String, FieldDeclaration>()
            val intDefAnnotationExpressions = mutableListOf<AnnotationExpr>()
            if (abortOnClassNotFound && members.isEmpty()) throw IllegalStateException("No members found for class ${xdDeclaredType.targetClassName}")
            val classCategory = Store.transactional { xdDeclaredType.classCategory?.toEnum() }
            members.forEachIndexed { index, member ->
                when (member) {
                    is ConstructorDeclaration -> {
                        Store.transactional {
                            xdDeclaredType.constructors.add(
                                    XdConstructor.createFromConstructorDeclaration(
                                            member,
                                            hasCallableAnyAttributeSetParameter(member),
                                            index
                                    )
                            )
                        }
                        constructorDeclarations.add(member)
                        constructorsCount += 1
                    }
                    is MethodDeclaration -> {
                        Store.transactional {
                            val method = XdMethod.createFromMethodDeclaration(
                                    member,
                                    hasCallableAnyAttributeSetParameter(member),
                                    index,
                                    classCategory,
                                    xdDeclaredType.targetClassName
                            )
                            xdDeclaredType.declaredMethods.add(method)
                            xdDeclaredType.methods.add(method)
                        }
                        methodsCount += 1
                    }
                    is FieldDeclaration -> {
                        Store.transactional {
                            val fields = XdField.createFromFieldDeclaration(
                                    member,
                                    index,
                                    classCategory,
                                    xdDeclaredType.targetClassName
                            )
                            for (field in fields) {
                                xdDeclaredType.declaredFields.add(field)
                                xdDeclaredType.fields.add(field)
                            }
                        }
                        for (variableDeclarator in member.variables) {
                            fieldDeclarations[variableDeclarator.nameAsString] = member
                            fieldsCount += 1
                        }
                    }
                    is AnnotationDeclaration -> {
                        val intDefAnnotationExpression = member.firstDescendantOfType(
                                AnnotationExpr::class.java
                        ) { a -> a.nameAsString == "IntDef"}
                        if (intDefAnnotationExpression != null) {
                            intDefAnnotationExpressions.add(intDefAnnotationExpression)
                        }
                    }
                }
            }

            Store.transactional {
                if (typeDeclaration is EnumDeclaration) {
                    typeDeclaration.entries.forEachIndexed { index, enumConstantDeclaration ->
                        val xdEnumEntry = XdEnumEntry.createFromEnumConstantDeclaration(
                                enumConstantDeclaration, index
                        )
                        xdDeclaredType.enumEntries.add(xdEnumEntry)
                        enumEntriesCount += 1
                    }
                }
                this.addMembersAndFieldsFromSuperClass(xdDeclaredType)
                if (classCategory != null) {
                    this.addConstructorExpressionToClass(
                            xdDeclaredType,
                            classCategory,
                            typeDeclaration as ClassOrInterfaceDeclaration,
                            constructorDeclarations
                    )
                }
                val xdIntDefAnnotations = IntDefAnnotationExtractor.extractIntDefAnnotations(
                        intDefAnnotationExpressions,
                        fieldDeclarations
                )
                for (xdIntDefAnnotation in xdIntDefAnnotations) {
                    xdDeclaredType.intDefAnnotations.add(xdIntDefAnnotation)
                }
                xdDeclaredType.resolvedBody = true
            }
        }
        println("\t\t\tPeeked ${xdDeclaredType.targetClassName} body - $elapsedTime ms - $constructorsCount constructors, $methodsCount methods, $fieldsCount fields, $enumEntriesCount enum entries")
    }

    private fun hasCallableAnyAttributeSetParameter(callableDeclaration: CallableDeclaration<*>): Boolean {
        for (parameter in callableDeclaration.parameters) {
            if (hasParameterSimpleNameAttributeSet(parameter) && parameter.type.isClassOrInterfaceType) {
                return try {
                    val xdDeclaredType = DeclaredSymbolResolver.resolveDeclaredType(
                            parameter.type.asClassOrInterfaceType()
                    )
                    xdDeclaredType.targetClassName.canonicalName == attributeSetCanonicalName
                }
                catch (e: UnsolvedSymbolException) {
                    continue
                }
            }
        }
        return false
    }

    private fun addMembersAndFieldsFromSuperClass(xdDeclaredType: XdDeclaredType) {
        val xdSuperClass = xdDeclaredType.superClass
        if (xdSuperClass != null) {
            for (xdMethod in xdSuperClass.methods.toList()) {
                xdDeclaredType.methods.add(xdMethod)
            }
            for (xdField in xdSuperClass.fields.toList()) {
                xdDeclaredType.fields.add(xdField)
            }
        }
    }

    private fun hasParameterSimpleNameAttributeSet(parameter: Parameter): Boolean {
        return parameter.type.isClassOrInterfaceType
                && parameter.type.asClassOrInterfaceType().nameAsString == attributeSetClassType.simpleName
    }

    private fun addConstructorExpressionToClass(
            xdDeclaredType: XdDeclaredType,
            classCategory: ClassCategory,
            classDeclaration: ClassOrInterfaceDeclaration,
            constructorDeclarations: MutableList<ConstructorDeclaration>
    ) {
       val constructorExpression = getConstructorExpression(
               classCategory,
               classDeclaration,
               constructorDeclarations
       )
        xdDeclaredType.constructorExpression = constructorExpression.toEntity()
    }

    private fun getConstructorExpression(
            classCategory: ClassCategory,
            classDeclaration: ClassOrInterfaceDeclaration,
            constructors: MutableList<ConstructorDeclaration>
    ): ConstructorExpression {
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

    private fun clearAnyBodyExpressions(xdDeclaredType: XdDeclaredType) {
        Store.transactional {
            if (xdDeclaredType.methods.any()) {
                xdDeclaredType.methods.clear()
                for (xdMethod in xdDeclaredType.declaredMethods.toList()) {
                    xdMethod.parameters.clear()
                }
                xdDeclaredType.declaredMethods.clear()
            }
            xdDeclaredType.fields.clear()
            xdDeclaredType.declaredFields.clear()
            if (xdDeclaredType.constructors.any()) {
                for (xdConstructor in xdDeclaredType.constructors.toList()) {
                    xdConstructor.parameters.clear()
                }
                xdDeclaredType.constructors.clear()
            }
        }
    }

    private fun ensureSuperClassBodiesAreResolvedFirst(xdDeclaredType: XdDeclaredType) {
        // ensure class bodies are resolved (base class first, derived class last)
        val fromBaseToDerivedXdClasses = Store.transactional {
            xdDeclaredType.superClasses.toList().sortedBy { xdSuperClass -> xdSuperClass.superClasses.size() }
        }
        for (xdSuperClass in fromBaseToDerivedXdClasses) {
            if (!DeclaredTypeRegistry.isBodyResolved(xdSuperClass)) {
                resolveBody(
                        xdSuperClass.getClassOrInterfaceDeclaration(),
                        xdSuperClass
                )
            }
        }
    }


}


