package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.google.android.material.appbar.AppBarLayout
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.String

open class AppBarLayoutLayoutParamsFactory<TLayoutParams : AppBarLayout.LayoutParams, TAttributes : AppBarLayoutLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : LinearLayoutLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String =
            "com.google.android.material.appbar.AppBarLayout.LayoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = AppBarLayout.LayoutParams(AppBarLayout.LayoutParams.MATCH_PARENT, AppBarLayout.LayoutParams.MATCH_PARENT)

    override fun init(
        layoutParams: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(layoutParams, context, attributes)
        if (layoutParams is AppBarLayout.LayoutParams) {
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

    companion object {
        init {
            InflaterComponent.addFactory(AppBarLayoutLayoutParamsFactory<AppBarLayout.LayoutParams, AppBarLayoutLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : AppBarLayout.LayoutParams, reified TAttributes : AppBarLayoutLayoutParamsAttributes> invoke() = AppBarLayoutLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
