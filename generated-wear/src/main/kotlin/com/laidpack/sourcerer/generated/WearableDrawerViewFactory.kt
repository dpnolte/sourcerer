package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.wear.widget.drawer.WearableDrawerView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class WearableDrawerViewFactory<TView : WearableDrawerView, TAttributes : WearableDrawerViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "wearableDrawerView"

    override fun createInstance(context: Context): View = WearableDrawerView(context)

    companion object {
        init {
            InflaterComponent.addFactory(WearableDrawerViewFactory<WearableDrawerView, WearableDrawerViewAttributes>())
        }

        inline operator fun <reified TView : WearableDrawerView, reified TAttributes : WearableDrawerViewAttributes> invoke() = WearableDrawerViewFactory(TView::class.java, TAttributes::class.java)
    }
}
