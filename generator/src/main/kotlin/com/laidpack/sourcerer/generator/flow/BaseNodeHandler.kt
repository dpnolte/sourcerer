package com.laidpack.sourcerer.generator.flow

import com.github.javaparser.ast.Node
import kotlin.reflect.KClass
import kotlin.system.measureTimeMillis

interface INodeHandler {
    fun nodeHandler(node: Node) : Boolean
    val targetType: KClass<*>
}

abstract class BaseNodeHandler <T: Node> (
        override val targetType: KClass<T>
) : INodeHandler {
    override fun nodeHandler (node: Node): Boolean {
        if (targetType.java.isInstance(node)) {
            var result : Boolean = false
            val executionTime = measureTimeMillis {
                result = handler(targetType.java.cast(node))
            }
            if (executionTimes.containsKey(this::class)) {
                timesExecuted[this::class] = (timesExecuted[this::class] as Int) + 1
                executionTimes[this::class] =  (executionTimes[this::class] as Long) + executionTime
            } else {
                timesExecuted[this::class] = 1
                executionTimes[this::class] = executionTime
            }
            averageExecutionTimes[this::class] = (executionTimes[this::class] as Long)/(timesExecuted[this::class] as Int)
            return result
        }
        throw IllegalStateException("Invalid target type $targetType and classOrInterfaceDeclarationProvider type ${node::class}")
    }
    abstract val handler : NodeHandler<T>

    companion object {
        private val executionTimes = mutableMapOf<KClass<*>, Long>()
        private val averageExecutionTimes = mutableMapOf<KClass<*>, Long>()
        private val timesExecuted = mutableMapOf<KClass<*>, Int>()
        fun printExecutionTimes() {
            println("Flow Interpreter handler execution times:")
            println("=".repeat(125))
            for ((handlerClass, executionTime) in executionTimes) {
                val formattedHandlerClass = handlerClass.java.simpleName.format("%-40s")
                val formattedExecutionTime = executionTime.toString().format("%-10s")
                val formattedTimesExecuted = timesExecuted[handlerClass].toString().format("%-5s")
                val formattedAverageExecutionTime = averageExecutionTimes[handlerClass].toString().format("%-10s")
                println("| $formattedHandlerClass| $formattedExecutionTime ms| $formattedTimesExecuted x| $formattedAverageExecutionTime ms|")
            }
            println("=".repeat(125))
        }
    }
}

typealias NodeHandler<T> = (node: T) -> Boolean