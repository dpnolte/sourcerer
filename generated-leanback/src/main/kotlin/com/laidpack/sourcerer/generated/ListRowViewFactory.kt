package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.leanback.widget.ListRowView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class ListRowViewFactory<TAttributes : ListRowViewAttributes>(attributesType: Class<TAttributes>) : LinearLayoutFactory<ListRowView, TAttributes>(ListRowView::class.java, attributesType) {
    override val elementName: String = "listRowView"

    override fun createInstance(context: Context): View = ListRowView(context)

    companion object {
        init {
            InflaterComponent.addFactory(ListRowViewFactory<ListRowViewAttributes>())
        }

        inline operator fun <reified TAttributes : ListRowViewAttributes> invoke() = ListRowViewFactory(TAttributes::class.java)
    }
}
