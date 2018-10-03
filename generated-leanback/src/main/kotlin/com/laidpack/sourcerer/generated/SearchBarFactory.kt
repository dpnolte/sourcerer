package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.leanback.widget.SearchBar
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class SearchBarFactory<TView : SearchBar, TAttributes : SearchBarAttributes> : RelativeLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "searchBar"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = SearchBar(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
    }
}
