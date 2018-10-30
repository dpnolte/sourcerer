package com.laidpack.sourcerer.generator

import com.github.javaparser.ast.Node
import com.github.javaparser.ast.body.*
import com.github.javaparser.ast.expr.ObjectCreationExpr
import java.util.function.BiPredicate

// based on: https://tomassetti.me/resolve-method-calls-using-java-symbol-solver/
class SpecificNodeIterator<T>(
        private val type: Class<T>,
        private val nodeHandler: (node: T) -> Boolean
) {
    fun explore(node: Node, path: MutableList<Int> = mutableListOf()) {
        if (type.isInstance(node)) {
            if (!nodeHandler(type.cast(node))) {
                return
            }
        }
        node.childNodes.forEachIndexed { index, child ->
            explore(child, path)
        }
    }
}
class PathAwareNodeIterator<T>(
        private val type: Class<T>,
        private val nodeHandler: (node: T, path: List<Int>) -> Boolean
) {
    fun explore(node: Node, path: List<Int> = listOf()) {
        if (type.isInstance(node)) {
            if (!nodeHandler(type.cast(node), path)) {
                return
            }
        }
        node.childNodes.forEachIndexed { index, child ->
            val nextPath = path.toMutableList()
            nextPath.add(index)
            explore(child, nextPath)
        }
    }
}

fun <T> Node.descendantsOfType(
        type: Class<T>,
        predicate: (T) -> Boolean = {true}
) : List<T> {
    val descendants = mutableListOf<T>()
    SpecificNodeIterator(
            type,
            nodeHandler =  fun(node: T): Boolean {
                if (predicate(node)) {
                    descendants.add(node)
                }
                return true
            }
    ).explore(this)
    return descendants
}
fun <T> Node.firstDescendantOfType(type: Class<T>, predicate: (T) -> Boolean = {true}) : T? {
    var descendant : T? = null
    SpecificNodeIterator(type, fun(node: T): Boolean {
        if (predicate(node)) {
            descendant = node
            return false
        }
        return true
    }).explore(this)
    return descendant
}

fun <T> Node.descendantsAndPatshOfType(
        type: Class<T>,
        predicate: (T) -> Boolean = {true}
) : List<Pair<T, List<Int>>> {
    val descendants = mutableListOf<Pair<T, List<Int>>>()
    PathAwareNodeIterator(
            type,
            nodeHandler =  fun(node, path): Boolean {
                if (predicate(node)) {
                    descendants.add(Pair(node, path))
                }
                return true
            }
    ).explore(this)
    return descendants
}

class ReverseSpecificNodeIterator<T>(private val type: Class<T>, private val nodeHandler: (node: T) -> Boolean) {
    fun explore(node: Node) {
        if (type.isInstance(node)) {
            if (!nodeHandler(type.cast(node))) {
                return
            }
        }
        if (node.parentNode.isPresent) {
            explore(node.parentNode.get())
        }
    }
}

fun <T> Node.ancestorsOfType(type: Class<T>) : List<T> {
    val ancestors = mutableListOf<T>()
    val startingNode = this
    ReverseSpecificNodeIterator(type, fun(node: T): Boolean {
        if (node != startingNode) {
            ancestors.add(node)
        }
        return true
    }).explore(this)
    return ancestors
}
fun <T> Node.firstAncestorOfType(type: Class<T>) : T? {
    var ancestor : T? = null
    val startingNode = this
    ReverseSpecificNodeIterator(type, fun(node: T): Boolean {
        if (node != startingNode) {
            ancestor = node
            return false
        }
        return true
    }).explore(this)
    return ancestor
}


fun <T> Node.ancestorsOfTypeUntil(type: Class<T>, upToNode: Node) : List<T> {
    val ancestors = mutableListOf<T>()
    val startingNode = this
    ReverseSpecificNodeIterator(type, fun(node: T): Boolean {
        if (node == upToNode) return false
        if (node != startingNode) {
            ancestors.add(node)
        }
        return true
    }).explore(this)
    return ancestors
}

