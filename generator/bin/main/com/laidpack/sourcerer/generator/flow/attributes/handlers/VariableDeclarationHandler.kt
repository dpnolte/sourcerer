package com.laidpack.sourcerer.generator.flow.attributes.handlers

import android.util.TypedValue
import com.github.javaparser.ast.body.VariableDeclarator
import com.laidpack.sourcerer.generator.flow.attributes.AttributeFlow
import com.laidpack.sourcerer.generator.flow.attributes.BaseAttributesHandler

internal class VariableDeclarationHandler(flow: AttributeFlow): BaseAttributesHandler<VariableDeclarator>(flow, VariableDeclarator::class) {
    override val handler = ::onVariableDeclaration

    private fun onVariableDeclaration(node: VariableDeclarator): Boolean {
        val initializerExpr = if (node.initializer.isPresent) {
            node.initializer.get()
        } else node.nameAsExpression
        flow.addVariable(node, initializerExpr)
        if (isTypedValueBeingDeclared(node)) {
            handleTypedValueBeingDeclared(node)
        }

        return true
    }

    private fun isTypedValueBeingDeclared(node: VariableDeclarator): Boolean {
        return node.type.isClassOrInterfaceType
                && node.type.asClassOrInterfaceType().nameAsString == "TypedValue"
                && node.type.resolve().describe() == typedValueCanonicalName
    }

    private fun handleTypedValueBeingDeclared(node: VariableDeclarator) {
        flow.setTypedValueVariable(node.name)
    }

    companion object {
        val typedValueCanonicalName: String = TypedValue::class.java.canonicalName
    }

}