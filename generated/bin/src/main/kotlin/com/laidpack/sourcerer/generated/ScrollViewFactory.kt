package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.ScrollView
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.Int
import kotlin.String

open class ScrollViewFactory<TView : ScrollView, TAttributes : ScrollViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "scrollView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = ScrollView(context)

    override fun init(
        view: TView,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        view.init {
            attributes.fillViewport?.let {
                if (isFillViewport != it) {
                    isFillViewport = it
                }
            }
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(ScrollViewFactory<ScrollView, ScrollViewAttributes>())
        }

        inline operator fun <reified TView : ScrollView, reified TAttributes : ScrollViewAttributes> invoke() = ScrollViewFactory(TView::class.java, TAttributes::class.java)
    }
}
