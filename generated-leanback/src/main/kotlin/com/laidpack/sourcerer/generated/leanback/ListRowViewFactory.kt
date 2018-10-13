package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.view.View
import androidx.leanback.widget.ListRowView
import com.laidpack.sourcerer.generated.LinearLayoutFactory
import java.lang.Class
import kotlin.String

open class ListRowViewFactory<TAttributes : ListRowViewAttributes>(attributesType: Class<TAttributes>) : LinearLayoutFactory<ListRowView, TAttributes>(ListRowView::class.java, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = ListRowView(context)

    companion object {
        const val elementType: String = "listRowView"

        inline operator fun <reified TAttributes : ListRowViewAttributes> invoke() = ListRowViewFactory(TAttributes::class.java)
    }
}
