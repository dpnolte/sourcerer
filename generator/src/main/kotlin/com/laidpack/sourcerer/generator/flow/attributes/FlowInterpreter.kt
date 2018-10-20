package com.laidpack.sourcerer.generator.flow.attributes

import com.laidpack.sourcerer.generator.*
import com.laidpack.sourcerer.generator.peeker.ClassInfo
import com.laidpack.sourcerer.generator.peeker.ClassRegistry
import com.laidpack.sourcerer.generator.peeker.TypedArrayInfo
import com.laidpack.sourcerer.generator.target.Setter


class FlowInterpreter (
        private val classInfo: ClassInfo,
        private val typedArrayInfo: TypedArrayInfo,
        private val attributeManager: AttributeManager,
        private val attributesDefinedInXml: Boolean
) : Interpreter {
    private val interpretations = mutableListOf<Interpretation>()

    override fun interpret(earlierIdentifiedSetters: Map<Int, Setter>): InterpretationResult {
        classInfo.getCallablesWithAttributeSetAsParameter().forEach { relevantConstructorOrMethod ->
            val methodFlowInterpreter = FlowInMethodInterpreter(
                    relevantConstructorOrMethod,
                    classInfo,
                    typedArrayInfo,
                    attributeManager,
                    attributesDefinedInXml
            )
            val interpretation = methodFlowInterpreter.interpret()
            interpretations.add(interpretation)
        }
        return InterpretationResult("flow", interpretations)
    }

    companion object : InterpreterFactory {
        override fun create(
                classInfo: ClassInfo,
                typedArrayInfo: TypedArrayInfo,
                attrManager: AttributeManager,
                attributesDefinedInXml: Boolean
        ): Interpreter {
            return FlowInterpreter(
                    classInfo,
                    typedArrayInfo,
                    attrManager,
                    attributesDefinedInXml
            )
        }


    }

}



