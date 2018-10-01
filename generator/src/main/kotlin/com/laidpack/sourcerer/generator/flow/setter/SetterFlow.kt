package com.laidpack.sourcerer.generator.flow.setter


import com.github.javaparser.ast.body.FieldDeclaration
import com.github.javaparser.ast.expr.AssignExpr
import com.github.javaparser.ast.expr.Expression
import com.github.javaparser.ast.expr.LiteralStringValueExpr
import com.github.javaparser.ast.expr.NameExpr
import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.Setter
import com.laidpack.sourcerer.generator.descendantsOfType
import com.laidpack.sourcerer.generator.peeker.ClassInfo
import com.laidpack.sourcerer.generator.peeker.MethodInfo
import com.laidpack.sourcerer.generator.target.Parameter

class SetterFlow (
        private val interpreter: SetterFlowInterpreter,
        private val setterInfo: MethodInfo,
        private val setter: Setter,
        private val parameter: Parameter,
        private val attribute: Attribute,
        val classInfo: ClassInfo
) {
    private val fields = mutableListOf<FieldDeclaration>()
    private val conditions = mutableMapOf<String /* var name */, String /* value */>()

    fun isParameterBeingAssigned(expression: Expression): Boolean {
        val nameExpressions = expression.descendantsOfType(NameExpr::class.java)
        return nameExpressions.any {
            it.nameAsString == parameter.name
        }
    }

    fun isValueBeingAssigned(expression: Expression): Boolean {
        val assignExpression = expression.descendantsOfType(AssignExpr::class.java).last()
        if (assignExpression.value.isNameExpr) {
            val name = assignExpression.value.asNameExpr().nameAsString
            if (classInfo.isFieldFromThisClass(name)) {
                val field = classInfo.getFieldFromThisClass(name)
                val variable = field.variables.first()
                // TODO: check for mutability?
                return variable.initializer.isPresent
                        && variable.initializer.get() is LiteralStringValueExpr
            }
        }
        return false
    }

    fun getAssignedValue(expression: Expression): String {
        val assignExpression = expression.descendantsOfType(AssignExpr::class.java).last()
        if (assignExpression.value.isNameExpr) {
            val name = assignExpression.value.asNameExpr().nameAsString
            if (classInfo.isFieldFromThisClass(name)) {
                val field = classInfo.getFieldFromThisClass(name)
                val variable = field.variables.first()
                if (variable.initializer.isPresent
                        && variable.initializer.get() is LiteralStringValueExpr) {
                    return variable.initializer.get().asLiteralStringValueExpr().value
                }
            }
        }
        throw IllegalStateException("Expression has no assigned value. Expression: '$expression'")
    }

    fun addFieldIfNew(fieldDeclaration: FieldDeclaration) {
        if (!fields.contains(fieldDeclaration)) {
            fields.add(fieldDeclaration)
        }
    }

    fun addConditionIfNew(name: String, value: String) {
        if (!conditions.containsKey(name)) {
            conditions[name] = value
        }
    }

    fun getGetterRequirements(): GetterRequirements {
        return GetterRequirements(fields, conditions)
    }
}