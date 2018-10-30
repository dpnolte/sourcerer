package com.laidpack.sourcerer.generator.resources

import com.laidpack.sourcerer.generator.SourcererResult
import com.laidpack.sourcerer.generator.Store
import com.laidpack.sourcerer.generator.index.ClassInfo
import com.laidpack.sourcerer.generator.index.XdDeclaredType
import com.laidpack.sourcerer.generator.index.XdFile
import com.laidpack.sourcerer.generator.resources.widgets.AndroidWidgetConnoisseur
import com.laidpack.sourcerer.generator.resources.widgets.MaterialDesignWidgetConnoisseur
import com.laidpack.sourcerer.generator.resources.widgets.SupportWidgetConnoisseur
import com.laidpack.sourcerer.generator.resources.widgets.WidgetConnoisseur
import com.squareup.kotlinpoet.ClassName
import kotlinx.dnq.query.*
import java.lang.IllegalArgumentException
import java.nio.file.Path

class WidgetRegistry(
        private val env: SourcererEnvironment,
        parsersList: List<WidgetConnoisseur>
): Iterable<Widget> {

    private val sortedParsers = parsersList.sortedBy { it.priority }
    val paths = sortedParsers
            .map { it.paths }
            .flatten()
    private val parsers = paths.associate { path ->
        Pair(path, sortedParsers.first { it.paths.contains(path) })
    }
    private val widgets by lazy {
        Store.transactional {
            Widget.all().toList()
        }
    }

    override operator fun iterator(): Iterator<Widget> {
        return widgets.iterator()
    }

    operator fun get(path: Path): WidgetConnoisseur {
        return parsers[path] as WidgetConnoisseur
    }
    operator fun get(widget: Widget): WidgetConnoisseur {
        val path = Store.transactional {
            widget.file.scanPath
        }
        return parsers[path] as WidgetConnoisseur
    }
    fun getPackageName(classInfo: ClassInfo): String {
        val widget = Store.transactional {
            Widget.query(
                    (Widget::viewClass eq classInfo.xdDeclaredType)
                            or
                            (Widget::layoutParamClasses contains classInfo.xdDeclaredType)
            ).firstOrNull() ?: throw  IllegalArgumentException("No widget for ${classInfo.xdDeclaredType.targetClassName} found")
        }
        return getPackageName(widget)
    }

    fun getWidget(sourcererResult: SourcererResult): Widget {
        return Store.transactional {
            Widget.query(
                    (Widget::viewClass eq sourcererResult.xdDeclaredType)
                            or
                            (Widget::layoutParamClasses contains sourcererResult.xdDeclaredType)
            ).firstOrNull() ?: throw  IllegalArgumentException("No widget for sourcerer result '${sourcererResult.targetClassName}' found")
        }
    }
    fun getModuleName(widget: Widget): String {
        val parser = this[widget]
        return parser.getModuleName(widget)
    }
    fun getPackageName(sourcererResult: SourcererResult): String {
        val widget = Store.transactional {
            Widget.query(
                    (Widget::viewClass eq sourcererResult.xdDeclaredType)
                            or
                            (Widget::layoutParamClasses contains sourcererResult.xdDeclaredType)
            )
        }.firstOrNull() ?: throw  IllegalArgumentException("No widget for sourcerer result found")
        return getPackageName(widget)
    }
    fun getPackageName(widget: Widget): String {
        val parser = this[widget]
        val moduleName = parser.getModuleName(widget)
        return "${SourcererEnvironment.rootPackageName}.${moduleName.replace("-", ".")}"
    }

    fun findOrCreate (xdFile: XdFile): Widget {
        return Store.transactional {
            val widget = Widget.query(
                    Widget::file eq xdFile
            ).firstOrNull()
            if (widget != null) {
                widget
            } else {
                val parser = parsers[xdFile.scanPath] as WidgetConnoisseur
                val attributesXmlPathString = parser.getAttributesFileUrl(xdFile.filePath, env).toString()
                Widget.new {
                    this.source = parser.sourceProvider()
                    this.file = xdFile
                    this.attributesXmlUrlAsString = attributesXmlPathString
                }
            }
        }
    }


    companion object {
        val defaultWidgetConnoisseurs = listOf(
                AndroidWidgetConnoisseur(),
                MaterialDesignWidgetConnoisseur(),
                SupportWidgetConnoisseur()
        )
        fun create(
                env: SourcererEnvironment,
                widgetConnoisseurs: List<WidgetConnoisseur> = defaultWidgetConnoisseurs
        ): WidgetRegistry {
            widgetConnoisseurs.forEach {
                it.setScanPaths(env)
            }
            return WidgetRegistry(env, widgetConnoisseurs)
        }

        fun getWidgetViewClassName(classInfo: ClassInfo): ClassName {
            return Store.transactional {
                val viewClass = classInfo.xdDeclaredType.widget?.viewClass as XdDeclaredType
                viewClass.targetClassName
            }
        }
    }
}