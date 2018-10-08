package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.HorizontalScrollView
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.Int
import kotlin.String

open class HorizontalScrollViewFactory<TView : HorizontalScrollView, TAttributes : HorizontalScrollViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "horizontalScrollView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = HorizontalScrollView(context)

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
            InflaterComponent.addFactory(HorizontalScrollViewFactory<HorizontalScrollView, HorizontalScrollViewAttributes>())
        }

        inline operator fun <reified TView : HorizontalScrollView, reified TAttributes : HorizontalScrollViewAttributes> invoke() = HorizontalScrollViewFactory(TView::class.java, TAttributes::class.java)
    }
}
