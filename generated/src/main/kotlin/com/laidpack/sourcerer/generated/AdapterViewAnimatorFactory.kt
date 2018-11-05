package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.AdapterViewAnimator
import com.laidpack.generator.api.ViewGroupElement
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = AdapterViewAnimatorFactory.elementType,
        attributesClazz = AdapterViewAnimatorAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class AdapterViewAnimatorFactory<TView : AdapterViewAnimator, TAttributes : AdapterViewAnimatorAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AdapterViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is AdapterViewAnimator) {
            view.apply {
                attributes.inAnimation?.let {
                    setInAnimation(context, it)
                }
                attributes.outAnimation?.let {
                    setOutAnimation(context, it)
                }
                attributes.animateFirstView?.let {
                    setAnimateFirstView(it)
                }
            }
        }
    }

    companion object {
        const val elementType: String = "adapterViewAnimator"

        inline operator fun <reified TView : AdapterViewAnimator, reified TAttributes : AdapterViewAnimatorAttributes> invoke() = AdapterViewAnimatorFactory(TView::class.java, TAttributes::class.java)
    }
}
