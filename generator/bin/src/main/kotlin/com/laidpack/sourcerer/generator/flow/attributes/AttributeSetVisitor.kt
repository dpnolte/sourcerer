package com.laidpack.sourcerer.generator.flow.attributes

import com.github.javaparser.ast.Node
import com.github.javaparser.ast.expr.NameExpr


class AttributeSetVisitor(private val handleNode: (node: Node, level: Int) -> Boolean) {
    fun explore(node: Node, level: Int = 1) {
        val name = when {
            node is NameExpr -> node.nameAsString
            else -> {
                val nodeAsString = node.toString()
                val substring = if (nodeAsString.contains("\n")) {
                    nodeAsString.substring(0, nodeAsString.indexOfFirst { c -> c == '\n'}) + "..."
                } else nodeAsString
                substring
            }
        }
        val result = handleNode(node, level)
        //println("-".repeat(level) + " $name -> $result")
        if (result) {
            for (child in node.childNodes) {
                explore(child, level + 1)
            }
        }
    }
}