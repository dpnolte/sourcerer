package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.leanback.widget.RowHeaderView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class RowHeaderViewFactory<TAttributes : RowHeaderViewAttributes>(attributesType: Class<TAttributes>) : TextViewFactory<RowHeaderView, TAttributes>(RowHeaderView::class.java, attributesType) {
    override val elementName: String = "rowHeaderView"

    override fun createInstance(context: Context): View = RowHeaderView(context)

    companion object {
        init {
            InflaterComponent.addFactory(RowHeaderViewFactory<RowHeaderViewAttributes>())
        }

        inline operator fun <reified TAttributes : RowHeaderViewAttributes> invoke() = RowHeaderViewFactory(TAttributes::class.java)
    }
}
