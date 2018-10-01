package com.laidpack.sourcerer.generator.flow.attributes.handlers

import com.github.javaparser.ast.expr.Expression
import com.github.javaparser.ast.expr.NameExpr
import com.github.javaparser.ast.stmt.IfStmt
import com.laidpack.sourcerer.generator.ancestorsOfTypeUntil
import com.laidpack.sourcerer.generator.descendantsOfType
import com.laidpack.sourcerer.generator.flow.attributes.AttributeFlow
import com.laidpack.sourcerer.generator.flow.attributes.BaseAttributesHandler

internal class IfStmtHandler(flow: AttributeFlow): BaseAttributesHandler<IfStmt>(flow, IfStmt::class) {
    override val handler = ::onIfStmt

    private fun onIfStmt(node: IfStmt): Boolean {
        // TODO: add conditional to attribute for if statements
        val variableNameExpressions = node.condition.descendantsOfType(NameExpr::class.java)
        variableNameExpressions.forEach { variableNameExpr ->
            if (flow.isVariableAssignedInCurrentMethod(variableNameExpr.nameAsString)) {
                val conditionExpressions = variableNameExpr.ancestorsOfTypeUntil(Expression::class.java, node.condition)
                        .filterNot { it.isNameExpr }
                flow.addConditionExpressionsToVariable(variableNameExpr.nameAsString, conditionExpressions)
            }
        }

        return true
    }



}