package com.laidpack.sourcerer.generated.wear

import android.content.Context
import android.view.View
import androidx.wear.widget.drawer.WearableNavigationDrawerView
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = WearableNavigationDrawerViewFactory.elementType,
        attributesClazz = WearableNavigationDrawerViewAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class WearableNavigationDrawerViewFactory<TView : WearableNavigationDrawerView, TAttributes : WearableNavigationDrawerViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : WearableDrawerViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = WearableNavigationDrawerView(context)

    companion object {
        const val elementType: String = "wearableNavigationDrawerView"

        inline operator fun <reified TView : WearableNavigationDrawerView, reified TAttributes : WearableNavigationDrawerViewAttributes> invoke() = WearableNavigationDrawerViewFactory(TView::class.java, TAttributes::class.java)
    }
}
