package com.laidpack.sourcerer.generator.javadoc

import com.laidpack.sourcerer.generator.*
import com.laidpack.sourcerer.generator.peeker.ClassInfo
import com.laidpack.sourcerer.generator.peeker.ClassRegistry
import com.laidpack.sourcerer.generator.peeker.TypedArrayInfo

class JavaDocInterpreter(
        private val classInfo: ClassInfo,
        private val attributeManager: AttributeManager
) : Interpreter {
    override fun interpret(): InterpretationResult {
        val interpretations = mutableListOf<Interpretation>()
        classInfo.getMethodsWithAttributeTagInComments().forEach { relevantMethod ->
            val interpreter = JavaDocForSetterInterpreter(relevantMethod, classInfo, attributeManager)
            val interpretation = interpreter.interpret()
            interpretations.add(interpretation)
        }
        return InterpretationResult(
                "javadoc",
                interpretations
        )
    }


    companion object : InterpreterFactory {
        override fun create(
                classInfo: ClassInfo,
                typedArrayInfo: TypedArrayInfo,
                attrManager: AttributeManager,
                classRegistry: ClassRegistry
        ): Interpreter {
            return JavaDocInterpreter(classInfo, attrManager)
        }
    }
}