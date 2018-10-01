package com.laidpack.sourcerer.generator.flow.attributes

import com.github.javaparser.ast.body.CallableDeclaration
import com.github.javaparser.ast.expr.SimpleName
import com.github.javaparser.resolution.declarations.ResolvedMethodLikeDeclaration
import com.laidpack.sourcerer.generator.flow.BaseFlowInterpreter
import com.laidpack.sourcerer.generator.flow.attributes.handlers.*
import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.AttributeManager
import com.laidpack.sourcerer.generator.Interpretation
import com.laidpack.sourcerer.generator.target.Setter
import com.laidpack.sourcerer.generator.peeker.*


class FlowInMethodInterpreter (
        private val relevantConstructorOrMethod: Pair<ResolvedMethodLikeDeclaration, CallableDeclaration<*>>,
        private val classInfo: ClassInfo,
        private val typedArrayInfo: TypedArrayInfo,
        attrManager: AttributeManager
) : BaseFlowInterpreter()  {
    private val flow = AttributeFlow(
            this.getAttributeSetVariableNameFromParam(), attrManager,
            this,
            classInfo,
            typedArrayInfo
    )
    private val visitor = AttributeSetVisitor(this::onNodeIteration)
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
        for (index in 0 until relevantConstructorOrMethod.first.numberOfParams) {
            val parameter = relevantConstructorOrMethod.second.parameters[index]
            if (parameter.type.isClassOrInterfaceType) {
                val indexedClass = ClassSymbolResolver.resolveClassOrInterfaceType(parameter.type.asClassOrInterfaceType())
                if (indexedClass.targetClassName.canonicalName == SourcePeeker.attributeSetClassType.canonicalName) {
                    return parameter.name
                }
            }
        }
        throw IllegalStateException("relevantConstructorOrMethod has no AttributeSet parameter. Is peeker really peeking the right method constructor?")
    }


}



