package com.laidpack.sourcerer.generator.index

import com.github.javaparser.ast.Node
import com.github.javaparser.ast.body.AnnotationDeclaration
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.VariableDeclarator
import com.github.javaparser.ast.expr.*
import com.laidpack.sourcerer.generator.firstAncestorOfType
import com.laidpack.sourcerer.generator.firstDescendantOfType


object IntValueExtractor {

    fun resolveIntValueFromNode(node: Node): Int {
        val initializerExpr = node.firstDescendantOfType(IntegerLiteralExpr::class.java)
        if (initializerExpr != null) {
            return initializerExpr.asInt()
        } else {
            val fieldAccessExpr = node.firstDescendantOfType(FieldAccessExpr::class.java)
            if (fieldAccessExpr != null) {
                return resolveFieldAccessExpr(fieldAccessExpr)
                        ?: throw IllegalStateException("Could not resolve int value for node $node with field access expression $fieldAccessExpr")
            }
            val binaryExpr = node.firstDescendantOfType(BinaryExpr::class.java)
            if (binaryExpr != null) {
                return resolveBinaryExpr(binaryExpr)
                        ?: throw IllegalStateException("Could not resolve int value for node $node with binary expression $binaryExpr")
            }
            val nameExpr = node.firstDescendantOfType(NameExpr::class.java)
                    ?: throw IllegalArgumentException("Cannot resolve node to Int. No integer literal expression, field access expression, binary expression, or name expression found for getClassOrInterfaceDeclaration '$node'")

            return resolveNameExpr(nameExpr)
        }

    }

    private fun resolveFieldAccessExpr(fieldAccessExpr: FieldAccessExpr): Int? {
        if (isIntegerMinOrMaxValue(fieldAccessExpr)) {
            return if (fieldAccessExpr.nameAsString == "MIN_VALUE") {
                Int.MIN_VALUE
            } else Int.MAX_VALUE
        } else {
            return resolveIntValueFromNode(findVariable(fieldAccessExpr)
                    ?: return null)
        }
    }

    private fun resolveBinaryExpr(binaryExpr: BinaryExpr): Int? {
        val leftHandSide = resolveIntValueFromNode(binaryExpr.left)
        val rightHandSide = resolveIntValueFromNode(binaryExpr.right)

        return when (binaryExpr.operator) {
            BinaryExpr.Operator.BINARY_OR -> leftHandSide.or(rightHandSide)
            BinaryExpr.Operator.BINARY_AND -> leftHandSide.and(rightHandSide)
            BinaryExpr.Operator.XOR -> leftHandSide.xor(rightHandSide)
            BinaryExpr.Operator.LEFT_SHIFT -> leftHandSide.shl(rightHandSide)
            BinaryExpr.Operator.SIGNED_RIGHT_SHIFT -> leftHandSide.shr(rightHandSide)
            BinaryExpr.Operator.UNSIGNED_RIGHT_SHIFT -> leftHandSide.ushr(rightHandSide)
            BinaryExpr.Operator.PLUS -> leftHandSide + rightHandSide
            BinaryExpr.Operator.MINUS -> leftHandSide - rightHandSide
            BinaryExpr.Operator.MULTIPLY -> leftHandSide * rightHandSide
            BinaryExpr.Operator.DIVIDE -> leftHandSide / rightHandSide
            BinaryExpr.Operator.REMAINDER -> leftHandSide % rightHandSide
            else -> null
        }
    }

    private fun resolveNameExpr(nameExpr: NameExpr): Int {
        val classDeclaration = nameExpr.firstAncestorOfType(ClassOrInterfaceDeclaration::class.java)
        val variableDeclarator = classDeclaration?.firstDescendantOfType(
                VariableDeclarator::class.java
        ) { n -> n.nameAsString == nameExpr.nameAsString}
                ?: throw java.lang.IllegalArgumentException("No variable found with name $nameExpr")
        return resolveIntValueFromNode(variableDeclarator)
    }

    private fun findVariable(fieldAccessExpr: FieldAccessExpr): VariableDeclarator? {
        val simpleName = fieldAccessExpr.scope.asNameExpr().nameAsString
        val fieldName = fieldAccessExpr.name.asString()
        val foundClasses = DeclaredTypeRegistry.findBySimpleName(simpleName)
        if (foundClasses.isNotEmpty()) {
            for (foundClass in foundClasses) {
                val typeDeclaration = foundClass.getTypeDeclaration()
                return typeDeclaration.firstDescendantOfType(
                        VariableDeclarator::class.java
                ) { v -> v.nameAsString == fieldName } ?: continue
            }
        } else {
            // check if it is defined with an annotation declaratino
            // example: @interface Gutter { int NONE = 0; }
            val rootNode = fieldAccessExpr.findRootNode()
            val annotationDeclaration = rootNode.firstDescendantOfType(
                    AnnotationDeclaration::class.java
            ) { n -> n.nameAsString == simpleName}
                    ?: throw IllegalStateException("Ambiguous field access expression '$fieldAccessExpr'. No indexed types found with that name")

            return annotationDeclaration.firstDescendantOfType(
                    VariableDeclarator::class.java
            ) { v -> v.nameAsString == fieldName }
        }
        return null
    }

    private fun isIntegerMinOrMaxValue(fieldAccessExpr: FieldAccessExpr): Boolean {
        return fieldAccessExpr.scope.isNameExpr
                && fieldAccessExpr.scope.asNameExpr().nameAsString == "Integer"
                && (fieldAccessExpr.nameAsString == "MIN_VALUE" || fieldAccessExpr.nameAsString == "MAX_VALUE")
    }
}