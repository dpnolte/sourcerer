package com.laidpack.sourcerer.generator.flow.attributes.handlers

import com.github.javaparser.ast.stmt.BreakStmt
import com.laidpack.sourcerer.generator.flow.NodeHandler
import com.laidpack.sourcerer.generator.flow.attributes.AttributeFlow
import com.laidpack.sourcerer.generator.flow.attributes.BaseAttributesHandler

class ConditionalToAttrBreakStmtHandler(flow: AttributeFlow): BaseAttributesHandler<BreakStmt>(flow, BreakStmt::class) {
    override val handler: NodeHandler<BreakStmt> = ::onResourceIdEntryBreak

    private fun onResourceIdEntryBreak(node: BreakStmt): Boolean {
        // TODO: findOrCreate support for nested switch statements..?
        if (flow.isConditionalToAttributeViaSwitchStmt) {
            flow.unflagAttributeAsConditional()
        }
        return true
    }
}