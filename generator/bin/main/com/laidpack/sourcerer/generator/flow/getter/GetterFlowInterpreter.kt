package com.laidpack.sourcerer.generator.flow.getter

import com.github.javaparser.ast.body.MethodDeclaration
import com.laidpack.sourcerer.generator.flow.BaseFlowInterpreter
import com.laidpack.sourcerer.generator.flow.FlowVisitor
import com.laidpack.sourcerer.generator.flow.getter.handlers.ReturnStmtHandler
import com.laidpack.sourcerer.generator.index.ClassInfo

class GetterFlowInterpreter(
        private val getterDeclaration: MethodDeclaration,
        variableName: String,
        conditions: Map<String, String>,
        classInfo: ClassInfo
    ) : BaseFlowInterpreter() {

    private val visitor = FlowVisitor(this::onNodeIteration)
    private val flow = GetterFlow(
            variableName,
            conditions,
            classInfo
    )

    override val handlers = listOf(
            ReturnStmtHandler(flow)
    )

    data class EligibilityResult(val eligible: Boolean, val fulfilledConditionCount: Int = 0)
    fun checkEligibility() : EligibilityResult {
        visitor.explore(getterDeclaration)
        return EligibilityResult(flow.isGetterEligible, flow.conditionCount)
    }

}
