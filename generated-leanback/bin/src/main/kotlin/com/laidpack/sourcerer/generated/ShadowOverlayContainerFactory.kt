package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.leanback.widget.ShadowOverlayContainer
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class ShadowOverlayContainerFactory<TView : ShadowOverlayContainer, TAttributes : ShadowOverlayContainerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "shadowOverlayContainer"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = ShadowOverlayContainer(context)

    companion object {
        init {
            InflaterComponent.addFactory(ShadowOverlayContainerFactory<ShadowOverlayContainer, ShadowOverlayContainerAttributes>())
        }

        inline operator fun <reified TView : ShadowOverlayContainer, reified TAttributes : ShadowOverlayContainerAttributes> invoke() = ShadowOverlayContainerFactory(TView::class.java, TAttributes::class.java)
    }
}
