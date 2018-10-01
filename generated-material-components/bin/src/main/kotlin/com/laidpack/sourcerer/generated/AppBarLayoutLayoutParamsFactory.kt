package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.google.android.material.appbar.AppBarLayout
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class AppBarLayoutLayoutParamsFactory<TLayoutParams : AppBarLayout.LayoutParams, TAttributes : AppBarLayoutLayoutParamsAttributes> : LinearLayoutLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = AppBarLayout.LayoutParams(AppBarLayout.LayoutParams.MATCH_PARENT, AppBarLayout.LayoutParams.MATCH_PARENT)

    override fun init(
        lp: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(lp, context, attributes)
        val layoutParams = lp as AppBarLayout.LayoutParams
        layoutParams.init {
            attributes.layout_scrollFlags?.let {
                if (scrollFlags != it.value) {
                    scrollFlags = it.value
                }
            }
            attributes.layout_scrollInterpolator?.let {
                val immutableLayoutScrollInterpolator = AnimationUtils.loadInterpolator(context, it)
                if (scrollInterpolator != immutableLayoutScrollInterpolator) {
                    scrollInterpolator = immutableLayoutScrollInterpolator
                }
            }
        }
    }
}
