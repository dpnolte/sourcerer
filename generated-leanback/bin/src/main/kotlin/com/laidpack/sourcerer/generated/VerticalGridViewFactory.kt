package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.leanback.widget.VerticalGridView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class VerticalGridViewFactory<TView : VerticalGridView, TAttributes : VerticalGridViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "verticalGridView"

    override fun createInstance(context: Context): View = VerticalGridView(context)

    companion object {
        init {
            InflaterComponent.addFactory(VerticalGridViewFactory<VerticalGridView, VerticalGridViewAttributes>())
        }

        inline operator fun <reified TView : VerticalGridView, reified TAttributes : VerticalGridViewAttributes> invoke() = VerticalGridViewFactory(TView::class.java, TAttributes::class.java)
    }
}
