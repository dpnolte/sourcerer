package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlin.String

open class CollapsingToolbarLayoutLayoutParamsFactory<TLayoutParams : CollapsingToolbarLayout.LayoutParams, TAttributes : CollapsingToolbarLayoutLayoutParamsAttributes> : FrameLayoutLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = CollapsingToolbarLayout.LayoutParams(CollapsingToolbarLayout.LayoutParams.MATCH_PARENT, CollapsingToolbarLayout.LayoutParams.MATCH_PARENT)
}
