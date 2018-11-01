package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
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
                if (Build.VERSION.SDK_INT >= 16) {
                    attributes.foregroundGravity?.let {
                        val localForegroundGravity = it.value
                        if (foregroundGravity != localForegroundGravity) {
                            foregroundGravity = localForegroundGravity
                        }
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
