package com.laidpack.sourcerer.generator.flow.getter


import com.github.javaparser.ast.body.FieldDeclaration
import com.github.javaparser.ast.expr.*
import com.github.javaparser.ast.stmt.Statement
import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.ancestorsOfType
import com.laidpack.sourcerer.generator.descendantsOfType
import com.laidpack.sourcerer.generator.peeker.ClassInfo
import com.laidpack.sourcerer.generator.peeker.MethodInfo

class GetterFlow (
        private val interpreter: GetterFlowInterpreter,
        private val potentialGetter: MethodInfo,
        private val field: FieldDeclaration,
        private val variableName: String,
        private val conditions: Map<String, String>,
        private val attribute: Attribute,
        val classInfo: ClassInfo
) {
    var isGetterEligible = false
        private set
    var conditionCount = 0
        private set



    fun getFieldFromStatement(statement: Statement): NameExpr? {
        val nameExpressions = statement.descendantsOfType(NameExpr::class.java)
        for(nameExpression in nameExpressions) {
            if (nameExpression.nameAsString == variableName) {
                return nameExpression
            }
        }
        return null
    }

    fun fieldIsReturnedAsPerConditions(nameExpr: NameExpr): Boolean {
        if (conditions.isEmpty()) return true

        val conditionalExpressions = nameExpr.ancestorsOfType(ConditionalExpr::class.java)
        for (conditionalExpression in conditionalExpressions) {
            if (conditionalExpression.condition is BinaryExpr) {
                val name = getVariableName(conditionalExpression.condition.asBinaryExpr())
                if (name != null) {
                    if (conditions.containsKey(name)) {
                        val value = getAssignedValue(conditionalExpression.condition.asBinaryExpr())
                        if (value != null && conditions[name] != value) {
                            return false // if condition is present in getter flow, it should match as per the setter
                        } else if (value != null && conditions[name] == value) {
                            conditionCount += 1
                        }
                    }
                }
            }
        }
        return true
    }

    private fun getVariableName(binaryExpr: BinaryExpr, leftHandSide : Boolean = true): String? {
        return when {
            leftHandSide && binaryExpr.left is NameExpr -> binaryExpr.left.asNameExpr().nameAsString
            !leftHandSide && binaryExpr.right is NameExpr -> binaryExpr.right.asNameExpr().nameAsString
            else -> null
        }
    }

    private fun getAssignedValue(binaryExpr: BinaryExpr): String? {
        if (binaryExpr.right is NameExpr) {
            val name = getVariableName(binaryExpr)
            if (name != null) {
                if (classInfo.isFieldFromThisClassOrSuperClass(name)) {
                    val variable = classInfo.getResolvedFieldFromThisClassOrSuperClass(name).variables.first()
                    if (variable.initializer.isPresent && variable.initializer.get() is LiteralStringValueExpr) {
                        return variable.initializer.get().asLiteralStringValueExpr().value
                    }
                }
            }
        }
        return null
    }


    fun flagGetterAsEligible() {
        isGetterEligible = true
    }

}