package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.ScrollView
import com.laidpack.generator.api.ViewGroupElement
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = ScrollViewFactory.elementType,
        attributesClazz = ScrollViewAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class ScrollViewFactory<TView : ScrollView, TAttributes : ScrollViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = ScrollView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is ScrollView) {
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
        const val elementType: String = "scrollView"

        inline operator fun <reified TView : ScrollView, reified TAttributes : ScrollViewAttributes> invoke() = ScrollViewFactory(TView::class.java, TAttributes::class.java)
    }
}
