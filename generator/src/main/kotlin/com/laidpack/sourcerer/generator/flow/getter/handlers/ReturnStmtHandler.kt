package com.laidpack.sourcerer.generator.flow.getter.handlers

import com.github.javaparser.ast.stmt.ReturnStmt
import com.laidpack.sourcerer.generator.flow.getter.BaseGetterHandler
import com.laidpack.sourcerer.generator.flow.getter.GetterFlow

class ReturnStmtHandler (flow: GetterFlow) : BaseGetterHandler<ReturnStmt>(flow, ReturnStmt::class) {
    override val handler = ::onReturnStatement
    private fun onReturnStatement(node: ReturnStmt): Boolean {
        val fieldAsNameExpr = flow.getFieldFromStatement(node)
        if (fieldAsNameExpr != null && flow.fieldIsReturnedAsPerConditions(fieldAsNameExpr)) {
            flow.flagGetterAsEligible()
        }
        return false
    }
}