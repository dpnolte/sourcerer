package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.TabWidget
import androidx.core.content.ContextCompat
import java.lang.Class
import kotlin.String

open class TabWidgetFactory<TView : TabWidget, TAttributes : TabWidgetAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = TabWidget(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is TabWidget) {
            view.apply {
                attributes.tabStripEnabled?.let {
                    if (isStripEnabled != it) {
                        isStripEnabled = it
                    }
                }
                if (Build.VERSION.SDK_INT >= 16) {
                    attributes.divider?.let {
                        val immutableDivider = ContextCompat.getDrawable(context, it) as Drawable
                        if (dividerDrawable != immutableDivider) {
                            setDividerDrawable(immutableDivider)
                        }
                    }
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    attributes.tabStripLeft?.let {
                        val immutableTabStripLeft = ContextCompat.getDrawable(context, it) as Drawable
                        if (leftStripDrawable != immutableTabStripLeft) {
                            leftStripDrawable = immutableTabStripLeft
                        }
                    }
                    attributes.tabStripRight?.let {
                        val immutableTabStripRight = ContextCompat.getDrawable(context, it) as Drawable
                        if (rightStripDrawable != immutableTabStripRight) {
                            rightStripDrawable = immutableTabStripRight
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "tabWidget"

        inline operator fun <reified TView : TabWidget, reified TAttributes : TabWidgetAttributes> invoke() = TabWidgetFactory(TView::class.java, TAttributes::class.java)
    }
}
