package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import java.lang.Class
import kotlin.String

open class FrameLayoutLayoutParamsFactory<TLayoutParams : FrameLayout.LayoutParams, TAttributes : FrameLayoutLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupMarginLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)

    override fun init(
        layoutParams: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(layoutParams, context, attributes)
        if (layoutParams is FrameLayout.LayoutParams) {
            layoutParams.apply {
                attributes.layout_gravity?.let {
                    val localLayoutGravity = it.value
                    if (localLayoutGravity != gravity) {
                        gravity = localLayoutGravity
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "android.widget.FrameLayout.LayoutParams"

        inline operator fun <reified TLayoutParams : FrameLayout.LayoutParams, reified TAttributes : FrameLayoutLayoutParamsAttributes> invoke() = FrameLayoutLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
