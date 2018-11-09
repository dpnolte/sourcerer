package com.laidpack.sourcerer.generator.generators.templates

class BehaviorUtilsTemplateProvider : CodeTemplateProvider {
    override val templateName: String = Companion.templateName
    override val fileName: String = "$templateName.kt"
    override fun getTemplate(packageName: String): String {
        return """
package $packageName

import android.content.Context
import android.util.AttributeSet
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
import java.lang.reflect.Constructor
import java.util.HashMap

object BehaviorUtils {
    private val CONSTRUCTOR_PARAMS = arrayOf(Context::class.java, AttributeSet::class.java)

    fun createFromName(name: String, context: Context): Behavior<*> {
        val fullName: String = when {
            name == "@string/appbar_scrolling_view_behavior" -> scrollingViewBehaviorStringValue
            name.startsWith(".") -> // Relative to the app package. Prepend the app package name.
                context.packageName + name
            name.indexOf('.') >= 0 -> // Fully qualified package name.
                name
            else -> "androidx.coordinatorlayout.widget.${'$'}name"
        }

        try {
            val clazz = context.classLoader
                    .loadClass(fullName) as Class<*>
            val c = clazz.getConstructor(*CONSTRUCTOR_PARAMS)
            c.isAccessible = true
            return c.newInstance(context, null) as Behavior<*>
        } catch (e: Exception) {
            throw RuntimeException("Could not inflate Behavior subclass ${'$'}fullName", e)
        }
    }

    private const val scrollingViewBehaviorStringValue = "com.google.android.material.appbar.AppBarLayout\${'$'}ScrollingViewBehavior"
}
""".trimIndent()
    }
    companion object {
        const val templateName = "BehaviorUtils"
    }
}
