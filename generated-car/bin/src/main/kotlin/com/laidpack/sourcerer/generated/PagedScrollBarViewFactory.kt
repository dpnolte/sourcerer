package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.car.widget.PagedScrollBarView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class PagedScrollBarViewFactory<TView : PagedScrollBarView, TAttributes : PagedScrollBarViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "pagedScrollBarView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = PagedScrollBarView(context)

    companion object {
        init {
            InflaterComponent.addFactory(PagedScrollBarViewFactory<PagedScrollBarView, PagedScrollBarViewAttributes>())
        }

        inline operator fun <reified TView : PagedScrollBarView, reified TAttributes : PagedScrollBarViewAttributes> invoke() = PagedScrollBarViewFactory(TView::class.java, TAttributes::class.java)
    }
}
