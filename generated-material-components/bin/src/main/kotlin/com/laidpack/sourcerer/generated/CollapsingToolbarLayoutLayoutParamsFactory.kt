package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.String

open class CollapsingToolbarLayoutLayoutParamsFactory<TLayoutParams : CollapsingToolbarLayout.LayoutParams, TAttributes : CollapsingToolbarLayoutLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : FrameLayoutLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String =
            "com.google.android.material.appbar.CollapsingToolbarLayout.LayoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = CollapsingToolbarLayout.LayoutParams(CollapsingToolbarLayout.LayoutParams.MATCH_PARENT, CollapsingToolbarLayout.LayoutParams.MATCH_PARENT)

    override fun init(
        layoutParams: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(layoutParams, context, attributes)
        if (layoutParams is CollapsingToolbarLayout.LayoutParams) {
            layoutParams.init {
                attributes.layout_collapseParallaxMultiplier?.let {
                    if (parallaxMultiplier != it) {
                        parallaxMultiplier = it
                    }
                }
            }
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(CollapsingToolbarLayoutLayoutParamsFactory<CollapsingToolbarLayout.LayoutParams, CollapsingToolbarLayoutLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : CollapsingToolbarLayout.LayoutParams, reified TAttributes : CollapsingToolbarLayoutLayoutParamsAttributes> invoke() = CollapsingToolbarLayoutLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
