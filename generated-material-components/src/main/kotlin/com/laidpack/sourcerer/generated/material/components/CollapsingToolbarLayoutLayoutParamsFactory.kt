package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.view.ViewGroup
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.laidpack.generator.api.LayoutParamsElement
import com.laidpack.sourcerer.generated.FrameLayoutLayoutParamsFactory
import java.lang.Class
import kotlin.String

@LayoutParamsElement(
        elementType = CollapsingToolbarLayoutLayoutParamsFactory.elementType,
        attributesClazz = CollapsingToolbarLayoutLayoutParamsAttributes::class
)
open class CollapsingToolbarLayoutLayoutParamsFactory<TLayoutParams : CollapsingToolbarLayout.LayoutParams, TAttributes : CollapsingToolbarLayoutLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : FrameLayoutLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = CollapsingToolbarLayout.LayoutParams(CollapsingToolbarLayout.LayoutParams.MATCH_PARENT, CollapsingToolbarLayout.LayoutParams.MATCH_PARENT)

    override fun init(
        layoutParams: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(layoutParams, context, attributes)
        if (layoutParams is CollapsingToolbarLayout.LayoutParams) {
            layoutParams.apply {
                attributes.layout_collapseMode?.let {
                    if (collapseMode != it.value) {
                        collapseMode = it.value
                    }
                }
                attributes.layout_collapseParallaxMultiplier?.let {
                    if (parallaxMultiplier != it) {
                        parallaxMultiplier = it
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String =
                "com.google.android.material.appbar.CollapsingToolbarLayout.LayoutParams"

        inline operator fun <reified TLayoutParams : CollapsingToolbarLayout.LayoutParams, reified TAttributes : CollapsingToolbarLayoutLayoutParamsAttributes> invoke() = CollapsingToolbarLayoutLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
