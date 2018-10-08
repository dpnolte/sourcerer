package com.laidpack.sourcerer.generator.flow

import com.github.javaparser.ast.Node
import com.github.javaparser.ast.expr.NameExpr
import com.laidpack.sourcerer.generator.resources.SourcererEnvironment


class FlowVisitor(private val handleNode: (node: Node, level: Int) -> BaseFlowInterpreter.NodeIterationResult) {
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
        if(SourcererEnvironment.printSourceTreeVisitOutput) {
            println("-".repeat(level) + " $name -> canContinue?: ${result.canContinue}, '${node::class.simpleName}' handled?: ${result.isHandled}")
        }
        if (result.canContinue) {
            for (child in node.childNodes) {
                explore(child, level + 1)
            }
        }
    }
}