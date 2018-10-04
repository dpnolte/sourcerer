package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class SwipeRefreshLayoutFactory<TView : SwipeRefreshLayout, TAttributes : SwipeRefreshLayoutAttributes> : ViewGroupFactory<TView, TAttributes>() {
    override val elementName: String = "swipeRefreshLayout"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = SwipeRefreshLayout(context)
}
