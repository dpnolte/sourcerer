package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.leanback.widget.SearchEditText
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class SearchEditTextFactory<TView : SearchEditText, TAttributes : SearchEditTextAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : EditTextFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "searchEditText"

    override fun createInstance(context: Context): View = SearchEditText(context)

    companion object {
        init {
            InflaterComponent.addFactory(SearchEditTextFactory<SearchEditText, SearchEditTextAttributes>())
        }

        inline operator fun <reified TView : SearchEditText, reified TAttributes : SearchEditTextAttributes> invoke() = SearchEditTextFactory(TView::class.java, TAttributes::class.java)
    }
}
