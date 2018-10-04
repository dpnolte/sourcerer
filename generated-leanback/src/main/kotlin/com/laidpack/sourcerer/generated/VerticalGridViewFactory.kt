package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.leanback.widget.VerticalGridView
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class VerticalGridViewFactory<TView : VerticalGridView, TAttributes : VerticalGridViewAttributes> : ViewGroupFactory<TView, TAttributes>() {
    override val elementName: String = "verticalGridView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = VerticalGridView(context)
}
