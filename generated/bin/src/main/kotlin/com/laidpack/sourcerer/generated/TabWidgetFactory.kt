package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.TabWidget
import androidx.core.content.ContextCompat
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.String

open class TabWidgetFactory<TView : TabWidget, TAttributes : TabWidgetAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "tabWidget"

    override fun createInstance(context: Context): View = TabWidget(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is TabWidget) {
            view.init {
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
        init {
            InflaterComponent.addFactory(TabWidgetFactory<TabWidget, TabWidgetAttributes>())
        }

        inline operator fun <reified TView : TabWidget, reified TAttributes : TabWidgetAttributes> invoke() = TabWidgetFactory(TView::class.java, TAttributes::class.java)
    }
}
