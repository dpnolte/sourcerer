package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.wear.widget.drawer.WearableActionDrawerView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class WearableActionDrawerViewFactory<TView : WearableActionDrawerView, TAttributes : WearableActionDrawerViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "wearableActionDrawerView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = WearableActionDrawerView(context)

    companion object {
        init {
            InflaterComponent.addFactory(WearableActionDrawerViewFactory<WearableActionDrawerView, WearableActionDrawerViewAttributes>())
        }

        inline operator fun <reified TView : WearableActionDrawerView, reified TAttributes : WearableActionDrawerViewAttributes> invoke() = WearableActionDrawerViewFactory(TView::class.java, TAttributes::class.java)
    }
}
