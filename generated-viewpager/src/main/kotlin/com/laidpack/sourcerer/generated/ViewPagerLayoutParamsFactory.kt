package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import kotlin.String

open class ViewPagerLayoutParamsFactory<TLayoutParams : ViewPager.LayoutParams, TAttributes : ViewPagerLayoutParamsAttributes> : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = ViewPager.LayoutParams()

    override fun init(
        lp: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(lp, context, attributes)
    }
}
