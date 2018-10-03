package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import com.laidpack.sourcerer.service.api.init
import kotlin.String

open class ViewGroupMarginLayoutParamsFactory<TLayoutParams : ViewGroup.MarginLayoutParams, TAttributes : ViewGroupMarginLayoutParamsAttributes> : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "marginLayoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.MATCH_PARENT, ViewGroup.MarginLayoutParams.MATCH_PARENT)

    override fun init(
        lp: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(lp, context, attributes)
        val layoutParams = lp as ViewGroup.MarginLayoutParams
        layoutParams.init {
            if (attributes.layout_marginLeft != null || attributes.layout_marginTop != null || attributes.layout_marginRight != null || attributes.layout_marginBottom != null) {
                val immutableLayoutMarginLeft = attributes.layout_marginLeft ?: leftMargin
                val immutableLayoutMarginTop = attributes.layout_marginTop ?: topMargin
                val immutableLayoutMarginRight = attributes.layout_marginRight ?: rightMargin
                val immutableLayoutMarginBottom = attributes.layout_marginBottom ?: bottomMargin
                if (leftMargin != attributes.layout_marginLeft || topMargin != attributes.layout_marginTop || rightMargin != attributes.layout_marginRight || bottomMargin != attributes.layout_marginBottom) {
                    setMargins(immutableLayoutMarginLeft, immutableLayoutMarginTop, immutableLayoutMarginRight, immutableLayoutMarginBottom)
                }
            }
            attributes.layout_marginStart?.let {
                if (marginStart != it) {
                    marginStart = it
                }
            }
            attributes.layout_marginEnd?.let {
                if (marginEnd != it) {
                    marginEnd = it
                }
            }
        }
    }
}
