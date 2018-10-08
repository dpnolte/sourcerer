package com.laidpack.sourcerer.generator.flow.attributes

import com.github.javaparser.ast.body.CallableDeclaration
import com.github.javaparser.ast.expr.SimpleName
import com.github.javaparser.resolution.declarations.ResolvedMethodLikeDeclaration
import com.laidpack.sourcerer.generator.flow.BaseFlowInterpreter
import com.laidpack.sourcerer.generator.flow.attributes.handlers.*
import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.AttributeManager
import com.laidpack.sourcerer.generator.Interpretation
import com.laidpack.sourcerer.generator.flow.FlowVisitor
import com.laidpack.sourcerer.generator.target.Setter
import com.laidpack.sourcerer.generator.peeker.*


class FlowInMethodInterpreter (
        private val relevantConstructorOrMethod: Pair<ResolvedMethodLikeDeclaration, CallableDeclaration<*>>,
        classInfo: ClassInfo,
        typedArrayInfo: TypedArrayInfo,
        attrManager: AttributeManager,
        classRegistry: ClassRegistry
) : BaseFlowInterpreter()  {
    private val flow = AttributeFlow(
            this.getAttributeSetVariableNameFromParam(),
            attrManager,
            this,
            classInfo,
            typedArrayInfo,
            this.getParametersAsVariables(),
            classRegistry
    )
    private val visitor = FlowVisitor(this::onNodeIteration)
    lateinit var attributes : Map<String, Attribute>
    lateinit var setters: Map<Int, Setter>


    override val handlers = listOf(
            VariableDeclarationHandler(flow),
            SwitchEntryStmtHandler(flow),
            SwitchStmtHandler(flow),
            ConditionalToAttrBreakStmtHandler(flow),
            ConditionalToAttrAssignHandler(flow),
            ConditionalToAttrMethodCallHandler(flow),
            ConditionalToAttrIfStmtHandler(flow),
            UnconditionalToAttrMethodCallHandler(flow),
            UnconditionalToAttrAssignHandler(flow),
            IfStmtHandler(flow)
    )

    override fun beforeInvokingHandlers() {
        flow.beforeInvokingHandlers(this)
    }

    fun interpret(): Interpretation {
        visitor.explore(relevantConstructorOrMethod.second)
        flow.injectAttributes(this)
        flow.injectSetters(this)
        return Interpretation(attributes, setters)
    }

    fun interpretNestedMethod(methodInfo: MethodInfo) {
        visitor.explore(methodInfo.methodDeclaration, flow.currentLevel + 1)
    }

    private fun getAttributeSetVariableNameFromParam(): SimpleName {
        for (parameter in relevantConstructorOrMethod.second.parameters) {
            if (parameter.type.isClassOrInterfaceType) {
                val indexedClass = ClassSymbolResolver.resolveClassOrInterfaceType(parameter.type.asClassOrInterfaceType())
                if (indexedClass.targetClassName.canonicalName == SourcePeeker.attributeSetClassType.canonicalName) {
                    return parameter.name
                }
            }
        }
        throw IllegalStateException("relevantConstructorOrMethod has no AttributeSet parameter. Is peeker really peeking the right method constructor?")
    }

    private fun getParametersAsVariables(): List<Variable> {
        val variables = mutableListOf<Variable>()
        for (parameter in relevantConstructorOrMethod.second.parameters) {
            variables.add(Variable(parameter.name, parameter.type, parameter.nameAsExpression))
        }
        return variables
    }


}



