package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.RatingBar
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.Int
import kotlin.String

open class RatingBarFactory<TView : RatingBar, TAttributes : RatingBarAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AbsSeekBarFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "ratingBar"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = RatingBar(context)

    override fun init(
        view: TView,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        view.init {
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

    companion object {
        init {
            InflaterComponent.addFactory(RatingBarFactory<RatingBar, RatingBarAttributes>())
        }

        inline operator fun <reified TView : RatingBar, reified TAttributes : RatingBarAttributes> invoke() = RatingBarFactory(TView::class.java, TAttributes::class.java)
    }
}
