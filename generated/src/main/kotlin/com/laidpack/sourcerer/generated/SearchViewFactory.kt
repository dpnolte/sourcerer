package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.SearchView
import java.lang.Class
import kotlin.String

open class SearchViewFactory<TView : SearchView, TAttributes : SearchViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = SearchView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is SearchView) {
            view.apply {
                attributes.iconifiedByDefault?.let {
                    if (isIconfiedByDefault != it) {
                        setIconifiedByDefault(it)
                    }
                }
                if (Build.VERSION.SDK_INT >= 16) {
                    attributes.queryHint?.let {
                        if (queryHint != it) {
                            queryHint = it
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "searchView"

        inline operator fun <reified TView : SearchView, reified TAttributes : SearchViewAttributes> invoke() = SearchViewFactory(TView::class.java, TAttributes::class.java)
    }
}
