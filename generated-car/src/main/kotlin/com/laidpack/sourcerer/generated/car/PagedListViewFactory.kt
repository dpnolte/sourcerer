package com.laidpack.sourcerer.generated.car

import android.content.Context
import android.view.View
import androidx.car.widget.PagedListView
import com.laidpack.sourcerer.generated.FrameLayoutFactory
import java.lang.Class
import kotlin.String

open class PagedListViewFactory<TView : PagedListView, TAttributes : PagedListViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = PagedListView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is PagedListView) {
            view.apply {
                attributes.scrollBarContainerWidth?.let {
                    if (width != it) {
                        setScrollBarContainerWidth(it)
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "pagedListView"

        inline operator fun <reified TView : PagedListView, reified TAttributes : PagedListViewAttributes> invoke() = PagedListViewFactory(TView::class.java, TAttributes::class.java)
    }
}
