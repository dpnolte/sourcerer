package com.laidpack.sourcerer.generator.flow.attributes.handlers

import com.github.javaparser.ast.expr.NameExpr
import com.github.javaparser.ast.stmt.SwitchStmt
import com.laidpack.sourcerer.generator.flow.attributes.AttributeFlow
import com.laidpack.sourcerer.generator.flow.attributes.BaseAttributesHandler

class SwitchStmtHandler(flow: AttributeFlow) : BaseAttributesHandler<SwitchStmt>(flow, SwitchStmt::class) {
    override val handler = ::onSwitchResourceId

    private fun onSwitchResourceId(node: SwitchStmt): Boolean {
        val selector = node.selector
        if (selector is NameExpr && selector.name == flow.resourceIdVariableName) {
            flow.flagWithinSwitchResourceIdBlock()
        }
        return true
    }
}