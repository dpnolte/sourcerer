package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.core.content.ContextCompat
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import com.laidpack.sourcerer.service.api.init
import kotlin.String

open class ListViewFactory<TView : ListView, TAttributes : ListViewAttributes> : AdapterViewFactory<TView, TAttributes>() {
    override val elementName: String = "listView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = ListView(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as ListView
        view.init {
            if (attributes.divider.hasColor || attributes.divider.hasReference) {
                val immutableDivider = when {
                    attributes.divider.hasColor -> ColorDrawable(attributes.divider.color)
                    else -> ContextCompat.getDrawable(context, attributes.divider.reference) as Drawable
                }
                if (divider != immutableDivider) {
                    divider = immutableDivider
                }
            }
        }
    }
}
