package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ListView
import androidx.core.content.ContextCompat
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.Int
import kotlin.String

open class ListViewFactory<TView : ListView, TAttributes : ListViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AbsListViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "listView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = -1

    override fun createInstance(context: Context): View = ListView(context)

    override fun init(
        view: TView,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
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
            attributes.dividerHeight?.let {
                if (dividerHeight != it) {
                    dividerHeight = it
                }
            }
            if (attributes.overScrollHeader.hasColor || attributes.overScrollHeader.hasReference) {
                val immutableOverScrollHeader = when {
                    attributes.overScrollHeader.hasColor -> ColorDrawable(attributes.overScrollHeader.color)
                    else -> ContextCompat.getDrawable(context, attributes.overScrollHeader.reference) as Drawable
                }
                if (overscrollHeader != immutableOverScrollHeader) {
                    overscrollHeader = immutableOverScrollHeader
                }
            }
            if (attributes.overScrollFooter.hasColor || attributes.overScrollFooter.hasReference) {
                val immutableOverScrollFooter = when {
                    attributes.overScrollFooter.hasColor -> ColorDrawable(attributes.overScrollFooter.color)
                    else -> ContextCompat.getDrawable(context, attributes.overScrollFooter.reference) as Drawable
                }
                if (overscrollFooter != immutableOverScrollFooter) {
                    overscrollFooter = immutableOverScrollFooter
                }
            }
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(ListViewFactory<ListView, ListViewAttributes>())
        }

        inline operator fun <reified TView : ListView, reified TAttributes : ListViewAttributes> invoke() = ListViewFactory(TView::class.java, TAttributes::class.java)
    }
}
