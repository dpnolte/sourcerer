package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.leanback.widget.SearchBar
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class SearchBarFactory<TView : SearchBar, TAttributes : SearchBarAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : RelativeLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "searchBar"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = SearchBar(context)

    companion object {
        init {
            InflaterComponent.addFactory(SearchBarFactory<SearchBar, SearchBarAttributes>())
        }

        inline operator fun <reified TView : SearchBar, reified TAttributes : SearchBarAttributes> invoke() = SearchBarFactory(TView::class.java, TAttributes::class.java)
    }
}
