package com.laidpack.sourcerer.generator.flow

import com.github.javaparser.ast.Node
import kotlin.reflect.KClass

interface INodeHandler {
    fun nodeHandler(node: Node) : Boolean
    val targetType: KClass<*>
}

abstract class BaseHandler <T: Node> (
        override val targetType: KClass<T>
) : INodeHandler {
    override fun nodeHandler (node: Node): Boolean {
        if (targetType.java.isInstance(node)) {
            return handler(targetType.java.cast(node))
        }
        throw IllegalStateException("Invalid target type $targetType and node type ${node::class}")
    }
    abstract val handler : NodeHandler<T>

}

typealias NodeHandler<T> = (node: T) -> Boolean