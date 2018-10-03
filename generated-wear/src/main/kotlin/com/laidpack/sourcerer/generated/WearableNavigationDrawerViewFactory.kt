package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.wear.widget.drawer.WearableNavigationDrawerView
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class WearableNavigationDrawerViewFactory<TView : WearableNavigationDrawerView, TAttributes : WearableNavigationDrawerViewAttributes> : FrameLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "wearableNavigationDrawerView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = WearableNavigationDrawerView(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
    }
}
