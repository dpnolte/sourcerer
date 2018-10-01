package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class CollapsingToolbarLayoutLayoutParamsFactory<TLayoutParams : CollapsingToolbarLayout.LayoutParams, TAttributes : CollapsingToolbarLayoutLayoutParamsAttributes> : FrameLayoutLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = CollapsingToolbarLayout.LayoutParams(CollapsingToolbarLayout.LayoutParams.MATCH_PARENT, CollapsingToolbarLayout.LayoutParams.MATCH_PARENT)

    override fun init(
        lp: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
    }
}
