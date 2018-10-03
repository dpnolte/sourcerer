package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlin.String

open class LinearLayoutLayoutParamsFactory<TLayoutParams : LinearLayout.LayoutParams, TAttributes : LinearLayoutLayoutParamsAttributes> : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)

    override fun init(
        lp: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(lp, context, attributes)
    }
}
