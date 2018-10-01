package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.AbsoluteLayout
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class AbsoluteLayoutLayoutParamsFactory<TLayoutParams : AbsoluteLayout.LayoutParams, TAttributes : AbsoluteLayoutLayoutParamsAttributes> : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = AbsoluteLayout.LayoutParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))

    override fun init(
        lp: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
    }
}
