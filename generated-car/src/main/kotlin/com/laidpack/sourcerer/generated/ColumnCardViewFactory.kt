package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.car.widget.ColumnCardView
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class ColumnCardViewFactory<TView : ColumnCardView, TAttributes : ColumnCardViewAttributes> : FrameLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "columnCardView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = ColumnCardView(context)
}
