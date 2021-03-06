package com.laidpack.sourcerer.generator.flow.attributes.handlers

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import com.github.javaparser.ast.body.VariableDeclarator
import com.github.javaparser.ast.expr.AssignExpr
import com.github.javaparser.ast.expr.FieldAccessExpr
import com.github.javaparser.ast.expr.MethodCallExpr
import com.github.javaparser.ast.expr.NameExpr
import com.github.javaparser.resolution.UnsolvedSymbolException
import com.laidpack.sourcerer.generator.firstAncestorOfType
import com.laidpack.sourcerer.generator.firstDescendantOfType
import com.laidpack.sourcerer.generator.flow.attributes.AttributeFlow
import com.laidpack.sourcerer.generator.flow.attributes.BaseAttributesHandler
import com.laidpack.sourcerer.generator.peeker.MethodInfo

internal class UnconditionalToAttrMethodCallHandler(flow: AttributeFlow): BaseAttributesHandler<MethodCallExpr>(flow, MethodCallExpr::class) {
    override val handler = ::onCurrentClassMethodCallUnconditionalToAttr

    private fun onCurrentClassMethodCallUnconditionalToAttr(node: MethodCallExpr): Boolean {
        if (!flow.isConditionalToAttribute) {
            return when {
                flow.classInfo.isMethodCallFromThisClass(node) && isTypedArrayOneOfTheArguments(node) -> handleAttributeDelegateMethod(node)
                flow.classInfo.isPublicMethodCallFromThisClass(node) -> handleSetterCall(node)
                isMethodCallToObtainStyleAttributes(node) -> handleObtainStyleAttributes(node)
                isMethodCallOnTypedArray(node) -> handleMethodCallOnTypedArray(node)
                else -> true
            }
        }

        return true
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
        val resourceIds = getResourceIdsFromArguments(node)
        if (resourceIds.isNotEmpty()) {
            flow.addAttributesFromArguments(node, resourceIds)
        }
        // visit method
        val methodInfo = flow.classInfo.getMethodInfoByCallExpr(node) as MethodInfo
        flow.visitNestedMethodWithTypedArrayParameter(null, methodInfo)
        return false
    }

    private fun isAnyResourceIdProvidedAsArgument(node: MethodCallExpr): Boolean {
        node.arguments.forEachIndexed { index, arg ->
            if(arg is FieldAccessExpr && arg.scope is FieldAccessExpr) {
                val argFullString = arg.toString()
                if (argFullString.contains(".R.styleable.")) {
                    return true
                }
            }
        }
        return false
    }

    private fun getResourceIdsFromArguments(node: MethodCallExpr): Map<Int, String> {
        val resourceIds = mutableMapOf<Int, String>()
        node.arguments.forEachIndexed { index, arg ->
            if(arg is FieldAccessExpr && arg.scope is FieldAccessExpr) {
                val argFullString = arg.toString()
                if (argFullString.contains(".R.styleable.")) {
                    resourceIds[index] = arg.nameAsString
                }
            }
        }
        return resourceIds
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

    private fun isMethodCallToObtainStyleAttributes(node: MethodCallExpr): Boolean {
        if(node.nameAsString == obtainStyledAttributeMethodName
                && node.scope.isPresent) {
            val scope = node.scope.get()
            val resolvedScope = try {
                scope.calculateResolvedType()
            } catch (e: UnsolvedSymbolException) {
                null
            } ?: return false
            val typeAsString = resolvedScope.describe()
            return typeAsString == contextCanonicalName || typeAsString == themeCanonicalName
        }
        return false
    }

    private fun handleMethodCallOnTypedArray(node: MethodCallExpr): Boolean {
        when {
            node.nameAsString == "getIndex" -> handleResourceIdRetrieved(node)
            isAnyResourceIdProvidedAsArgument(node)
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
        val resourceIds = getResourceIdsFromArguments(node)
        resourceIds.values.forEach {
            flow.addAttribute(it)
            val attribute = flow.getAttributeByResourceId(it)
            flow.setTypedArrayGetterForAttribute(node, attribute)
            val assignedVariableName = getAssignedVariableName(node)
            if (assignedVariableName != null) {
                // clear any previous variables being derived to
                flow.addVariableAsDerivedFromAttribute(assignedVariableName, node, attribute)
            } else {
                val parentCall = node.firstAncestorOfType(MethodCallExpr::class.java)
                if (parentCall != null && flow.classInfo.isPublicMethodCallFromThisClass(parentCall)) {
                    val parameterIndex = parentCall.arguments.indexOfFirst { arg ->
                        arg.firstDescendantOfType(MethodCallExpr::class.java) == node
                    }
                    flow.addSetterToAttribute(parentCall, parameterIndex, attribute)
                }

            }
        }
    }

    private fun getAssignedVariableName(node: MethodCallExpr): String? {
        val variableDeclarator = node.firstAncestorOfType(VariableDeclarator::class.java)
        if (variableDeclarator != null) {
            return variableDeclarator.nameAsString
        }
        val assignExpr = node.firstAncestorOfType(AssignExpr::class.java)
        if (assignExpr != null && assignExpr.target is NameExpr) {
            return assignExpr.target.asNameExpr().nameAsString
        }
        return null
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

        return false // don't go further down the tree
    }

    companion object {
        private const val obtainStyledAttributeMethodName = "obtainStyledAttributes"
        private const val getIndexMethodName = "getIndex"
        private val  contextCanonicalName = Context::class.java.canonicalName
        private val themeCanonicalName = Resources.Theme::class.java.canonicalName

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