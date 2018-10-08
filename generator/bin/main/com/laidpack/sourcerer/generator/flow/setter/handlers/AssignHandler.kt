package com.laidpack.sourcerer.generator.flow.setter.handlers

import com.github.javaparser.ast.expr.AssignExpr
import com.github.javaparser.ast.expr.NameExpr
import com.laidpack.sourcerer.generator.descendantsOfType
import com.laidpack.sourcerer.generator.flow.setter.BaseSetterHandler
import com.laidpack.sourcerer.generator.flow.setter.SetterFlow

class AssignHandler(flow: SetterFlow) : BaseSetterHandler<AssignExpr>(flow, AssignExpr::class) {
    override val handler = ::onAssignExpression

    // for now, assume 1 parameter setters
    private fun onAssignExpression(node: AssignExpr): Boolean {
        when {
            flow.isParameterBeingAssigned(node) -> addAssignedVariables(node)
            flow.isValueBeingAssigned(node) -> addValueAsRequirement(node)
        }
        return true
    }

    private fun addAssignedVariables(node: AssignExpr) {
        val assignExpressions = node.descendantsOfType(AssignExpr::class.java)
        for(assignExpression in assignExpressions) {
            if (assignExpression.target is NameExpr) {
                val name = assignExpression.target.asNameExpr().nameAsString
                if (flow.classInfo.isFieldFromThisClass(name)) {
                    flow.addFieldIfNew(flow.classInfo.getFieldFromThisClass(name))
                }
            }
        }
    }

    private fun addValueAsRequirement(node: AssignExpr) {
        val assignExpressions = node.descendantsOfType(AssignExpr::class.java)
        val value = flow.getAssignedValue(node)
        for(assignExpression in assignExpressions) {
            if (assignExpression.target is NameExpr) {
                val name = assignExpression.target.asNameExpr().nameAsString
                if (flow.classInfo.isFieldFromThisClass(name)) {
                    flow.addConditionIfNew(name, value)
                }
            }
        }
    }
}