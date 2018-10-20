package com.laidpack.sourcerer.generator.flow.attributes.handlers

import com.github.javaparser.ast.expr.*
import com.laidpack.sourcerer.generator.flow.attributes.AttributeFlow
import com.laidpack.sourcerer.generator.flow.attributes.BaseAttributesHandler

class UnconditionalToAttrAssignHandler(flow: AttributeFlow) : BaseAttributesHandler<AssignExpr>(flow, AssignExpr::class) {
    override val handler = ::onAssignExpressionUnconditionalToAttribute

    private fun onAssignExpressionUnconditionalToAttribute(node: AssignExpr): Boolean {
        // check if assign expression might possible assign attribute if in nested method with resource ids as parameters
        if (!flow.isConditionalToAttribute && flow.isAnyParameterDeclaredAsResourceName) {
            when {
                // check if Type Array getter is assigning value to variable
                node.target.isNameExpr && node.value.isMethodCallExpr -> {
                    methodCallIsAssignedToVariable(node)
                }
                /*
                // check if assigned variable value is retrieved from type array getter earlier
                classOrInterfaceDeclarationProvider.target.isNameExpr && classOrInterfaceDeclarationProvider.value.isNameExpr -> {
                    variableValueIsAssignedToAnotherVariable(classOrInterfaceDeclarationProvider)
                }
                // check if variable is assigned via binary expression
                classOrInterfaceDeclarationProvider.target is NameExpr && classOrInterfaceDeclarationProvider.value is BinaryExpr -> {
                    handleBinaryExpression(classOrInterfaceDeclarationProvider.target as NameExpr, classOrInterfaceDeclarationProvider.value as BinaryExpr)
                }*/
            }

            return false // don't go further down the tree when analyzing statements w.r.t. attributes
        }
        return true
    }

    private fun methodCallIsAssignedToVariable(node: AssignExpr) {
        val targetExpr = node.target.asNameExpr()
        val valueExpr = node.value.asMethodCallExpr()
        if (flow.isPublicField(targetExpr.nameAsString) && flow.isAttributeValueRetrievedWithMethodCall(valueExpr)) {
            val attribute = flow.getAttributeFromResourceNameArgument(valueExpr)
            val field = flow.classInfo.getResolvedFieldFromThisClassOrSuperClass(targetExpr.nameAsString)
            flow.setTypedArrayGetterForAttribute(valueExpr, attribute)
            flow.addSetterToAttribute(field, attribute)
        }
    }

    /*
    private fun variableValueIsAssignedToAnotherVariable(classOrInterfaceDeclarationProvider: AssignExpr) {
        val targetExpr = classOrInterfaceDeclarationProvider.target.asNameExpr()
        if (flow.isVariableDerivedFromAttribute(targetExpr.nameAsString) || flow.isAttributeConditionalToTrue()) {
            val impact = if(flow.isAttributeConditionalToTrue()) VariableImpact.IF_TRUE else VariableImpact.CHAINED
            flow.addIndirectVariableAsDerivedFromAttribute(targetExpr.nameAsString, classOrInterfaceDeclarationProvider, impact)
        }
    }

    private fun handleBinaryExpression(targetExpr: NameExpr, valueExpr: BinaryExpr) {
        // check if there are any method calls within binary expression
        val methodCallExpressions = valueExpr.descendantsOfType(MethodCallExpr::class.java)
        val isFieldFromCurrentClass = flow.isFieldFromCurrentClass(targetExpr.nameAsString)
        // if yes, try to resolve to typedarray getter (even if nested in other methods)
        methodCallExpressions.forEach { methodCallExpr ->
            when {
                !isFieldFromCurrentClass && flow.isAttributeValueRetrievedWithMethodCall(methodCallExpr) -> {
                    flow.setTypedArrayGetterForAttribute(methodCallExpr)
                    flow.addVariableAsDerivedFromAttribute(targetExpr.nameAsString, methodCallExpr)
                }
                flow.isMethodCallFromThisClass(methodCallExpr) && flow.isMethodCalledWithTypedArrayAsParameter(methodCallExpr) -> {
                    handleMethodCallWithTypedArrayParameter(targetExpr, methodCallExpr)
                }
            }
        }
        // TODO.. findOrCreate binary operations to variable (attribute impact..)
    }

    private fun handleMethodCallWithTypedArrayParameter(targetExpr: NameExpr, methodCallExpr: MethodCallExpr) {
        val methodInfo = flow.getMethodInfoByCallExpr(methodCallExpr)
        flow.visitNestedMethodWithTypedArrayParameter(targetExpr, methodInfo)
    }*/
}