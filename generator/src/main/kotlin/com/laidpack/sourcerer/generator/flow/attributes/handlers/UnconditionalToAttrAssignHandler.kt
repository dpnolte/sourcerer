package com.laidpack.sourcerer.generator.flow.attributes.handlers

import com.github.javaparser.ast.expr.*
import com.laidpack.sourcerer.generator.flow.attributes.AttributeFlow
import com.laidpack.sourcerer.generator.flow.attributes.BaseAttributesHandler
import com.laidpack.sourcerer.generator.index.XdField

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
            }

            return false // don't go further down the tree when analyzing statements w.r.t. attributes
        }
        return true
    }

    private fun methodCallIsAssignedToVariable(node: AssignExpr) {
        val targetExpr = node.target.asNameExpr()
        val valueExpr = node.value.asMethodCallExpr()
        if (flow.classInfo.isPublicFieldFromThisClassOrSuperClass(targetExpr.nameAsString)
                && flow.isAttributeValueRetrievedWithMethodCall(valueExpr)) {
            val attribute = flow.getAttributeFromResourceNameArgument(valueExpr)
            val field = flow.classInfo.getFieldFromThisClassOrSuperClass(targetExpr.nameAsString) as XdField
            flow.setTypedArrayGetterForAttribute(valueExpr, attribute)
            flow.addSetterToAttribute(field, attribute)
        }
    }
}