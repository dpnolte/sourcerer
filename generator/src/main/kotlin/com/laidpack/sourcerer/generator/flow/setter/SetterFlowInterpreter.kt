package com.laidpack.sourcerer.generator.flow.setter

import com.github.javaparser.ast.body.FieldDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.Setter
import com.laidpack.sourcerer.generator.flow.BaseFlowInterpreter
import com.laidpack.sourcerer.generator.flow.FlowVisitor
import com.laidpack.sourcerer.generator.flow.setter.handlers.AssignHandler
import com.laidpack.sourcerer.generator.index.ClassInfo

data class GetterRequirements(
        val fields: Map<String /* var name */, FieldDeclaration>,
        val conditions: Map<String /* var name */, String /* value as string */>
)

class SetterFlowInterpreter(
        private val setterMethodDeclaration: MethodDeclaration,
        setter: Setter,
        attribute: Attribute,
        classInfo: ClassInfo,
        providedParameterIndex: Int? = null
    ) : BaseFlowInterpreter() {

    private val visitor = FlowVisitor(this::onNodeIteration)
    private val parameterIndex = providedParameterIndex ?: setter.callSignatureMaps[attribute.name]
    private val parameter = setter.parameters[parameterIndex]
    private val flow = SetterFlow(parameter, classInfo)

    override val handlers = listOf(
            AssignHandler(flow)
    )

    fun resolveGetterRequirements() : GetterRequirements {
        visitor.explore(setterMethodDeclaration)
        return flow.getGetterRequirements()
    }
}
