package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.leanback.widget.ListRowHoverCardView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class ListRowHoverCardViewFactory<TView : ListRowHoverCardView, TAttributes : ListRowHoverCardViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "listRowHoverCardView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = ListRowHoverCardView(context)

    companion object {
        init {
            InflaterComponent.addFactory(ListRowHoverCardViewFactory<ListRowHoverCardView, ListRowHoverCardViewAttributes>())
        }

        inline operator fun <reified TView : ListRowHoverCardView, reified TAttributes : ListRowHoverCardViewAttributes> invoke() = ListRowHoverCardViewFactory(TView::class.java, TAttributes::class.java)
    }
}
