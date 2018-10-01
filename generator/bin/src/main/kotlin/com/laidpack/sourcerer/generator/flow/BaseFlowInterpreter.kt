package com.laidpack.sourcerer.generator.flow

import com.github.javaparser.ast.Node
import kotlin.reflect.KClass

abstract class BaseFlowInterpreter {
    var currentLevel: Int = 0

    protected abstract val handlers : List<INodeHandler>
    private val mappedHandlers : Map<KClass<*>, List<INodeHandler>> by lazy {
        if (mutableMappedHandlers.isEmpty()) {
            mutableMappedHandlers.putAll(handlers.groupBy { it.targetType })
        }
        mutableMappedHandlers
    }
    private val mutableMappedHandlers : MutableMap<KClass<*>, List<INodeHandler>> = mutableMapOf()

    open fun beforeInvokingHandlers() {}

    fun onNodeIteration(node: Node, level: Int): Boolean {
        currentLevel = level
        beforeInvokingHandlers()
        // activate listeners based on type
        if (mappedHandlers.containsKey(node::class)) {
            val handlers = mappedHandlers[node::class] as List<INodeHandler>
            var canContinue = true
            for (handler in handlers) {
                val result = handler.nodeHandler(node)
                if (!result) canContinue = false
            }
            if (!canContinue) return false
        }

        return true
    }
}

