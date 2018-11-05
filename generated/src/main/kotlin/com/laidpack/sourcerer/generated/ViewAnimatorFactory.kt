package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.ViewAnimator
import com.laidpack.generator.api.ViewGroupElement
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = ViewAnimatorFactory.elementType,
        attributesClazz = ViewAnimatorAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class ViewAnimatorFactory<TView : ViewAnimator, TAttributes : ViewAnimatorAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = ViewAnimator(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is ViewAnimator) {
            view.apply {
                attributes.inAnimation?.let {
                    setInAnimation(context, it)
                }
                attributes.outAnimation?.let {
                    setOutAnimation(context, it)
                }
                attributes.FrameLayout_measureAllChildren?.let {
                    if (measureAllChildren != it) {
                        measureAllChildren = it
                    }
                }
                attributes.animateFirstView?.let {
                    if (animateFirstView != it) {
                        animateFirstView = it
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "viewAnimator"

        inline operator fun <reified TView : ViewAnimator, reified TAttributes : ViewAnimatorAttributes> invoke() = ViewAnimatorFactory(TView::class.java, TAttributes::class.java)
    }
}
