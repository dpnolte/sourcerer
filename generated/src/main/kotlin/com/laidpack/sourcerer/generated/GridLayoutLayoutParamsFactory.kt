package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.GridLayout
import kotlin.String

open class GridLayoutLayoutParamsFactory<TLayoutParams : GridLayout.LayoutParams, TAttributes : GridLayoutLayoutParamsAttributes> : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = GridLayout.LayoutParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))

    override fun init(
        lp: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(lp, context, attributes)
    }
}
