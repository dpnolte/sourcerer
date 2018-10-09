package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.leanback.widget.HorizontalGridView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class HorizontalGridViewFactory<TView : HorizontalGridView, TAttributes : HorizontalGridViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "horizontalGridView"

    override fun createInstance(context: Context): View = HorizontalGridView(context)

    companion object {
        init {
            InflaterComponent.addFactory(HorizontalGridViewFactory<HorizontalGridView, HorizontalGridViewAttributes>())
        }

        inline operator fun <reified TView : HorizontalGridView, reified TAttributes : HorizontalGridViewAttributes> invoke() = HorizontalGridViewFactory(TView::class.java, TAttributes::class.java)
    }
}
