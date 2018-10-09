package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.car.widget.ColumnCardView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class ColumnCardViewFactory<TAttributes : ColumnCardViewAttributes>(attributesType: Class<TAttributes>) : FrameLayoutFactory<ColumnCardView, TAttributes>(ColumnCardView::class.java, attributesType) {
    override val elementName: String = "columnCardView"

    override fun createInstance(context: Context): View = ColumnCardView(context)

    companion object {
        init {
            InflaterComponent.addFactory(ColumnCardViewFactory<ColumnCardViewAttributes>())
        }

        inline operator fun <reified TAttributes : ColumnCardViewAttributes> invoke() = ColumnCardViewFactory(TAttributes::class.java)
    }
}
