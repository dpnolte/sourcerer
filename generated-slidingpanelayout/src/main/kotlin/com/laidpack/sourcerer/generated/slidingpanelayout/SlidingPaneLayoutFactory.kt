package com.laidpack.sourcerer.generated.slidingpanelayout

import android.content.Context
import android.view.View
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.ViewGroupFactory
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = SlidingPaneLayoutFactory.elementType,
        attributesClazz = SlidingPaneLayoutAttributes::class,
        layoutParamAttributesClazz = SlidingPaneLayoutLayoutParamsAttributes::class
)
open class SlidingPaneLayoutFactory<TView : SlidingPaneLayout, TAttributes : SlidingPaneLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = SlidingPaneLayout(context)

    companion object {
        const val elementType: String = "slidingPaneLayout"

        inline operator fun <reified TView : SlidingPaneLayout, reified TAttributes : SlidingPaneLayoutAttributes> invoke() = SlidingPaneLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
