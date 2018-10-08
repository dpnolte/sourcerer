package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.ExpandableListView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class ExpandableListViewFactory<TView : ExpandableListView, TAttributes : ExpandableListViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ListViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "expandableListView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = ExpandableListView(context)

    companion object {
        init {
            InflaterComponent.addFactory(ExpandableListViewFactory<ExpandableListView, ExpandableListViewAttributes>())
        }

        inline operator fun <reified TView : ExpandableListView, reified TAttributes : ExpandableListViewAttributes> invoke() = ExpandableListViewFactory(TView::class.java, TAttributes::class.java)
    }
}
