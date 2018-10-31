package com.laidpack.sourcerer.generator.flow.attributes

import com.github.javaparser.ast.expr.*
import com.github.javaparser.ast.stmt.IfStmt
import com.github.javaparser.ast.stmt.ReturnStmt
import com.laidpack.sourcerer.generator.*
import com.laidpack.sourcerer.generator.resources.StyleableAttributeFormat

class TypeValueResolver(private val ifStmt: IfStmt, private val flow: AttributeFlow) {
    fun resolveTypeValueConditions() {
        //TODO: findOrCreate support for switch statements and
        if (ifStmt.hasElseBlock()) {
            val returnStatements = ifStmt.elseStmt.get().descendantsOfType(ReturnStmt::class.java)
            if (returnStatements.isNotEmpty() && returnStatements.size == 1) {
                val returnStmt = returnStatements.first()
                val hasNoValueCondition = Condition(ConditionType.HasValue, "false")
                addConditionalImpact(setOf(hasNoValueCondition), returnStmt)
            } else if (returnStatements.size > 1) throw TODO("not yet implemented: multiple return statements in case of no value")
        }
        val hasValueCondition = Condition(ConditionType.HasValue, "true")
        val returnStmts = ifStmt.thenStmt.descendantsOfType(ReturnStmt::class.java)
        for(returnStmt in returnStmts) {
            val inBetweenIfStmts = returnStmt.ancestorsOfTypeUntil(ifStmt::class.java, ifStmt)
            val conditions = mutableSetOf(hasValueCondition)
            for(inBetweenIfStmt in inBetweenIfStmts) {
                val inThenBlock = inBetweenIfStmt.thenStmt.descendantsOfType(returnStmt::class.java).any { it == returnStmt }
                val condition = resolveCondition(inBetweenIfStmt.condition, inThenBlock, conditions)
                conditions.add(condition)
            }
            addConditionalImpact(conditions, returnStmt)
        }
    }

    private fun resolveCondition(condition: Expression, inThenBlock: Boolean, otherConditions: Set<Condition>): Condition {
        return when (condition) {
            is BinaryExpr -> resolveBinaryCondition(condition, inThenBlock, otherConditions)
            else -> throw IllegalStateException("Condition '$condition' could not be resolved")
        }
    }

    private fun resolveBinaryCondition(condition: BinaryExpr, inThenBlock: Boolean, otherConditions: Set<Condition>): Condition {
        val isAccessingFieldFromTypeValue = isAccessingFieldFromTypeValue(condition)
        val fieldName = if(isAccessingFieldFromTypeValue) condition.left.asFieldAccessExpr().name.asString() else ""
        when {
            isAccessingFieldFromTypeValue && fieldName == "type" ->
                return resolveTypeCondition(condition.right.asFieldAccessExpr().nameAsString, inThenBlock)
            isAccessingFieldFromTypeValue && fieldName == "data" ->
                return resolveValueCondition(condition.right.asIntegerLiteralExpr().value, inThenBlock, otherConditions)
        }

        throw IllegalStateException("Binary expression condition '$condition' could not be resolved")

    }

    private fun isAccessingFieldFromTypeValue(condition: BinaryExpr): Boolean {
        return condition.left is FieldAccessExpr
                && isAccessingFieldFromTypeValue(condition.left.asFieldAccessExpr())
    }
    private fun isAccessingFieldFromTypeValue(expr: FieldAccessExpr): Boolean {
        return expr.asFieldAccessExpr().scope is NameExpr
                && expr.asFieldAccessExpr().scope.asNameExpr().name == flow.typedValueVariableName
    }

    private fun resolveTypeCondition(type: String, inThenBlock: Boolean): Condition {
        val conditionType = if (inThenBlock) ConditionType.IsType else ConditionType.IsNotType
        return when {
            type == "TYPE_INT_BOOLEAN" -> Condition(conditionType, Boolean::class.java.canonicalName)
            type == "TYPE_REFERENCE" -> Condition(conditionType, StyleableAttributeFormat.referenceQualifierClassName.canonicalName)
            type == "TYPE_STRING" -> Condition(conditionType, String::class.java.canonicalName)
            type == "TYPE_FLOAT" -> Condition(conditionType, Float::class.java.canonicalName)
            type == "TYPE_DIMENSION" -> Condition(conditionType, StyleableAttributeFormat.dimensionQualifierClassName.canonicalName)
            type.contains("COLOR") -> Condition(conditionType, StyleableAttributeFormat.colorQualifierClassName.canonicalName)
            else -> throw IllegalStateException("Type condition with value '$type' could not be resolved")
        }
    }
    private fun resolveValueCondition(value: String, inThenBlock: Boolean, otherConditions: Set<Condition>): Condition {
        val isBoolean = otherConditions.any { c ->
            c.type == ConditionType.IsType && c.value == Boolean::class.java.canonicalName
        }
        return when {
            isBoolean && value == "1" -> Condition(ConditionType.Boolean, if (inThenBlock) "true" else "false")
            isBoolean && value == "0" -> Condition(ConditionType.Boolean, if (inThenBlock) "false" else "true")
            else -> Condition(ConditionType.Int, value)
        }
    }

    private data class ResolvedReturnStatement(val valueExpression: Expression, val additionalCondition: Condition? = null)
    private fun resolveReturnStatement(returnStmt: ReturnStmt, conditions: Set<Condition>): List<ResolvedReturnStatement> {
        if (!returnStmt.expression.isPresent) throw IllegalStateException("Return statement has no expression? Set by intermediate var?")

        val valueExpression = returnStmt.expression.get()
        val isAccessingFieldFromTypeValue = valueExpression is FieldAccessExpr && isAccessingFieldFromTypeValue(valueExpression)
        val fieldName = if(isAccessingFieldFromTypeValue) valueExpression.asFieldAccessExpr().name.asString() else ""

        return when {
            valueExpression is NameExpr -> return listOf(ResolvedReturnStatement(valueExpression))
            isAccessingFieldFromTypeValue && fieldName == "data" -> return listOf(ResolvedReturnStatement(valueExpression))
            valueExpression is EnclosedExpr && valueExpression.inner.isConditionalExpr -> {
                val conditionalExpr = valueExpression.inner.asConditionalExpr()
                val conditionForThen= resolveCondition(conditionalExpr.condition, true, conditions)
                val conditionForElse = resolveCondition(conditionalExpr.condition, false, conditions)
                return listOf(
                        ResolvedReturnStatement(conditionalExpr.thenExpr, conditionForThen),
                        ResolvedReturnStatement(conditionalExpr.elseExpr, conditionForElse)
                )
            }

            else -> throw IllegalStateException("Could not resolve return statement '$returnStmt' with expression '$valueExpression'")
        }
    }

    private fun addConditionalImpact(conditions: Set<Condition>, returnStmt: ReturnStmt) {
        val resolvedReturnStatements = resolveReturnStatement(returnStmt, conditions)
        if (resolvedReturnStatements.size == 1 && resolvedReturnStatements.first().additionalCondition == null) {
            val conditionalImpact = ConditionalImpact(conditions, resolvedReturnStatements.first().valueExpression)
            flow.addConditionalImpactToVariableAssignedByNestedMethod(conditionalImpact)
        } else {
            for (resolvedReturnStatement in resolvedReturnStatements) {
                val conditionsForThisValue = mutableSetOf<Condition>()
                conditionsForThisValue.addAll(conditions)
                if (resolvedReturnStatement.additionalCondition != null) {
                    conditionsForThisValue.add(resolvedReturnStatement.additionalCondition)
                }
                val conditionalImpact = ConditionalImpact(conditionsForThisValue, resolvedReturnStatement.valueExpression)
                flow.addConditionalImpactToVariableAssignedByNestedMethod(conditionalImpact)
            }
        }
    }
}