package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.ActionMenuView
import kotlin.String

open class ActionMenuViewLayoutParamsFactory<TLayoutParams : ActionMenuView.LayoutParams, TAttributes : ActionMenuViewLayoutParamsAttributes> : LinearLayoutLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = ActionMenuView.LayoutParams(ActionMenuView.LayoutParams.MATCH_PARENT, ActionMenuView.LayoutParams.MATCH_PARENT)

    override fun init(
        lp: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(lp, context, attributes)
    }
}
