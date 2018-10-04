package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import kotlin.String

open class FrameLayoutLayoutParamsFactory<TLayoutParams : FrameLayout.LayoutParams, TAttributes : FrameLayoutLayoutParamsAttributes> : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
}
