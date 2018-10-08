package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.car.widget.ColumnCardView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class ColumnCardViewFactory<TView : ColumnCardView, TAttributes : ColumnCardViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "columnCardView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = ColumnCardView(context)

    companion object {
        init {
            InflaterComponent.addFactory(ColumnCardViewFactory<ColumnCardView, ColumnCardViewAttributes>())
        }

        inline operator fun <reified TView : ColumnCardView, reified TAttributes : ColumnCardViewAttributes> invoke() = ColumnCardViewFactory(TView::class.java, TAttributes::class.java)
    }
}
