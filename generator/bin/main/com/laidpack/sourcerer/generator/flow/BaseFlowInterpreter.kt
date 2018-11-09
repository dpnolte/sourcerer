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

    data class NodeIterationResult (val canContinue: Boolean, val isHandled: Boolean, val handlerNames: List<String> = listOf())
    fun onNodeIteration(node: Node, level: Int): NodeIterationResult {
        currentLevel = level
        beforeInvokingHandlers()
        // activate listeners based on type
        if (mappedHandlers.containsKey(node::class)) {
            val handlers = mappedHandlers[node::class] as List<INodeHandler>
            val handlerNames = mutableListOf<String>()
            var canContinue = true
            for (handler in handlers) {
                val result = handler.nodeHandler(node)
                handlerNames.add(handler::class.java.simpleName)
                if (!result) canContinue = false
            }
            return NodeIterationResult(canContinue, true, handlerNames)
        }

        return NodeIterationResult(true, false)
    }
}

