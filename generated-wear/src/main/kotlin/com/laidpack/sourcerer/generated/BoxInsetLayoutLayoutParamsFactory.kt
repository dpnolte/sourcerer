package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import androidx.wear.widget.BoxInsetLayout
import kotlin.String

open class BoxInsetLayoutLayoutParamsFactory<TLayoutParams : BoxInsetLayout.LayoutParams, TAttributes : BoxInsetLayoutLayoutParamsAttributes> : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = BoxInsetLayout.LayoutParams(BoxInsetLayout.LayoutParams.MATCH_PARENT, BoxInsetLayout.LayoutParams.MATCH_PARENT)
}
