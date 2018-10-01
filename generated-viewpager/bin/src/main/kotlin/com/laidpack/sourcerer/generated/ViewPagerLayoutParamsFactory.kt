package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class ViewPagerLayoutParamsFactory<TLayoutParams : ViewPager.LayoutParams, TAttributes : ViewPagerLayoutParamsAttributes> : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = ViewPager.LayoutParams()

    override fun init(
        lp: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
    }
}
