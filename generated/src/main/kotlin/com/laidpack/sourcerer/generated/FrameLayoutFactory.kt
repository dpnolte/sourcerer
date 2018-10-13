package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import java.lang.Class
import kotlin.String

open class FrameLayoutFactory<TView : FrameLayout, TAttributes : FrameLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = FrameLayout(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is FrameLayout) {
            view.apply {
                attributes.measureAllChildren?.let {
                    if (measureAllChildren != it) {
                        measureAllChildren = it
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "frameLayout"

        inline operator fun <reified TView : FrameLayout, reified TAttributes : FrameLayoutAttributes> invoke() = FrameLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
