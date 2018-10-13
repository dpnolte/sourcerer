package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import com.laidpack.sourcerer.services.api.BaseLayoutParamsFactory
import java.lang.Class
import kotlin.String

open class ViewGroupLayoutParamsFactory<TLayoutParams : ViewGroup.LayoutParams, TAttributes : ViewGroupLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : BaseLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

    override fun init(
        layoutParams: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        layoutParams.apply {
            attributes.layout_width?.let {
                if (it.value != width) {
                    width = it.value
                }
            }
            attributes.layout_height?.let {
                if (it.value != height) {
                    height = it.value
                }
            }
        }
    }

    companion object {
        const val elementType: String = "android.view.ViewGroup.LayoutParams"

        inline operator fun <reified TLayoutParams : ViewGroup.LayoutParams, reified TAttributes : ViewGroupLayoutParamsAttributes> invoke() = ViewGroupLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
