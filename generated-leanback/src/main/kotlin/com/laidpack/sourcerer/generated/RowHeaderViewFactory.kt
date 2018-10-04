package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.leanback.widget.RowHeaderView
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class RowHeaderViewFactory<TView : RowHeaderView, TAttributes : RowHeaderViewAttributes> : TextViewFactory<TView, TAttributes>() {
    override val elementName: String = "rowHeaderView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = RowHeaderView(context)
}
