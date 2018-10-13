package com.laidpack.sourcerer.generated.wear

import android.content.Context
import android.view.ViewGroup
import androidx.wear.widget.BoxInsetLayout
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsFactory
import java.lang.Class
import kotlin.String

open class BoxInsetLayoutLayoutParamsFactory<TLayoutParams : BoxInsetLayout.LayoutParams, TAttributes : BoxInsetLayoutLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = BoxInsetLayout.LayoutParams(BoxInsetLayout.LayoutParams.MATCH_PARENT, BoxInsetLayout.LayoutParams.MATCH_PARENT)

    override fun init(
        layoutParams: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(layoutParams, context, attributes)
        if (layoutParams is BoxInsetLayout.LayoutParams) {
            layoutParams.apply {
                attributes.boxedEdges?.let {
                    if (it.value != boxedEdges) {
                        boxedEdges = it.value
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "androidx.wear.widget.BoxInsetLayout.LayoutParams"

        inline operator fun <reified TLayoutParams : BoxInsetLayout.LayoutParams, reified TAttributes : BoxInsetLayoutLayoutParamsAttributes> invoke() = BoxInsetLayoutLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
