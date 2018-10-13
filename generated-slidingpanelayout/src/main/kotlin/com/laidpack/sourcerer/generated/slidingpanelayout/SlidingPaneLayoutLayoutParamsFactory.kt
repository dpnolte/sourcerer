package com.laidpack.sourcerer.generated.slidingpanelayout

import android.content.Context
import android.view.ViewGroup
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsFactory
import java.lang.Class
import kotlin.String

open class SlidingPaneLayoutLayoutParamsFactory<TLayoutParams : SlidingPaneLayout.LayoutParams, TAttributes : SlidingPaneLayoutLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = SlidingPaneLayout.LayoutParams(SlidingPaneLayout.LayoutParams.MATCH_PARENT, SlidingPaneLayout.LayoutParams.MATCH_PARENT)

    companion object {
        const val elementType: String =
                "androidx.slidingpanelayout.widget.SlidingPaneLayout.LayoutParams"

        inline operator fun <reified TLayoutParams : SlidingPaneLayout.LayoutParams, reified TAttributes : SlidingPaneLayoutLayoutParamsAttributes> invoke() = SlidingPaneLayoutLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
