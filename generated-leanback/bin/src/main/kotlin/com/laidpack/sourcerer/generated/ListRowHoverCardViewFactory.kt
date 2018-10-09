package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.leanback.widget.ListRowHoverCardView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class ListRowHoverCardViewFactory<TAttributes : ListRowHoverCardViewAttributes>(attributesType: Class<TAttributes>) : LinearLayoutFactory<ListRowHoverCardView, TAttributes>(ListRowHoverCardView::class.java, attributesType) {
    override val elementName: String = "listRowHoverCardView"

    override fun createInstance(context: Context): View = ListRowHoverCardView(context)

    companion object {
        init {
            InflaterComponent.addFactory(ListRowHoverCardViewFactory<ListRowHoverCardViewAttributes>())
        }

        inline operator fun <reified TAttributes : ListRowHoverCardViewAttributes> invoke() = ListRowHoverCardViewFactory(TAttributes::class.java)
    }
}
