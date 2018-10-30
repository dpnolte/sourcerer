package com.laidpack.sourcerer.generated.core

import android.content.Context
import android.view.View
import androidx.core.widget.NestedScrollView
import com.laidpack.sourcerer.generated.FrameLayoutFactory
import java.lang.Class
import kotlin.String

open class NestedScrollViewFactory<TView : NestedScrollView, TAttributes : NestedScrollViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = NestedScrollView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is NestedScrollView) {
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
        const val elementType: String = "nestedScrollView"

        inline operator fun <reified TView : NestedScrollView, reified TAttributes : NestedScrollViewAttributes> invoke() = NestedScrollViewFactory(TView::class.java, TAttributes::class.java)
    }
}
