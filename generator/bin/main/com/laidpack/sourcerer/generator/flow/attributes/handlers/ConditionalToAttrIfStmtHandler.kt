package com.laidpack.sourcerer.generator.flow.attributes.handlers

import com.github.javaparser.ast.expr.*
import com.github.javaparser.ast.stmt.IfStmt
import com.laidpack.sourcerer.generator.descendantsOfType
import com.laidpack.sourcerer.generator.flow.attributes.AttributeFlow
import com.laidpack.sourcerer.generator.flow.attributes.BaseAttributesHandler
import com.laidpack.sourcerer.generator.flow.attributes.TypeValueResolver

internal class ConditionalToAttrIfStmtHandler(flow: AttributeFlow): BaseAttributesHandler<IfStmt>(flow, IfStmt::class) {
    override val handler = ::onIfStmtConditionalToAttr

    private fun onIfStmtConditionalToAttr(node: IfStmt): Boolean {
        if (flow.isConditionalToAttribute) {
            // check if statement contains something like: if (a.getBoolean(attr, false)) {
            val methodCallExpressions = node.condition.descendantsOfType(MethodCallExpr::class.java)
            val typedArrayGetter = methodCallExpressions.find { methodCallExpr ->
                flow.isAttributeValueRetrievedWithMethodCall(methodCallExpr)
            }
            if (typedArrayGetter != null) {
                when (typedArrayGetter.nameAsString) {
                    "getBoolean" -> flow.flagConditionalToAttributeBeingTrue()
                    "getValue" -> {
                        flow.flagConditionalToTypeValue()
                        if (flow.withinNestedMethod) {
                            val resolver = TypeValueResolver(node, flow)
                            resolver.resolveTypeValueConditions()
                            return false
                        }
                    }
                }

            }
        }

        return true
    }



}