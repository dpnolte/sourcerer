package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import kotlin.String

open class LinearLayoutCompatLayoutParamsFactory<TLayoutParams : LinearLayoutCompat.LayoutParams, TAttributes : LinearLayoutCompatLayoutParamsAttributes> : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT)

    override fun init(
        lp: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(lp, context, attributes)
    }
}
