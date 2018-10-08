package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class SlidingPaneLayoutLayoutParamsFactory<TLayoutParams : SlidingPaneLayout.LayoutParams, TAttributes : SlidingPaneLayoutLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String =
            "androidx.slidingpanelayout.widget.SlidingPaneLayout.LayoutParams"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): ViewGroup.LayoutParams = SlidingPaneLayout.LayoutParams(SlidingPaneLayout.LayoutParams.MATCH_PARENT, SlidingPaneLayout.LayoutParams.MATCH_PARENT)

    companion object {
        init {
            InflaterComponent.addFactory(SlidingPaneLayoutLayoutParamsFactory<SlidingPaneLayout.LayoutParams, SlidingPaneLayoutLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : SlidingPaneLayout.LayoutParams, reified TAttributes : SlidingPaneLayoutLayoutParamsAttributes> invoke() = SlidingPaneLayoutLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
