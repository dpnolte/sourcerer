package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.ViewGroup
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.String

open class ViewGroupMarginLayoutParamsFactory<TLayoutParams : ViewGroup.MarginLayoutParams, TAttributes : ViewGroupMarginLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "android.view.ViewGroup.MarginLayoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.MATCH_PARENT, ViewGroup.MarginLayoutParams.MATCH_PARENT)

    override fun init(
        layoutParams: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(layoutParams, context, attributes)
        if (layoutParams is ViewGroup.MarginLayoutParams) {
            layoutParams.init {
                if (attributes.layout_marginLeft != null || attributes.layout_marginTop != null || attributes.layout_marginRight != null || attributes.layout_marginBottom != null) {
                    val immutableLayoutMarginLeft = attributes.layout_marginLeft ?: leftMargin
                    val immutableLayoutMarginTop = attributes.layout_marginTop ?: topMargin
                    val immutableLayoutMarginRight = attributes.layout_marginRight ?: rightMargin
                    val immutableLayoutMarginBottom = attributes.layout_marginBottom ?: bottomMargin
                    if (leftMargin != immutableLayoutMarginLeft || topMargin != immutableLayoutMarginTop || rightMargin != immutableLayoutMarginRight || bottomMargin != immutableLayoutMarginBottom) {
                        setMargins(immutableLayoutMarginLeft, immutableLayoutMarginTop, immutableLayoutMarginRight, immutableLayoutMarginBottom)
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
        init {
            InflaterComponent.addFactory(ViewGroupMarginLayoutParamsFactory<ViewGroup.MarginLayoutParams, ViewGroupMarginLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : ViewGroup.MarginLayoutParams, reified TAttributes : ViewGroupMarginLayoutParamsAttributes> invoke() = ViewGroupMarginLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
