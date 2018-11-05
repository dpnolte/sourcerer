package com.laidpack.sourcerer.generated.car

import android.content.Context
import android.view.View
import androidx.car.widget.PagedScrollBarView
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.ViewGroupFactory
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = PagedScrollBarViewFactory.elementType,
        attributesClazz = PagedScrollBarViewAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class PagedScrollBarViewFactory<TView : PagedScrollBarView, TAttributes : PagedScrollBarViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = PagedScrollBarView(context)

    companion object {
        const val elementType: String = "pagedScrollBarView"

        inline operator fun <reified TView : PagedScrollBarView, reified TAttributes : PagedScrollBarViewAttributes> invoke() = PagedScrollBarViewFactory(TView::class.java, TAttributes::class.java)
    }
}
