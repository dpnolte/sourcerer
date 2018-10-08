package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.car.widget.PagedListView
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.Int
import kotlin.String

open class PagedListViewFactory<TView : PagedListView, TAttributes : PagedListViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "pagedListView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = PagedListView(context)

    override fun init(
        view: TView,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        view.init {
            attributes.scrollBarContainerWidth?.let {
                if (width != it) {
                    setScrollBarContainerWidth(it)
                }
            }
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(PagedListViewFactory<PagedListView, PagedListViewAttributes>())
        }

        inline operator fun <reified TView : PagedListView, reified TAttributes : PagedListViewAttributes> invoke() = PagedListViewFactory(TView::class.java, TAttributes::class.java)
    }
}
