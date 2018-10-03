package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import androidx.leanback.widget.BaseCardView
import kotlin.String

open class BaseCardViewLayoutParamsFactory<TLayoutParams : BaseCardView.LayoutParams, TAttributes : BaseCardViewLayoutParamsAttributes> : FrameLayoutLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = BaseCardView.LayoutParams(BaseCardView.LayoutParams.MATCH_PARENT, BaseCardView.LayoutParams.MATCH_PARENT)

    override fun init(
        lp: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(lp, context, attributes)
    }
}
