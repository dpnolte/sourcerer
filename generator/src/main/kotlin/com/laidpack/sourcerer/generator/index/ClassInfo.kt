package com.laidpack.sourcerer.generator.index

import com.github.javaparser.ast.body.*
import com.github.javaparser.ast.expr.*
import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.firstAncestorOfType
import com.laidpack.sourcerer.generator.firstDescendantOfType
import com.laidpack.sourcerer.generator.resources.StyleableAttributeEnumValue
import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.Setter
import com.laidpack.sourcerer.generator.target.Getter
import com.squareup.kotlinpoet.ClassName
import kotlinx.dnq.query.*

data class ClassInfo(
        val xdDeclaredType: XdDeclaredType,
        val classDeclaration: ClassOrInterfaceDeclaration
) {
    val compilationUnit by lazy {classDeclaration.findCompilationUnit().get()}
    val classCategory = Store.transactional { xdDeclaredType.classCategory?.toEnum() }
    private val superClassDeclarations by lazy {
        Store.transactional {
            xdDeclaredType.superClasses.toList().associate { xdSuperClass ->
                Pair(xdSuperClass.canonicalName, xdSuperClass.getClassOrInterfaceDeclaration())
            }
        }
    }
    private fun getSuperClassDeclaration(className: ClassName): ClassOrInterfaceDeclaration {
        return superClassDeclarations[className.canonicalName] as ClassOrInterfaceDeclaration
    }

    fun getPotentialGettersForField(field: FieldDeclaration, variableName: String): List<XdMember> {
        val targetDescribedType = field.describeType(variableName)
        val list = mutableListOf<XdMember>()
        Store.transactional {
            val xdFields = xdDeclaredType.fields.query(
                    XdField::canBeGetter eq true
            ).toList()
            list.addAll(
                    xdFields.filter { xdField -> xdField.describedType == targetDescribedType }
            )
            val xdMethods = xdDeclaredType.methods.query(
                    XdMethod::canBeGetter eq true
            ).toList()
            list.addAll(xdMethods.filter {xdMethod ->  xdMethod.describedReturnType == targetDescribedType})
        }
        return list
    }

    // since it is a field, we assume setters can only be methods. not other public fields
    fun getPotentialSettersForType(describedType: String, attributeName: String): List<XdMethod> {
        return Store.transactional { t ->
            xdDeclaredType.methods
                    .filter {
                        (it.canBeSetter eq true)
                                .and(it.isVoid eq true)

                    }
                    .asSequence()
                    .filter {
                        it.parameters.size() == 1
                        && it.parameters.first().describedType == describedType
                    }
                    .sortedBy { xdMethod ->
                        xdMethod.name == "set${attributeName.capitalize()}"
                    }
                    .toList()
        }
    }

    fun getPotentialGettersForAnyOfTheseNames(names: List<String>): List<XdMember> {
        val list = mutableListOf<XdMember>()
        Store.transactional {
            val methods = xdDeclaredType.methods.query(
                    (XdMethod::canBeGetter eq true)
                    and
                    (XdMethod::name.inValues(names))
            )
            list.addAll(methods.toList())
            val fields = xdDeclaredType.fields.query(
                    (XdField::canBeGetter eq true)
                            and
                            (XdField::name.inValues(names))
            )
            list.addAll(fields.toList())
        }
        return list
    }

    fun getAnyGetterByJavaDoc(attribute: Attribute, desiredNumberOfAttributes: Set<Int>): XdMember? {
        return Store.transactional {
            val xdMethod = xdDeclaredType.methods.query(
                            (XdMethod::attributeNamesFromBlockTags.contains(attribute.name))
                            and
                            (XdMethod::canBeGetter eq true)
                    )
                    .asSequence()
                    .firstOrNull { m -> desiredNumberOfAttributes.contains(m.attributeNamesFromBlockTags.size) }
            if (xdMethod != null) {
                return@transactional xdMethod
            }
            val xdFields = xdDeclaredType.fields.query(
                            (XdField::attributeNamesFromBlockTags.contains(attribute.name))
                            and
                            (XdField::canBeGetter eq true)
                    )
                    .toList()
                    .filter {f -> desiredNumberOfAttributes.contains(f.attributeNamesFromBlockTags.size) }
            return@transactional if (xdFields.size == 1) xdFields.first() else null
        }
    }

    fun getCallablesWithAttributeSetAsParameter(): List<CallableDeclaration<*>> {
        val list = mutableListOf<CallableDeclaration<*>>()
        Store.transactional {
            val methods = xdDeclaredType.declaredMethods.query(
                    XdMethod::isMethodWithAttributeSetAsParameter eq true
            ).toList()
            list.addAll(
                    methods.map {
                        classDeclaration.members[it.memberIndex] as MethodDeclaration
                    }
            )
            val constructors = xdDeclaredType.constructors.query(
                    XdConstructor::isConstructorWithAttributeSetAsParameter eq true
            ).toList()
            list.addAll(
                    constructors.map {
                        classDeclaration.members[it.memberIndex] as ConstructorDeclaration
                    }
            )
        }
        return list
    }
    fun getPotentialSettersWithAttributeTagInComments(): List<XdMethod> {
        return Store.transactional {
            xdDeclaredType.declaredMethods.query(
                    (XdMethod::hasAnyJavaDocAttributeBlockTag eq true)
                    and
                    (XdMethod::canBeSetter eq true)
            ).toList()
        }
    }

    fun getMethodInfoByCallExpr(methodCallExpr: MethodCallExpr, checkIfMethodIsCalledFromCurrentClass: Boolean = true): XdMethod? {
        if((!checkIfMethodIsCalledFromCurrentClass || isMethodCallOnCurrentClass(methodCallExpr))
        ) {
            return Store.transactional {
                var xdMethods = xdDeclaredType.methods.query(
                        XdMethod::name eq methodCallExpr.nameAsString
                ).toList()
                // if no result, check if it is a static method of parent
                if (xdMethods.isEmpty() && xdDeclaredType.isNestedType) {
                    val parentCanonicalName = xdDeclaredType.canonicalName.substring(0, xdDeclaredType.canonicalName.lastIndexOf('.'))
                    val xdParentClass = XdDeclaredType.query(
                            XdDeclaredType::canonicalName eq parentCanonicalName
                    ).first()
                    if (!xdParentClass.resolvedBody) {
                        TypeBodyPeeker.peek(xdParentClass.getClassOrInterfaceDeclaration(), xdParentClass)
                    }
                    xdMethods = xdParentClass.methods.query(
                            (XdMethod::name eq methodCallExpr.nameAsString)
                            and (XdMethod::isStatic eq true)
                    ).toList()
                }
                return@transactional if (xdMethods.size == 1) {
                    xdMethods.first()
                } else {
                    // for performance reasons, try to match first based on number of parameters
                    val arguments = methodCallExpr.arguments
                    val xdMethodsWithCorrectParamCount = xdMethods.filter {xdMethod ->
                        xdMethod.parameters.size() == arguments.size
                    }
                    if (xdMethodsWithCorrectParamCount.size == 1) {
                        xdMethodsWithCorrectParamCount.first()
                    } else {
                        // check types
                        val argumentTypes = arguments.map {arg ->
                            if (arg is MethodCallExpr) {
                                getReturnTypeFromMethodCall(arg)
                            } else arg.describeType()
                        }
                        xdMethodsWithCorrectParamCount.find { xdMethod ->
                            var index = -1
                            val parameters = xdMethod.parameters.toList()
                            return@find parameters.all { param ->
                                index += 1
                                return@all param.describedType == argumentTypes[index]
                            }
                        }
                    }
                }
            }
        }
        return null
    }

    private fun isMethodCallOnCurrentClass(methodCallExpr: MethodCallExpr): Boolean {
        return !methodCallExpr.scope.isPresent
                || methodCallExpr.scope.get() is ThisExpr
                || methodCallExpr.scope.get() is SuperExpr
    }

    private fun getVariableDeclaredInConstructorOrMethod(nameExpr: NameExpr): VariableDeclarator? {
        val bodyDeclaration = nameExpr.firstAncestorOfType(BodyDeclaration::class.java)
            ?: throw IllegalArgumentException("Name expression $nameExpr is not within a body declaration")
        return bodyDeclaration.firstDescendantOfType(VariableDeclarator::class.java) {v ->
            v.nameAsString == nameExpr.nameAsString
        }
    }

    private fun getReturnTypeFromMethodCall(methodCallExpr: MethodCallExpr): String {
        return when {
            isMethodCallOnCurrentClass(methodCallExpr) -> {
                val xdOtherMethod =  getMethodInfoByCallExpr(methodCallExpr, false) as XdMethod
                xdOtherMethod.describedReturnType
            }
            methodCallExpr.scope.get() is NameExpr -> {
                val scopeNameExpr = methodCallExpr.scope.get().asNameExpr()
                val variableDeclarator = getVariableDeclaredInConstructorOrMethod(scopeNameExpr)
                if (variableDeclarator != null) {
                    val xdClass = DeclaredSymbolResolver.resolveDeclaredType(variableDeclarator.type.asClassOrInterfaceType())
                    Store.transactional {
                        if (!xdClass.resolvedBody) {
                            TypeBodyPeeker.peek(xdClass.getClassOrInterfaceDeclaration(), xdClass)
                        }
                        xdClass.methods.query(
                                XdMethod::name eq methodCallExpr.nameAsString
                        ).first().describedReturnType
                    }
                } else {
                    val xdField = getFieldFromThisClassOrSuperClass(scopeNameExpr.nameAsString)
                    if (xdField != null) {
                        val canonicalName = xdField.describedType
                        Store.transactional {
                            val xdClass = XdDeclaredType.query(XdDeclaredType::canonicalName eq canonicalName).first()
                            if (!xdClass.resolvedBody) {
                                TypeBodyPeeker.peek(xdClass.getClassOrInterfaceDeclaration(), xdClass)
                            }
                            xdClass.methods.query(
                                    XdMethod::name eq methodCallExpr.nameAsString
                            ).first().describedReturnType
                        }
                    } else {
                        val xdClass = DeclaredSymbolResolver.resolveDeclaredType(scopeNameExpr)
                        Store.transactional {
                            if (!xdClass.resolvedBody) {
                                TypeBodyPeeker.peek(xdClass.getTypeDeclaration(), xdClass)
                            }
                            val xdMethod = xdClass.methods.query(
                                    XdMethod::name eq methodCallExpr.nameAsString
                            ).firstOrNull()
                                    ?: throw IllegalStateException("Cannot resolve return type for method call '$methodCallExpr' with scope name '$scopeNameExpr'")
                            xdMethod.describedReturnType
                        }
                    }
                }
            }
            else -> throw IllegalStateException("Cannot resolve return type for method call '$methodCallExpr'")
        }
    }

    fun isMethodCallFromThisClassOrSuperClass(methodCallExpr: MethodCallExpr): Boolean {
        return Store.transactional {
            xdDeclaredType.methods.query(
                    XdMethod::name eq methodCallExpr.nameAsString
            ).any()
        }
    }

    fun isPublicMethodCallFromThisClassOrSuperClass(methodCallExpr: MethodCallExpr): Boolean {
        val xdMethod = this.getMethodInfoByCallExpr(methodCallExpr)
        return xdMethod != null && Store.transactional { xdMethod.isPublic }
    }
    fun isPublicFieldFromThisClassOrSuperClass(methodCallExpr: MethodCallExpr): Boolean {
        val xdMethod = this.getMethodInfoByCallExpr(methodCallExpr)
        return xdMethod != null && Store.transactional { xdMethod.isPublic }
    }

    fun getFieldFromThisClassOrSuperClass(variableName: String): XdField? {
        return Store.transactional {
            xdDeclaredType.fields.query(
                    XdField::name eq variableName
            ).firstOrNull() ?: return@transactional null
        }
    }
    fun getFieldDeclarationFromThisClassOrSuperClass(variableName: String): FieldDeclaration? {
        val field = getFieldFromThisClassOrSuperClass(variableName) ?: return null
        return getFieldDeclarationFromThisClassOrSuperClass(field)
    }
    fun getFieldDeclarationFromThisClassOrSuperClass(field: XdField): FieldDeclaration? {
        return Store.transactional {
            return@transactional if (field.declaredInType.targetClassName == xdDeclaredType.targetClassName ) {
                classDeclaration.members[field.memberIndex] as FieldDeclaration?
            } else {
                getSuperClassDeclaration(field.declaredInType.targetClassName).members[field.memberIndex] as FieldDeclaration?
            }
        }
    }
    fun getResolvedFieldFromThisClassOrSuperClass(variableName: String): FieldDeclaration {
        return getFieldDeclarationFromThisClassOrSuperClass(variableName)
                ?: throw IllegalArgumentException("No field declaration with name '$variableName' in class '${xdDeclaredType.targetClassName}' or super classes")
    }


    fun getMethodDeclarationFromThisClassOrSuperClass(method: XdMethod): MethodDeclaration? {
        return Store.transactional {
            return@transactional if (method.declaredInType.targetClassName == xdDeclaredType.targetClassName ) {
                classDeclaration.members[method.memberIndex] as MethodDeclaration?
            } else {
                getSuperClassDeclaration(method.declaredInType.targetClassName).members[method.memberIndex] as MethodDeclaration?
            }
        }
    }
    fun getResolvedMethodDeclarationFromThisClassOrSuperClass(xdMethod: XdMethod): MethodDeclaration {
        return getMethodDeclarationFromThisClassOrSuperClass(xdMethod)
                ?: Store.transactional {  throw IllegalArgumentException("No method declaration with name '${xdMethod.name}' in class '${xdMethod.declaredInType.targetClassName}'") }
    }


    fun isPublicFieldFromThisClassOrSuperClass(variableName: String): Boolean {
        return Store.transactional {
            val field = xdDeclaredType.fields.query(
                    XdField::name eq variableName
            ).firstOrNull() ?: return@transactional false

            return@transactional field.isPublic
        }
    }
    fun isFieldFromThisClassOrSuperClass(variableName: String): Boolean {
        return Store.transactional {
            xdDeclaredType.fields.query(
                    XdField::name eq variableName
            ).any()
        }
    }
    fun isFieldFromParentClass(variableName: String): Boolean {
        if (classDeclaration.isNestedType) {
            return Store.transactional {
                val ancestorClassName = xdDeclaredType.targetClassName.canonicalName.substring(
                        0, xdDeclaredType.targetClassName.canonicalName.lastIndexOf('.')
                )
                val xdAncestorClass = XdDeclaredType.query(
                        XdDeclaredType::canonicalName eq ancestorClassName
                ).firstOrNull()
                        ?: throw java.lang.IllegalArgumentException("No ancestor class found with name $ancestorClassName")
                if (!xdAncestorClass.resolvedBody) {
                    TypeBodyPeeker.peek(xdAncestorClass.getClassOrInterfaceDeclaration(), xdAncestorClass)
                }
                xdAncestorClass.fields.query(
                        XdField::name eq variableName
                ).any()
            }
        }
        return false
    }

    fun getResolvedFieldDeclarationFromParentClass(variableName: String): FieldDeclaration {
        return Store.transactional {
            if (!xdDeclaredType.isNestedType) throw java.lang.IllegalArgumentException("${xdDeclaredType.targetClassName} is not a nested class")
            val ancestorClassName = xdDeclaredType.targetClassName.canonicalName.substring(
                    0, xdDeclaredType.targetClassName.canonicalName.lastIndexOf('.')
            )
            val xdAncestorClass = XdDeclaredType.query(
                    XdDeclaredType::canonicalName eq ancestorClassName
            ).firstOrNull()
                    ?: throw java.lang.IllegalArgumentException("No ancestor class found with name $ancestorClassName")
            if (!xdAncestorClass.resolvedBody) {
                TypeBodyPeeker.peek(xdAncestorClass.getClassOrInterfaceDeclaration(), xdAncestorClass)
            }
            val xdField = xdAncestorClass.fields.query(
                    XdField::name eq variableName
            ).firstOrNull()
                    ?: throw java.lang.IllegalArgumentException("Ancestor $ancestorClassName class has no field with name $variableName")
            return@transactional xdAncestorClass.getClassOrInterfaceDeclaration().members[xdField.memberIndex] as FieldDeclaration
        }
    }

    fun getSetterFromThisClassOrSuperClass(setter: Setter): XdMember {
        val setterHashCode = setter.hashCode()
        return Store.transactional {
            val xdMethods = xdDeclaredType.methods.query(
                    XdMethod::name eq setter.name
            ).toList()
            return@transactional when {
                xdMethods.size == 1 -> xdMethods[0]
                xdMethods.size > 1 -> xdMethods.first { xdMethod -> xdMethod.accessorHashCode == setterHashCode }
                else -> {
                    xdDeclaredType.fields.query(
                            XdField::accessorHashCode eq setterHashCode
                    ).firstOrNull()
                        ?: throw IllegalArgumentException("XdMethod or XdField '${setter.name}' not found for setter in class '${xdDeclaredType.targetClassName}' or super classes '${xdDeclaredType.superClasses.toList().joinToString { xdSuperClass -> xdSuperClass.canonicalName }}'")
                }
            }
        }
    }
    fun getGetterFromThisClassOrSuperClass(getter: Getter): XdMember {
        val getterHashCode = getter.hashCode()
        return Store.transactional {
            val xdMethods = xdDeclaredType.methods.query(
                    XdMethod::name eq getter.name
            ).toList()
            return@transactional when {
                xdMethods.size == 1 -> xdMethods[0]
                xdMethods.size > 1 -> xdMethods.first { xdMethod -> xdMethod.accessorHashCode == getterHashCode }
                else -> {
                    xdDeclaredType.fields.query(
                            XdField::accessorHashCode eq getterHashCode
                    ).firstOrNull()
                            ?: throw IllegalArgumentException("XdMethod or XdField '${getter.name}' not found for getter in class '${xdDeclaredType.targetClassName}' or super classes '${xdDeclaredType.superClasses.toList().joinToString { xdSuperClass -> xdSuperClass.canonicalName }}'")
                }
            }
        }
    }

    fun isIntDefAnnotation(annotationName: String): Boolean {
        return Store.transactional {
            xdDeclaredType.intDefAnnotations.query(
                    XdIntDefAnnotation::name eq annotationName
            ).any()
        }
    }

    fun getIntDefAnnotationAsAttributeEnum(annotationName: String): List<StyleableAttributeEnumValue> {
        return Store.transactional {
            val intDefAnnotation = xdDeclaredType.intDefAnnotations.query(
                    XdIntDefAnnotation::name eq annotationName
            ).first()
            return@transactional intDefAnnotation.values.toList().map {xdIntDefValue ->
                StyleableAttributeEnumValue(xdIntDefValue.name, xdIntDefValue.value)
            }
        }
    }
}


