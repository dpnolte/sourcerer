package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.TabWidget
import androidx.core.content.ContextCompat
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class TabWidgetFactory<TView : TabWidget, TAttributes : TabWidgetAttributes> : LinearLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "tabWidget"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = TabWidget(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as TabWidget
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
                        setLeftStripDrawable(immutableTabStripLeft)
                    }
                }
                attributes.tabStripRight?.let {
                    val immutableTabStripRight = ContextCompat.getDrawable(context, it) as Drawable
                    if (rightStripDrawable != immutableTabStripRight) {
                        setRightStripDrawable(immutableTabStripRight)
                    }
                }
            }
        }
    }
}
