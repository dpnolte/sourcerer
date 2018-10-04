package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.wear.widget.drawer.WearableDrawerView
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class WearableDrawerViewFactory<TView : WearableDrawerView, TAttributes : WearableDrawerViewAttributes> : FrameLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "wearableDrawerView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = WearableDrawerView(context)
}
