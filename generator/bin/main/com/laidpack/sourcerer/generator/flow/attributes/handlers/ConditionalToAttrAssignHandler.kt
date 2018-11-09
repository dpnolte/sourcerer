package com.laidpack.sourcerer.generator.flow.attributes.handlers

import com.github.javaparser.ast.expr.*
import com.laidpack.sourcerer.generator.descendantsOfType
import com.laidpack.sourcerer.generator.flow.NodeHandler
import com.laidpack.sourcerer.generator.flow.attributes.AttributeFlow
import com.laidpack.sourcerer.generator.flow.attributes.BaseAttributesHandler
import com.laidpack.sourcerer.generator.flow.attributes.VariableImpact
import com.laidpack.sourcerer.generator.index.XdMethod

class ConditionalToAttrAssignHandler(flow: AttributeFlow) : BaseAttributesHandler<AssignExpr>(flow, AssignExpr::class) {
    override val handler: NodeHandler<AssignExpr> = ::onAssignExpressionConditionalToAttribute

    private fun onAssignExpressionConditionalToAttribute(node: AssignExpr): Boolean {
        if (flow.isConditionalToAttribute) {
            when {
                // check if Type Array getter is assigning value to variable
                node.target.isNameExpr && node.value.isMethodCallExpr -> {
                    methodCallIsAssignedToVariable(node)
                }
                // check if assigned variable value is retrieved from type array getter earlier
                node.target.isNameExpr && node.value.isNameExpr -> {
                    variableValueIsAssignedToAnotherVariable(node)
                }
                // check if variable is assigned via binary expression
                node.target is NameExpr && node.value is BinaryExpr -> {
                    handleBinaryExpression(node.target as NameExpr, node.value as BinaryExpr)
                }
            }

            return false // don't go further down the tree when analyzing statements w.r.t. attributes
        }
        return true
    }

    private fun methodCallIsAssignedToVariable(node: AssignExpr) {
        val targetExpr = node.target.asNameExpr()
        val valueExpr = node.value.asMethodCallExpr()
        if (flow.isAttributeValueRetrievedWithMethodCall(valueExpr)) {
            flow.setTypedArrayGetterForAttribute(valueExpr)
            flow.addVariableAsDerivedFromAttribute(targetExpr.nameAsString, valueExpr)
        }
    }

    private fun variableValueIsAssignedToAnotherVariable(node: AssignExpr) {
        val targetExpr = node.target.asNameExpr()
        if (flow.isVariableDerivedFromAttribute(targetExpr.nameAsString) || flow.isAttributeConditionalToTrue()) {
            val impact = if(flow.isAttributeConditionalToTrue()) VariableImpact.IF_TRUE else VariableImpact.CHAINED
            flow.addIndirectVariableAsDerivedFromAttribute(targetExpr.nameAsString, node, impact)
        }
    }

    private fun handleBinaryExpression(targetExpr: NameExpr, valueExpr: BinaryExpr) {
        // check if there are any method calls within binary expression
        val methodCallExpressions = valueExpr.descendantsOfType(MethodCallExpr::class.java)
        val isMemberVariableFromThisClass = flow.classInfo.isFieldFromThisClassOrSuperClass(targetExpr.nameAsString)
        // if yes, try to resolve to typedarray getter (even if nested in other methods)
        methodCallExpressions.forEach { methodCallExpr ->
            when {
                !isMemberVariableFromThisClass && flow.isAttributeValueRetrievedWithMethodCall(methodCallExpr) -> {
                    flow.setTypedArrayGetterForAttribute(methodCallExpr)
                    flow.addVariableAsDerivedFromAttribute(targetExpr.nameAsString, methodCallExpr)
                }
                flow.classInfo.isMethodCallFromThisClassOrSuperClass(methodCallExpr) && flow.isMethodCalledWithTypedArrayAsParameter(methodCallExpr) -> {
                    handleMethodCallWithTypedArrayParameter(targetExpr, methodCallExpr)
                }
            }
        }
        // TODO.. findOrCreate binary operations to variable (attribute impact..)
    }

    private fun handleMethodCallWithTypedArrayParameter(targetExpr: NameExpr, methodCallExpr: MethodCallExpr) {
        val method = flow.classInfo.getMethodInfoByCallExpr(methodCallExpr) as XdMethod
        flow.visitNestedMethodWithTypedArrayParameter(targetExpr, method)
    }
}