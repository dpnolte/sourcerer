package com.laidpack.sourcerer.generated.slidingpanelayout

import android.content.Context
import android.view.ViewGroup
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.laidpack.sourcerer.generated.ViewGroupMarginLayoutParamsFactory
import java.lang.Class
import kotlin.String

open class SlidingPaneLayoutLayoutParamsFactory<TLayoutParams : SlidingPaneLayout.LayoutParams, TAttributes : SlidingPaneLayoutLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupMarginLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = SlidingPaneLayout.LayoutParams(SlidingPaneLayout.LayoutParams.MATCH_PARENT, SlidingPaneLayout.LayoutParams.MATCH_PARENT)

    override fun init(
        layoutParams: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(layoutParams, context, attributes)
        if (layoutParams is SlidingPaneLayout.LayoutParams) {
            layoutParams.apply {
                attributes.layout_weight?.let {
                    if (it != weight) {
                        weight = it
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String =
                "androidx.slidingpanelayout.widget.SlidingPaneLayout.LayoutParams"

        inline operator fun <reified TLayoutParams : SlidingPaneLayout.LayoutParams, reified TAttributes : SlidingPaneLayoutLayoutParamsAttributes> invoke() = SlidingPaneLayoutLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
