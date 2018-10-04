package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.RelativeLayout
import kotlin.String

open class RelativeLayoutLayoutParamsFactory<TLayoutParams : RelativeLayout.LayoutParams, TAttributes : RelativeLayoutLayoutParamsAttributes> : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
}
