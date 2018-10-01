package com.laidpack.sourcerer.generator.flow.setter

import com.github.javaparser.ast.body.FieldDeclaration
import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.Setter
import com.laidpack.sourcerer.generator.flow.BaseFlowInterpreter
import com.laidpack.sourcerer.generator.flow.setter.handlers.AssignHandler
import com.laidpack.sourcerer.generator.peeker.ClassInfo
import com.laidpack.sourcerer.generator.peeker.MethodInfo

data class GetterRequirements(val fields: List<FieldDeclaration>, val conditions: Map<String /* var name */, String /* value as string */>)

class SetterFlowInterpreter(
        private val setterInfo: MethodInfo,
        setter: Setter,
        attribute: Attribute,
        classInfo: ClassInfo
    ) : BaseFlowInterpreter() {

    private val visitor = SetterVisitor(this::onNodeIteration)
    private val parameter = setter.parameters[setter.attributeToParameter[attribute.name] as Int]
    private val flow = SetterFlow(this, setterInfo, setter, parameter, attribute, classInfo)

    override val handlers = listOf(
            AssignHandler(flow)
    )

    fun resolveGetterRequirements() : GetterRequirements {
        visitor.explore(setterInfo.methodDeclaration)
        return flow.getGetterRequirements()
    }
}
