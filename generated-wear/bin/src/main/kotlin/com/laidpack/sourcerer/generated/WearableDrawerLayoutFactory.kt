package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.wear.widget.drawer.WearableDrawerLayout
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class WearableDrawerLayoutFactory<TView : WearableDrawerLayout, TAttributes : WearableDrawerLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "wearableDrawerLayout"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = WearableDrawerLayout(context)

    companion object {
        init {
            InflaterComponent.addFactory(WearableDrawerLayoutFactory<WearableDrawerLayout, WearableDrawerLayoutAttributes>())
        }

        inline operator fun <reified TView : WearableDrawerLayout, reified TAttributes : WearableDrawerLayoutAttributes> invoke() = WearableDrawerLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
