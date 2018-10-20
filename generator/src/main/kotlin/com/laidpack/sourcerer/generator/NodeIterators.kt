package com.laidpack.sourcerer.generator

import com.github.javaparser.ast.Node
import com.github.javaparser.ast.body.*
import com.github.javaparser.ast.expr.ObjectCreationExpr
import java.util.function.BiPredicate

// from: https://tomassetti.me/resolve-method-calls-using-java-symbol-solver/
class SpecificNodeIterator<T>(private val type: Class<T>, private val nodeHandler: (node: T) -> Boolean) {

    fun explore(node: Node) {
        if (type.isInstance(node)) {
            if (!nodeHandler(type.cast(node))) {
                return
            }
        }
        for (child in node.childNodes) {
            explore(child)
        }
    }
}

fun <T> Node.descendantsOfType(type: Class<T>, predicate: (T) -> Boolean = {true}) : List<T> {
    val descendants = mutableListOf<T>()
    SpecificNodeIterator(type, fun(node: T): Boolean {
            descendants.add(node)
            return true
    }).explore(this)
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

