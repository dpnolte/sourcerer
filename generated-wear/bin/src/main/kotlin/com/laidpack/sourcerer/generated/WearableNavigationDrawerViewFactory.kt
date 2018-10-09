package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.wear.widget.drawer.WearableNavigationDrawerView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class WearableNavigationDrawerViewFactory<TView : WearableNavigationDrawerView, TAttributes : WearableNavigationDrawerViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "wearableNavigationDrawerView"

    override fun createInstance(context: Context): View = WearableNavigationDrawerView(context)

    companion object {
        init {
            InflaterComponent.addFactory(WearableNavigationDrawerViewFactory<WearableNavigationDrawerView, WearableNavigationDrawerViewAttributes>())
        }

        inline operator fun <reified TView : WearableNavigationDrawerView, reified TAttributes : WearableNavigationDrawerViewAttributes> invoke() = WearableNavigationDrawerViewFactory(TView::class.java, TAttributes::class.java)
    }
}
