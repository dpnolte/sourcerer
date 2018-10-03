package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.RadioGroup
import kotlin.String

open class RadioGroupLayoutParamsFactory<TLayoutParams : RadioGroup.LayoutParams, TAttributes : RadioGroupLayoutParamsAttributes> : LinearLayoutLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.MATCH_PARENT)

    override fun init(
        lp: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(lp, context, attributes)
    }
}
