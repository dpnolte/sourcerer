package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import com.laidpack.sourcerer.service.api.init
import kotlin.String

open class SearchViewFactory<TView : SearchView, TAttributes : SearchViewAttributes> : LinearLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "searchView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = SearchView(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as SearchView
        view.init {
            attributes.iconifiedByDefault?.let {
                if (isIconified != it) {
                    setIconifiedByDefault(it)
                }
            }
            if (Build.VERSION.SDK_INT >= 16) {
                attributes.maxWidth?.let {
                    if (maxWidth != it) {
                        maxWidth = it
                    }
                }
                attributes.queryHint?.let {
                    if (queryHint != it) {
                        queryHint = it
                    }
                }
                attributes.imeOptions?.let {
                    if (imeOptions != it) {
                        imeOptions = it
                    }
                }
                attributes.inputType?.let {
                    if (inputType != it) {
                        inputType = it
                    }
                }
            }
        }
    }
}
