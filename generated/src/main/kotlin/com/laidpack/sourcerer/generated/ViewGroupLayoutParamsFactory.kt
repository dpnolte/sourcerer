package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import com.laidpack.generator.api.LayoutParamsElement
import com.laidpack.sourcerer.services.api.BaseLayoutParamsFactory
import java.lang.Class
import kotlin.String

@LayoutParamsElement(
        elementType = ViewGroupLayoutParamsFactory.elementType,
        attributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class ViewGroupLayoutParamsFactory<TLayoutParams : ViewGroup.LayoutParams, TAttributes : ViewGroupLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : BaseLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

    override fun init(
        layoutParams: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        layoutParams.apply {
            if (attributes.layout_width.hasDimension || attributes.layout_width.hasEnum) {
                val localLayoutWidth = when {
                    attributes.layout_width.hasDimension -> attributes.layout_width.dimension
                    else -> attributes.layout_width.enum
                }
                if (localLayoutWidth != width) {
                    width = localLayoutWidth
                }
            }
            if (attributes.layout_height.hasDimension || attributes.layout_height.hasEnum) {
                val localLayoutHeight = when {
                    attributes.layout_height.hasDimension -> attributes.layout_height.dimension
                    else -> attributes.layout_height.enum
                }
                if (localLayoutHeight != height) {
                    height = localLayoutHeight
                }
            }
        }
    }

    companion object {
        const val elementType: String = "android.view.ViewGroup.LayoutParams"

        inline operator fun <reified TLayoutParams : ViewGroup.LayoutParams, reified TAttributes : ViewGroupLayoutParamsAttributes> invoke() = ViewGroupLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
