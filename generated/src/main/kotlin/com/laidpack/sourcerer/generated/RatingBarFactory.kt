package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.RatingBar
import com.laidpack.generator.api.ViewElement
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = RatingBarFactory.elementType,
        attributesClazz = RatingBarAttributes::class
)
open class RatingBarFactory<TView : RatingBar, TAttributes : RatingBarAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AbsSeekBarFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = RatingBar(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is RatingBar) {
            view.apply {
                attributes.numStars?.let {
                    if (numStars != it) {
                        numStars = it
                    }
                }
                attributes.rating?.let {
                    if (rating != it) {
                        rating = it
                    }
                }
                attributes.stepSize?.let {
                    if (stepSize != it) {
                        stepSize = it
                    }
                }
                attributes.isIndicator?.let {
                    if (isIndicator != it) {
                        setIsIndicator(it)
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "ratingBar"

        inline operator fun <reified TView : RatingBar, reified TAttributes : RatingBarAttributes> invoke() = RatingBarFactory(TView::class.java, TAttributes::class.java)
    }
}
