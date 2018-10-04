package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.wear.widget.WearableRecyclerView
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class WearableRecyclerViewFactory<TView : WearableRecyclerView, TAttributes : WearableRecyclerViewAttributes> : ViewGroupFactory<TView, TAttributes>() {
    override val elementName: String = "wearableRecyclerView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = WearableRecyclerView(context)
}
