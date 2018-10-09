package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.appcompat.widget.SearchView
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.String

open class SearchViewFactory<TView : SearchView, TAttributes : SearchViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
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
                attributes.queryHint?.let {
                    if (queryHint != it) {
                        queryHint = it
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
