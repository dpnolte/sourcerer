package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.leanback.widget.ShadowOverlayContainer
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class ShadowOverlayContainerFactory<TView : ShadowOverlayContainer, TAttributes : ShadowOverlayContainerAttributes> : FrameLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "shadowOverlayContainer"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = ShadowOverlayContainer(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
    }
}