package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.wear.widget.BoxInsetLayout
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class BoxInsetLayoutFactory<TView : BoxInsetLayout, TAttributes : BoxInsetLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "boxInsetLayout"

    override fun createInstance(context: Context): View = BoxInsetLayout(context)

    companion object {
        init {
            InflaterComponent.addFactory(BoxInsetLayoutFactory<BoxInsetLayout, BoxInsetLayoutAttributes>())
        }

        inline operator fun <reified TView : BoxInsetLayout, reified TAttributes : BoxInsetLayoutAttributes> invoke() = BoxInsetLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
