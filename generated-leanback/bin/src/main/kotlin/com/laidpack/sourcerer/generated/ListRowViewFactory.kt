package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.leanback.widget.ListRowView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class ListRowViewFactory<TView : ListRowView, TAttributes : ListRowViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "listRowView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = ListRowView(context)

    companion object {
        init {
            InflaterComponent.addFactory(ListRowViewFactory<ListRowView, ListRowViewAttributes>())
        }

        inline operator fun <reified TView : ListRowView, reified TAttributes : ListRowViewAttributes> invoke() = ListRowViewFactory(TView::class.java, TAttributes::class.java)
    }
}
