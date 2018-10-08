package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.slice.widget.SliceView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class SliceViewFactory<TView : SliceView, TAttributes : SliceViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "sliceView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = SliceView(context)

    companion object {
        init {
            InflaterComponent.addFactory(SliceViewFactory<SliceView, SliceViewAttributes>())
        }

        inline operator fun <reified TView : SliceView, reified TAttributes : SliceViewAttributes> invoke() = SliceViewFactory(TView::class.java, TAttributes::class.java)
    }
}
