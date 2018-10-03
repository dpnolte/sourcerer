package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import com.google.android.material.chip.ChipGroup
import kotlin.String

open class ChipGroupLayoutParamsFactory<TLayoutParams : ChipGroup.LayoutParams, TAttributes : ChipGroupLayoutParamsAttributes> : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = ChipGroup.LayoutParams(ChipGroup.LayoutParams.MATCH_PARENT, ChipGroup.LayoutParams.MATCH_PARENT)

    override fun init(
        lp: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(lp, context, attributes)
    }
}
