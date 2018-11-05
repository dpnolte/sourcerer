package com.laidpack.sourcerer.generated.wear

import android.content.Context
import android.view.View
import androidx.wear.widget.drawer.WearableActionDrawerView
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = WearableActionDrawerViewFactory.elementType,
        attributesClazz = WearableActionDrawerViewAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class WearableActionDrawerViewFactory<TView : WearableActionDrawerView, TAttributes : WearableActionDrawerViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : WearableDrawerViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = WearableActionDrawerView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is WearableActionDrawerView) {
            view.apply {
                attributes.drawerTitle?.let {
                    setTitle(it)
                }
            }
        }
    }

    companion object {
        const val elementType: String = "wearableActionDrawerView"

        inline operator fun <reified TView : WearableActionDrawerView, reified TAttributes : WearableActionDrawerViewAttributes> invoke() = WearableActionDrawerViewFactory(TView::class.java, TAttributes::class.java)
    }
}
