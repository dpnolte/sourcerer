package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.BaseLayoutParamsFactory
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.Int
import kotlin.String

open class ViewGroupLayoutParamsFactory<TLayoutParams : ViewGroup.LayoutParams, TAttributes : ViewGroupLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : BaseLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "android.view.ViewGroup.LayoutParams"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): ViewGroup.LayoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

    override fun init(
        layoutParams: TLayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        layoutParams.init {
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
        init {
            InflaterComponent.addFactory(ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : ViewGroup.LayoutParams, reified TAttributes : ViewGroupLayoutParamsAttributes> invoke() = ViewGroupLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
