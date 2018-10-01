package com.laidpack.sourcerer.generator

import com.github.javaparser.ast.Node
import com.github.javaparser.ast.body.*
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

class SimpleNodeIterator(private val nodeHandler: (node: Node) -> Boolean) {
    fun explore(node: Node) {
        if (!nodeHandler(node)) {
            return
        }
        for (child in node.childNodes) {
            explore(child)
        }
    }
}

// this is a method extension: we had this method to the existing class "Node"
fun <T> Node.descendantsOfType(type: Class<T>) : List<T> {
    val descendants = mutableListOf<T>()
    SpecificNodeIterator(type, fun(node: T): Boolean {
            descendants.add(node)
            return true
    }).explore(this)
    return descendants
}
// this is a method extension: we had this method to the existing class "Node"
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


fun ClassOrInterfaceDeclaration.getBodyDeclarationsOfThisClass() : List<BodyDeclaration<*>> {
    val declarations = mutableListOf<BodyDeclaration<*>>()
    SimpleNodeIterator(fun (node: Node): Boolean {
        if (node is MethodDeclaration || node is ConstructorDeclaration || (node is FieldDeclaration && node.parentNode.get() is ClassOrInterfaceDeclaration)) {
            declarations.add(node as BodyDeclaration<*>)
        }
        return node !is ClassOrInterfaceDeclaration || node == this
    }).explore(this)
    return declarations
}

