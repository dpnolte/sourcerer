package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.ViewGroup
import java.lang.Class
import kotlin.String

open class ViewGroupMarginLayoutParamsFactory<TLayoutParams : ViewGroup.MarginLayoutParams, TAttributes : ViewGroupMarginLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.MATCH_PARENT, ViewGroup.MarginLayoutParams.MATCH_PARENT)

    override fun init(
        layoutParams: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(layoutParams, context, attributes)
        if (layoutParams is ViewGroup.MarginLayoutParams) {
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
                if (attributes.layout_marginLeft != null || attributes.layout_marginBottom != null || attributes.layout_marginRight != null || attributes.layout_marginTop != null) {
                    val localLayoutMarginLeftDimension = attributes.layout_marginLeft ?: leftMargin
                    if (localLayoutMarginLeftDimension != leftMargin) {
                        leftMargin = localLayoutMarginLeftDimension
                    }
                    val localLayoutMarginBottomDimension = attributes.layout_marginBottom ?: bottomMargin
                    if (localLayoutMarginBottomDimension != bottomMargin) {
                        bottomMargin = localLayoutMarginBottomDimension
                    }
                    val localLayoutMarginRightDimension = attributes.layout_marginRight ?: rightMargin
                    if (localLayoutMarginRightDimension != rightMargin) {
                        rightMargin = localLayoutMarginRightDimension
                    }
                    val localLayoutMarginTopDimension = attributes.layout_marginTop ?: topMargin
                    if (localLayoutMarginTopDimension != topMargin) {
                        topMargin = localLayoutMarginTopDimension
                    }
                }
                if (Build.VERSION.SDK_INT >= 17) {
                    attributes.layout_marginStart?.let {
                        if (marginStart != it) {
                            marginStart = it
                        }
                    }
                    attributes.layout_marginEnd?.let {
                        if (marginEnd != it) {
                            marginEnd = it
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "android.view.ViewGroup.MarginLayoutParams"

        inline operator fun <reified TLayoutParams : ViewGroup.MarginLayoutParams, reified TAttributes : ViewGroupMarginLayoutParamsAttributes> invoke() = ViewGroupMarginLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
