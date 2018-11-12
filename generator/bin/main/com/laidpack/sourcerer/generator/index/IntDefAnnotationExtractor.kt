package com.laidpack.sourcerer.generator.index

import com.github.javaparser.ast.Node
import com.github.javaparser.ast.body.AnnotationDeclaration
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.FieldDeclaration
import com.github.javaparser.ast.body.VariableDeclarator
import com.github.javaparser.ast.expr.*
import com.github.javaparser.ast.type.PrimitiveType
import com.laidpack.sourcerer.generator.firstAncestorOfType
import com.laidpack.sourcerer.generator.firstDescendantOfType

object IntDefAnnotationExtractor {

    private val integerMinValueVar = VariableDeclarator(
            PrimitiveType.intType(),
            "integerMin",
            IntegerLiteralExpr("${Int.MIN_VALUE}")
    )
    private val integerMaxValueVar = VariableDeclarator(
            PrimitiveType.intType(),
            "integerMin",
            IntegerLiteralExpr("${Int.MAX_VALUE}")
    )

    fun extractIntDefAnnotations(
            intDefinedAnnotations: List<AnnotationExpr>,
            fieldDeclarations: Map<String, FieldDeclaration>
    ): List<XdIntDefAnnotation> {
        val intDefAnnotations = mutableListOf<XdIntDefAnnotation>()
        for (annotationExpression in intDefinedAnnotations) {
            intDefAnnotations.add(extractIntDefinedAnnotation(annotationExpression, fieldDeclarations))

        }
        return intDefAnnotations
    }

    private fun extractIntDefinedAnnotation(
            intDefinedAnnotation: AnnotationExpr,
            fieldDeclarations: Map<String, FieldDeclaration>
    ): XdIntDefAnnotation {
        val arrayInitializerExpr =
                intDefinedAnnotation.firstDescendantOfType(ArrayInitializerExpr::class.java) as ArrayInitializerExpr
        val variables = arrayInitializerExpr.values.map {
            when {
                it is NameExpr -> {
                    val name = it.nameAsString
                    val field = fieldDeclarations[name] as FieldDeclaration
                    field.variables.first { it.nameAsString == name }
                }
                it is FieldAccessExpr && isIntegerMinOrMaxValue(it) -> {
                    if (it.nameAsString == "MIN_VALUE") {
                          integerMinValueVar
                    } else integerMaxValueVar
                }
                it is FieldAccessExpr -> {
                    val variable = findVariable(it) ?: throw IllegalStateException("Cannot find field '$it' in @IntDef annotation $intDefinedAnnotation")
                    variable
                }
                else -> throw IllegalStateException("Cannot convert '$it' to a variable")
            }
        }
        val xdIntDefAnnotation = XdIntDefAnnotation.new {
            this.name = intDefinedAnnotation.nameAsString
        }
        for (variable in variables) {
            xdIntDefAnnotation.values.add(XdIntDefAnnotationValue.new {
                this.name = variable.nameAsString.toLowerCase()
                this.value = IntValueExtractor.resolveIntValueFromNode(variable)
            })
        }
        return xdIntDefAnnotation
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