package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ExpandableListView
import androidx.core.content.ContextCompat
import java.lang.Class
import kotlin.String

open class ExpandableListViewFactory<TView : ExpandableListView, TAttributes : ExpandableListViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ListViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = ExpandableListView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is ExpandableListView) {
            view.apply {
                attributes.groupIndicator?.let {
                    val localGroupIndicator = ContextCompat.getDrawable(context, it) as Drawable
                    setGroupIndicator(localGroupIndicator)
                }
                attributes.childIndicator?.let {
                    val localChildIndicator = ContextCompat.getDrawable(context, it) as Drawable
                    setChildIndicator(localChildIndicator)
                }
                if (attributes.childDivider.hasColor || attributes.childDivider.hasReference) {
                    val localChildDivider = when {
                        attributes.childDivider.hasColor -> ColorDrawable(attributes.childDivider.color)
                        else -> ContextCompat.getDrawable(context, attributes.childDivider.reference) as Drawable
                    }
                    setChildDivider(localChildDivider)
                }
            }
        }
    }

    companion object {
        const val elementType: String = "expandableListView"

        inline operator fun <reified TView : ExpandableListView, reified TAttributes : ExpandableListViewAttributes> invoke() = ExpandableListViewFactory(TView::class.java, TAttributes::class.java)
    }
}
