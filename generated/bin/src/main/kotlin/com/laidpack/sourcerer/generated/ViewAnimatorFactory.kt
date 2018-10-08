package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.ViewAnimator
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.Int
import kotlin.String

open class ViewAnimatorFactory<TView : ViewAnimator, TAttributes : ViewAnimatorAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "viewAnimator"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = ViewAnimator(context)

    override fun init(
        view: TView,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        view.init {
            attributes.animateFirstView?.let {
                if (animateFirstView != it) {
                    animateFirstView = it
                }
            }
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(ViewAnimatorFactory<ViewAnimator, ViewAnimatorAttributes>())
        }

        inline operator fun <reified TView : ViewAnimator, reified TAttributes : ViewAnimatorAttributes> invoke() = ViewAnimatorFactory(TView::class.java, TAttributes::class.java)
    }
}
