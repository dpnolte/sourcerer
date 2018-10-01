package com.laidpack.sourcerer.generator.flow.attributes.handlers

import com.github.javaparser.ast.stmt.SwitchEntryStmt
import com.laidpack.sourcerer.generator.flow.attributes.AttributeFlow
import com.laidpack.sourcerer.generator.flow.attributes.BaseAttributesHandler
import com.laidpack.sourcerer.generator.flow.attributes.ConditionStmt

class SwitchEntryStmtHandler(flow: AttributeFlow) : BaseAttributesHandler<SwitchEntryStmt>(flow, SwitchEntryStmt::class) {
    override val handler = ::onSwitchResourceIdEntry

    private fun onSwitchResourceIdEntry(node: SwitchEntryStmt): Boolean {
        if (flow.withinSwitchResourceIdBlock && node.label.isPresent ) {
            val attributeResourceName = node.label.get().toString()
            flow.addAttribute(attributeResourceName)
            flow.flagAttributeAsConditional(attributeResourceName, ConditionStmt.SWITCH_ENTRY)
        }
        return true
    }
}