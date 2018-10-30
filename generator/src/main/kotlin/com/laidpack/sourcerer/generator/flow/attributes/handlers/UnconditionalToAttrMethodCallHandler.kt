package com.laidpack.sourcerer.generator.flow.attributes.handlers

import android.content.Context
import android.content.res.TypedArray
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.VariableDeclarator
import com.github.javaparser.ast.expr.*
import com.laidpack.sourcerer.generator.ancestorsOfType
import com.laidpack.sourcerer.generator.firstAncestorOfType
import com.laidpack.sourcerer.generator.firstDescendantOfType
import com.laidpack.sourcerer.generator.flow.attributes.AttributeFlow
import com.laidpack.sourcerer.generator.flow.attributes.AttributeInSource
import com.laidpack.sourcerer.generator.flow.attributes.BaseAttributesHandler
import com.laidpack.sourcerer.generator.index.XdField
import com.laidpack.sourcerer.generator.index.XdMethod
import com.laidpack.sourcerer.generator.index.describeType

internal class UnconditionalToAttrMethodCallHandler(flow: AttributeFlow): BaseAttributesHandler<MethodCallExpr>(flow, MethodCallExpr::class) {
    override val handler = ::onCurrentClassMethodCallUnconditionalToAttr

    private fun onCurrentClassMethodCallUnconditionalToAttr(node: MethodCallExpr): Boolean {
        var canContinue = true
        if (!flow.isConditionalToAttribute) {
            canContinue = when {
                flow.classInfo.isMethodCallFromThisClassOrSuperClass(node) && isTypedArrayOneOfTheArguments(node)
                    -> handleAttributeDelegateMethod(node)
                flow.classInfo.isPublicMethodCallFromThisClassOrSuperClass(node)
                    -> handleSetterCall(node)
                isMethodCallToObtainStyleAttributes(node)
                    -> handleObtainStyleAttributes(node)
                isMethodCallOnTypedArray(node)
                    -> handleMethodCallOnTypedArray(node)
                else -> true
            }
        }

        return canContinue
    }

    private fun handleSetterCall(node: MethodCallExpr): Boolean {
        // check if argument used for setter (i.e., method call) are variables that are derived from an attribute
        var isValidSetter = false
        val variableNames = mutableListOf<String>()
        node.arguments.forEach{ arg ->
            // is method from this class called with a variable derived from attribute?
            if (arg.isNameExpr){
                val nameExpr = arg.asNameExpr()
                variableNames.add(nameExpr.nameAsString)
                if (!isValidSetter && flow.isVariableDerivedFromAnyAttribute(nameExpr.nameAsString)) {
                    isValidSetter = true
                }
            }
        }
        if (isValidSetter) {
            flow.addSetterByDerivedVariables(node, variableNames)
            return false // don't go further down the tree
        }
        return true
    }

    private fun handleAttributeDelegateMethod(node: MethodCallExpr): Boolean {
        // check if other arguments are resource name field access expressions
        val resourceIds = getResourceNamesFromArguments(node)
        if (resourceIds.isNotEmpty()) {
            flow.addAttributesFromArguments(node, resourceIds)
        }
        // visit method
        val method = flow.classInfo.getMethodInfoByCallExpr(node) as XdMethod
        flow.visitNestedMethodWithTypedArrayParameter(null, method)
        return false
    }

    private fun isAnyResourceNameProvidedAsArgument(node: MethodCallExpr): Boolean {
        val resourceNameFromIndex = getResourceNameFromIndexArgument(node)
        if (resourceNameFromIndex != null) {
            return true
        } else {
            node.arguments.forEachIndexed { index, arg ->
                if (arg is FieldAccessExpr && arg.scope is FieldAccessExpr) {
                    val argFullString = arg.toString()
                    if (argFullString.contains("styleable.")) {
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun getResourceNamesFromArguments(node: MethodCallExpr): Map<Int, String> {
        val resourceNames = mutableMapOf<Int, String>()
        val resourceNameFromIndex = getResourceNameFromIndexArgument(node)
        if (resourceNameFromIndex != null) {
            resourceNames[0] = resourceNameFromIndex
        } else {
            node.arguments.forEachIndexed { index, arg ->
                if (arg is FieldAccessExpr && arg.scope is FieldAccessExpr) {
                    val argFullString = arg.toString()
                    if (argFullString.contains("styleable.")) {
                        resourceNames[index] = arg.nameAsString
                    }
                }
            }
        }
        return resourceNames
    }

    private fun getResourceNameFromIndexArgument(node: MethodCallExpr): String? {
        if (node.scope.isPresent && node.scope.get() is NameExpr) {
            val scope = node.scope.get()
            val variableName = scope.asNameExpr().nameAsString
            val firstArgument = node.arguments.firstOrNull()
            if (firstArgument != null && firstArgument.isIntegerLiteralExpr) {
                val key = Pair(variableName, firstArgument.asIntegerLiteralExpr().asInt())
                if(flow.typedArrayIndexToResourceName.containsKey(key)) {
                    return flow.typedArrayIndexToResourceName[key] as String
                }
            }
        }
        return null
    }

    private fun isTypedArrayOneOfTheArguments(node: MethodCallExpr): Boolean {
        return node.arguments.any { it is NameExpr && flow.typedArrayVariableNames.contains(it.name)}
    }

    private fun isMethodCallOnTypedArray(node: MethodCallExpr): Boolean {
        if (node.scope.isPresent && node.scope.get() is NameExpr) {
            val scope = node.scope.get() as NameExpr
            return flow.typedArrayVariableNames.contains(scope.name)
        }
        return false
    }


    private fun handleMethodCallOnTypedArray(node: MethodCallExpr): Boolean {
        when {
            node.nameAsString == "getIndex" -> handleResourceIdRetrieved(node)
            isAnyResourceNameProvidedAsArgument(node)
                    && flow.isAttributeValueRetrievedWithMethodCall(node, false) -> {
                handleGetResourceValueCall(node)
            }

        }
        return false
    }

    private fun handleResourceIdRetrieved(node: MethodCallExpr) {
        val variableDeclarator = node.firstAncestorOfType(VariableDeclarator::class.java)
            ?: throw IllegalStateException("retrieved resource id is not assigned to variable")
        flow.setResourceIdVariable(variableDeclarator.name)
    }

    private fun handleGetResourceValueCall(node: MethodCallExpr) {
        val resourceIds = getResourceNamesFromArguments(node)
        val attributesUsedInCall = mutableListOf<AttributeInSource>()
        resourceIds.values.forEach {
            flow.addAttribute(it)
            val attribute = flow.getAttributeByResourceId(it)
            flow.setTypedArrayGetterForAttribute(node, attribute)
            attributesUsedInCall.add(attribute)
        }
        val attributeNames = attributesUsedInCall.map { it.name }
        attributesUsedInCall.forEach { attribute ->
            // check if attr is assigned to public member field
            val assignExpressions = node.ancestorsOfType(AssignExpr::class.java)
            var attributeIsAssignedToField = false
            for (assignExpr in assignExpressions) {
                val target = assignExpr.target
                when {
                    target is NameExpr && flow.classInfo.isPublicFieldFromThisClassOrSuperClass(target.nameAsString) -> {
                        flow.addSetterToAttribute(
                                flow.classInfo.getFieldFromThisClassOrSuperClass(target.nameAsString) as XdField,
                                attribute
                        )
                        attributeIsAssignedToField = true
                    }
                    target is FieldAccessExpr
                            && target.scope.isThisExpr
                            && flow.classInfo.isFieldFromThisClassOrSuperClass(target.nameAsString)-> {
                        flow.addSetterToAttribute(
                                flow.classInfo.getFieldFromThisClassOrSuperClass(target.nameAsString) as XdField,
                                attribute
                        )
                        attributeIsAssignedToField = true
                    }
                }
            }
            if (!attributeIsAssignedToField) {
                // check if attr is assigned with method call
                val parentCall = node.firstAncestorOfType(MethodCallExpr::class.java)
                var identifiedSetterMethod = false
                when {
                    // call on a public method of current class
                    parentCall != null && flow.classInfo.isPublicMethodCallFromThisClassOrSuperClass(parentCall) -> {
                        val parameterIndex = parentCall.arguments.indexOfFirst { arg ->
                            arg.firstDescendantOfType(MethodCallExpr::class.java) == node
                        }
                        flow.addSetterToAttribute(parentCall, parameterIndex, attributeNames, attribute)
                        identifiedSetterMethod = true
                    }
                    // call on a field's method or public static class
                    parentCall != null
                            && parentCall.scope.isPresent
                            && parentCall.scope.get() is NameExpr
                    -> {
                        val scope = parentCall.scope.get().asNameExpr()
                        val scopeName = scope.nameAsString
                        when {
                            // call on a field's method
                            parentCall.arguments.size == 1 && flow.classInfo.isFieldFromThisClassOrSuperClass(scopeName) -> {
                                flow.addMethodCallOnFieldAsBeingSetByAttribute(parentCall, attribute)
                                identifiedSetterMethod = true
                            }
                            // call on a public static method with this provided
                            parentCall.arguments.any { it is ThisExpr } && flow.isPublicStaticMethod(scope, parentCall.nameAsString) -> {
                                val classDeclaration = flow.getClassDeclarationFromNameExpression(
                                        parentCall.scope.get().asNameExpr()
                                ) as ClassOrInterfaceDeclaration
                                val methodDeclaration = classDeclaration.methods.first { it.nameAsString == parentCall.nameAsString }
                                if (methodDeclaration.isPublic) {
                                    val parameterIndex = parentCall.arguments.indexOfFirst { arg ->
                                        arg.firstDescendantOfType(MethodCallExpr::class.java) == node
                                    }
                                    flow.addStaticSetterToAttribute(
                                            classDeclaration,
                                            methodDeclaration,
                                            parameterIndex,
                                            parentCall,
                                            attributeNames,
                                            attribute
                                    )
                                    identifiedSetterMethod = true
                                }
                            }
                        }
                    }
                }
                // assigned to any variables
                if(!identifiedSetterMethod) {
                    val assignedVariableNames = getAnyAssignedVariableNames(node)
                    for (assignedVariableName in assignedVariableNames) {
                        flow.addVariableAsDerivedFromAttribute(assignedVariableName, node, attribute)
                    }
                }
            }
        }
    }

    private fun getAnyAssignedVariableNames(node: MethodCallExpr): List<String> {
        val variableDeclarator = node.firstAncestorOfType(VariableDeclarator::class.java)
        if (variableDeclarator != null) {
            return listOf(variableDeclarator.nameAsString)
        }
        val assignExpressions = node.ancestorsOfType(AssignExpr::class.java)
        val variablesNames = mutableListOf<String>()
        for (assignExpr in assignExpressions) {
            val target = assignExpr.target
            if (target is NameExpr) {
                variablesNames.add(target.nameAsString)
            }
        }
        return variablesNames
    }


    private val typedArrayTypes = setOf(
            TypedArray::class.java.canonicalName, tintTypedArrayCanonicalName
    )
    private fun isMethodCallToObtainStyleAttributes(node: MethodCallExpr): Boolean {
        if(node.nameAsString == obtainStyledAttributeMethodName
                && node.scope.isPresent) {
            // check if return type is TypedArray or TintTypedArray
            val describedType = node.firstAncestorOfType(VariableDeclarator::class.java)?.describeType()
                    ?: node.firstAncestorOfType(AssignExpr::class.java)?.calculateResolvedType()?.describe()
                    ?: return false
            return typedArrayTypes.contains(describedType)
        }
        return false
    }
    private fun handleObtainStyleAttributes(node: MethodCallExpr): Boolean {
        //flow.flagLastDeclaredVariableAsTypedArrayVariable()
        val variableDeclarator = node.firstAncestorOfType(VariableDeclarator::class.java)
        if (variableDeclarator != null) {
            flow.addTypedArrayVariable(variableDeclarator.name)
        } else {
            val assignExpr = node.firstAncestorOfType(AssignExpr::class.java) ?: throw  IllegalStateException("obtain style attributed not assigned to variable")
            flow.addTypedArrayVariable(assignExpr.target.asNameExpr().name)
        }
        // check if any non-default attributes are obtained
        flow.checkIfAnyNonDefaultAttributeIsObtained(node)

        return false // don't go further down the tree
    }

    companion object {
        private const val obtainStyledAttributeMethodName = "obtainStyledAttributes"
        private const val getIndexMethodName = "getIndex"
        private const val tintTypedArrayCanonicalName = "androidx.appcompat.widget.TintTypedArray"

        init {
            ensureMethodsExist()
        }
        private fun ensureMethodsExist() {
            if (!Context::class.java.methods.any { it.name == obtainStyledAttributeMethodName }) {
                throw NoSuchMethodException("$obtainStyledAttributeMethodName no longer exists on Context")
            }
            if (!TypedArray::class.java.methods.any { it.name == getIndexMethodName }) {
                throw NoSuchMethodException("$getIndexMethodName no longer exists on TypedArray")
            }
        }
    }


}