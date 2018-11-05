package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.view.View
import androidx.leanback.widget.ShadowOverlayContainer
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.FrameLayoutFactory
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = ShadowOverlayContainerFactory.elementType,
        attributesClazz = ShadowOverlayContainerAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class ShadowOverlayContainerFactory<TView : ShadowOverlayContainer, TAttributes : ShadowOverlayContainerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = ShadowOverlayContainer(context)

    companion object {
        const val elementType: String = "shadowOverlayContainer"

        inline operator fun <reified TView : ShadowOverlayContainer, reified TAttributes : ShadowOverlayContainerAttributes> invoke() = ShadowOverlayContainerFactory(TView::class.java, TAttributes::class.java)
    }
}
