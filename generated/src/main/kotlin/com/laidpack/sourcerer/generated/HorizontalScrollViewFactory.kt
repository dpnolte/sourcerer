package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.HorizontalScrollView
import com.laidpack.generator.api.ViewGroupElement
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = HorizontalScrollViewFactory.elementType,
        attributesClazz = HorizontalScrollViewAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class HorizontalScrollViewFactory<TView : HorizontalScrollView, TAttributes : HorizontalScrollViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = HorizontalScrollView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is HorizontalScrollView) {
            view.apply {
                attributes.fillViewport?.let {
                    if (isFillViewport != it) {
                        isFillViewport = it
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "horizontalScrollView"

        inline operator fun <reified TView : HorizontalScrollView, reified TAttributes : HorizontalScrollViewAttributes> invoke() = HorizontalScrollViewFactory(TView::class.java, TAttributes::class.java)
    }
}
