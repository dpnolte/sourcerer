package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class SlidingPaneLayoutFactory<TView : SlidingPaneLayout, TAttributes : SlidingPaneLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "slidingPaneLayout"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = SlidingPaneLayout(context)

    companion object {
        init {
            InflaterComponent.addFactory(SlidingPaneLayoutFactory<SlidingPaneLayout, SlidingPaneLayoutAttributes>())
        }

        inline operator fun <reified TView : SlidingPaneLayout, reified TAttributes : SlidingPaneLayoutAttributes> invoke() = SlidingPaneLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
