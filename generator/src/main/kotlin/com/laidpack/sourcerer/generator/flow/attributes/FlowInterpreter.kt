package com.laidpack.sourcerer.generator.flow.attributes

import com.laidpack.sourcerer.generator.*
import com.laidpack.sourcerer.generator.peeker.ClassCategory
import com.laidpack.sourcerer.generator.peeker.ClassInfo
import com.laidpack.sourcerer.generator.peeker.TypedArrayInfo


class FlowInterpreter (
        private val classInfo: ClassInfo,
        private val typedArrayInfo: TypedArrayInfo,
        private val attributeManager: AttributeManager
) : Interpreter {
    private val interpretations = mutableListOf<Interpretation>()

    override fun interpret(): InterpretationResult {
        if (classInfo.classCategory == ClassCategory.View) {
            classInfo.getMethodLikeWithAttributeSetAsParam().forEach { relevantConstructorOrMethod ->
                val methodFlowInterpreter = FlowInMethodInterpreter(relevantConstructorOrMethod, classInfo, typedArrayInfo, attributeManager)
                val interpretation = methodFlowInterpreter.interpret()
                interpretations.add(interpretation)
            }
        } else {
            println("No flow interpretation for lp classes")
        }
        return InterpretationResult("flow", interpretations)
    }

    companion object : InterpreterFactory {
        override fun create(classInfo: ClassInfo, typedArrayInfo: TypedArrayInfo, attrManager: AttributeManager): Interpreter {
            return FlowInterpreter(
                    classInfo,
                    typedArrayInfo,
                    attrManager
            )
        }


    }

}



