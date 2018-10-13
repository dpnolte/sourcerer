package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.view.View
import androidx.leanback.widget.ListRowHoverCardView
import com.laidpack.sourcerer.generated.LinearLayoutFactory
import java.lang.Class
import kotlin.String

open class ListRowHoverCardViewFactory<TAttributes : ListRowHoverCardViewAttributes>(attributesType: Class<TAttributes>) : LinearLayoutFactory<ListRowHoverCardView, TAttributes>(ListRowHoverCardView::class.java, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = ListRowHoverCardView(context)

    companion object {
        const val elementType: String = "listRowHoverCardView"

        inline operator fun <reified TAttributes : ListRowHoverCardViewAttributes> invoke() = ListRowHoverCardViewFactory(TAttributes::class.java)
    }
}
