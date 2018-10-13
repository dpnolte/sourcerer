package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.ViewFlipper
import java.lang.Class
import kotlin.String

open class ViewFlipperFactory<TView : ViewFlipper, TAttributes : ViewFlipperAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewAnimatorFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = ViewFlipper(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is ViewFlipper) {
            view.apply {
                attributes.autoStart?.let {
                    if (isAutoStart != it) {
                        isAutoStart = it
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "viewFlipper"

        inline operator fun <reified TView : ViewFlipper, reified TAttributes : ViewFlipperAttributes> invoke() = ViewFlipperFactory(TView::class.java, TAttributes::class.java)
    }
}
