package com.laidpack.sourcerer.generator

import com.laidpack.sourcerer.generator.target.Attribute
import com.laidpack.sourcerer.generator.target.Setter
import com.laidpack.sourcerer.generator.peeker.ClassInfo
import com.laidpack.sourcerer.generator.peeker.ClassRegistry
import com.laidpack.sourcerer.generator.peeker.TypedArrayInfo

interface Interpreter {
    fun interpret(earlierIdentifiedSetters: Map<Int, Setter>): InterpretationResult
}

interface InterpreterFactory {
    fun create(
            classInfo: ClassInfo,
            typedArrayInfo: TypedArrayInfo,
            attrManager: AttributeManager,
            classRegistry: ClassRegistry
    ): Interpreter
}

data class Interpretation(val attributes: Map<String, Attribute>, val setters: Map<Int, Setter>)
data class InterpretationResult (
        val name: String,
        val interpretations: List<Interpretation>
) {
    val identified = interpretations.sumBy { it.attributes.size }
    var identifiedNew = 0
    var resolved = 0
}