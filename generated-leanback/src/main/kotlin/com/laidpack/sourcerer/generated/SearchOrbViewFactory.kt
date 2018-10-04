package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.leanback.widget.SearchOrbView
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class SearchOrbViewFactory<TView : SearchOrbView, TAttributes : SearchOrbViewAttributes> : FrameLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "searchOrbView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = SearchOrbView(context)
}
