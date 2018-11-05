package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import com.laidpack.generator.api.LayoutParamsElement
import java.lang.Class
import kotlin.String

@LayoutParamsElement(
        elementType = LinearLayoutLayoutParamsFactory.elementType,
        attributesClazz = LinearLayoutLayoutParamsAttributes::class
)
open class LinearLayoutLayoutParamsFactory<TLayoutParams : LinearLayout.LayoutParams, TAttributes : LinearLayoutLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupMarginLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)

    override fun init(
        layoutParams: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(layoutParams, context, attributes)
        if (layoutParams is LinearLayout.LayoutParams) {
            layoutParams.apply {
                attributes.layout_weight?.let {
                    if (it != weight) {
                        weight = it
                    }
                }
                if (attributes.layout_gravity.hasInteger || attributes.layout_gravity.hasFlags) {
                    val localLayoutGravity = when {
                        attributes.layout_gravity.hasInteger -> attributes.layout_gravity.integer
                        else -> attributes.layout_gravity.flags
                    }
                    if (localLayoutGravity != gravity) {
                        gravity = localLayoutGravity
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "android.widget.LinearLayout.LayoutParams"

        inline operator fun <reified TLayoutParams : LinearLayout.LayoutParams, reified TAttributes : LinearLayoutLayoutParamsAttributes> invoke() = LinearLayoutLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
