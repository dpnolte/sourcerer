package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import kotlin.String

open class SlidingPaneLayoutLayoutParamsFactory<TLayoutParams : SlidingPaneLayout.LayoutParams, TAttributes : SlidingPaneLayoutLayoutParamsAttributes> : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = SlidingPaneLayout.LayoutParams(SlidingPaneLayout.LayoutParams.MATCH_PARENT, SlidingPaneLayout.LayoutParams.MATCH_PARENT)

    override fun init(
        lp: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(lp, context, attributes)
    }
}
