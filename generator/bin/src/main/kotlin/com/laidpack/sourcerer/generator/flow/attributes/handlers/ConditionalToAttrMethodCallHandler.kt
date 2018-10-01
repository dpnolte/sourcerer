package com.laidpack.sourcerer.generator.flow.attributes.handlers

import com.github.javaparser.ast.expr.MethodCallExpr
import com.laidpack.sourcerer.generator.flow.attributes.AttributeFlow
import com.laidpack.sourcerer.generator.flow.attributes.BaseAttributesHandler

class ConditionalToAttrMethodCallHandler(flow: AttributeFlow) : BaseAttributesHandler<MethodCallExpr>(flow, MethodCallExpr::class) {
    override val handler = ::onMethodCallExpressionConditionalToAttribute

    private fun onMethodCallExpressionConditionalToAttribute(node: MethodCallExpr): Boolean {
        if (flow.isMethodCallConditionalToAttributeAndFromCurrentClass(node)) {
            // check if method call is valid setter for an attribute
            loop@ for (index in 0 until node.arguments.size) {
                val arg = node.arguments[index]
                // is method from this class called with type array getter as argument?
                when {
                    arg.isMethodCallExpr -> {
                        val methodCallExpr = arg.asMethodCallExpr()
                        if (flow.isAttributeValueRetrievedWithMethodCall(methodCallExpr)) {
                            flow.setTypedArrayGetterForAttribute(methodCallExpr)
                            flow.addSetterToAttribute(node, index)
                            break@loop
                        }
                        // is method from this class called with a variable derived from attribute?
                    }
                    arg.isNameExpr -> {
                        val nameExpr = arg.asNameExpr()
                        if (flow.isVariableDerivedFromAttribute(nameExpr.nameAsString)) {
                            flow.addSetterToAttribute(node, index)
                            break@loop
                        }
                    }
                }
            }
            return false // don't go further down the tree when analyzing statements w.r.t. attributes
        }
        return true
    }

}