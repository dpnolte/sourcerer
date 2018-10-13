package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.ExpandableListView
import java.lang.Class
import kotlin.String

open class ExpandableListViewFactory<TView : ExpandableListView, TAttributes : ExpandableListViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ListViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = ExpandableListView(context)

    companion object {
        const val elementType: String = "expandableListView"

        inline operator fun <reified TView : ExpandableListView, reified TAttributes : ExpandableListViewAttributes> invoke() = ExpandableListViewFactory(TView::class.java, TAttributes::class.java)
    }
}
