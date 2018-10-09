package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class FrameLayoutLayoutParamsFactory<TLayoutParams : FrameLayout.LayoutParams, TAttributes : FrameLayoutLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "android.widget.FrameLayout.LayoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)

    companion object {
        init {
            InflaterComponent.addFactory(FrameLayoutLayoutParamsFactory<FrameLayout.LayoutParams, FrameLayoutLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : FrameLayout.LayoutParams, reified TAttributes : FrameLayoutLayoutParamsAttributes> invoke() = FrameLayoutLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
