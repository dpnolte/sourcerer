package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.SearchView
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.String

open class SearchViewFactory<TView : SearchView, TAttributes : SearchViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "searchView"

    override fun createInstance(context: Context): View = SearchView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is SearchView) {
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

    companion object {
        init {
            InflaterComponent.addFactory(SearchViewFactory<SearchView, SearchViewAttributes>())
        }

        inline operator fun <reified TView : SearchView, reified TAttributes : SearchViewAttributes> invoke() = SearchViewFactory(TView::class.java, TAttributes::class.java)
    }
}