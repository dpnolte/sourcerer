package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.view.View
import androidx.leanback.widget.SearchBar
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.RelativeLayoutFactory
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = SearchBarFactory.elementType,
        attributesClazz = SearchBarAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class SearchBarFactory<TView : SearchBar, TAttributes : SearchBarAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : RelativeLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = SearchBar(context)

    companion object {
        const val elementType: String = "searchBar"

        inline operator fun <reified TView : SearchBar, reified TAttributes : SearchBarAttributes> invoke() = SearchBarFactory(TView::class.java, TAttributes::class.java)
    }
}
