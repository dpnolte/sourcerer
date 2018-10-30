package com.laidpack.sourcerer.generator.flow

import com.github.ajalt.mordant.TermColors
import com.github.javaparser.ast.Node
import com.github.javaparser.ast.expr.NameExpr
import com.laidpack.sourcerer.generator.resources.SourcererEnvironment


class FlowVisitor(
        private val handleNode: (node: Node, level: Int) -> BaseFlowInterpreter.NodeIterationResult,
        private val isConditionalToAttr: () -> Boolean = {false}
) {

    fun explore(node: Node, level: Int = 1) {

        val result = handleNode(node, level)
        printTrace(node, level, result)
        if (result.canContinue) {
            for (child in node.childNodes) {
                explore(child, level + 1)
            }
        }
    }

    private fun printTrace(
            node: Node,
            level: Int,
            result: BaseFlowInterpreter.NodeIterationResult
    ) {
        if(SourcererEnvironment.printFlowInterpreterTrace) {
            val name = when (node) {
                is NameExpr -> node.nameAsString
                else -> {
                    val nodeAsString = node.toString()
                    val substring = if (nodeAsString.contains("\n")) {
                        nodeAsString.substring(0, nodeAsString.indexOfFirst { c -> c == '\n'}) + "..."
                    } else nodeAsString
                    substring
                }
            }
            val shortName = if(name.length > 80) "${name.substring(0, 60)}..." else name // 63
            val levelIndicator = if (level < 10) "0$level" else level.toString() // 65

            val nodeInfo = levelIndicator + s.repeat(level) + "'${node::class.simpleName}' ${c("-> '$shortName'")}"
            val padding = " ".repeat( 122 - nodeInfo.length )
            val handled = if (result.isHandled) "true  " else "false "
            val canContinue = if (result.canContinue) "true  " else "false "
            val columns = "$padding|handled?: $handled|continue?: $canContinue|conditional to attr?: ${isConditionalToAttr()} |${result.handlerNames.joinToString()}"
            val coloredColumns = if (result.isHandled) t.rgb(0, 100, 0)(columns) else c(columns)

            println("$nodeInfo$coloredColumns")
        }
    }

    private val t = TermColors()
    private val c: (String) -> String = {msg ->
        if (isConditionalToAttr()) {
            t.green(msg)
        } else {
            t.gray(msg)
        }
    }
    private val s: String
        get() {
            return if (isConditionalToAttr()) {
                t.green("█")
            } else {
                t.gray("█")
            }
        }

}