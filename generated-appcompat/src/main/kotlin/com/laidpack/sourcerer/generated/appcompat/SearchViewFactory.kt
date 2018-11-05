package com.laidpack.sourcerer.generated.appcompat

import android.content.Context
import android.view.View
import androidx.appcompat.widget.SearchView
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = SearchViewFactory.elementType,
        attributesClazz = SearchViewAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class SearchViewFactory<TView : SearchView, TAttributes : SearchViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutCompatFactory<TView, TAttributes>(instanceType, attributesType) {
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
                    if (isIconified != it) {
                        setIconifiedByDefault(it)
                    }
                }
                attributes.queryHint?.let {
                    if (queryHint != it) {
                        queryHint = it
                    }
                }
                attributes.android_maxWidth?.let {
                    if (maxWidth != it) {
                        maxWidth = it
                    }
                }
                attributes.android_focusable?.let {
                    if (isFocusable != it) {
                        isFocusable = it
                    }
                }
                attributes.android_imeOptions?.let {
                    if (imeOptions != it) {
                        imeOptions = it
                    }
                }
                attributes.android_inputType?.let {
                    if (inputType != it) {
                        inputType = it
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "appcompat.searchView"

        inline operator fun <reified TView : SearchView, reified TAttributes : SearchViewAttributes> invoke() = SearchViewFactory(TView::class.java, TAttributes::class.java)
    }
}
