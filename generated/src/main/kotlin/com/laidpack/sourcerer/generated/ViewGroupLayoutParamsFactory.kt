package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import com.laidpack.sourcerer.service.api.BaseLayoutParamsFactory
import kotlin.String

open class ViewGroupLayoutParamsFactory<TLayoutParams : ViewGroup.LayoutParams, TAttributes : ViewGroupLayoutParamsAttributes> : BaseLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

    override fun init(
        lp: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
    }
}
