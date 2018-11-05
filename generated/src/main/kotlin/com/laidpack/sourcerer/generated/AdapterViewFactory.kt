package com.laidpack.sourcerer.generated

import android.widget.AdapterView
import com.laidpack.generator.api.ViewGroupElement
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = AdapterViewFactory.elementType,
        attributesClazz = AdapterViewAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class AdapterViewFactory<TView : AdapterView<*>, TAttributes : AdapterViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    companion object {
        const val elementType: String = "adapterView"

        inline operator fun <reified TView : AdapterView<*>, reified TAttributes : AdapterViewAttributes> invoke() = AdapterViewFactory(TView::class.java, TAttributes::class.java)
    }
}
