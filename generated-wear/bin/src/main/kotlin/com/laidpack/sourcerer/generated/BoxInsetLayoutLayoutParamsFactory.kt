package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import androidx.wear.widget.BoxInsetLayout
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class BoxInsetLayoutLayoutParamsFactory<TLayoutParams : BoxInsetLayout.LayoutParams, TAttributes : BoxInsetLayoutLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "androidx.wear.widget.BoxInsetLayout.LayoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = BoxInsetLayout.LayoutParams(BoxInsetLayout.LayoutParams.MATCH_PARENT, BoxInsetLayout.LayoutParams.MATCH_PARENT)

    companion object {
        init {
            InflaterComponent.addFactory(BoxInsetLayoutLayoutParamsFactory<BoxInsetLayout.LayoutParams, BoxInsetLayoutLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : BoxInsetLayout.LayoutParams, reified TAttributes : BoxInsetLayoutLayoutParamsAttributes> invoke() = BoxInsetLayoutLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
