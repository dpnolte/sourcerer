package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import com.laidpack.sourcerer.service.api.init
import kotlin.String

open class RatingBarFactory<TView : RatingBar, TAttributes : RatingBarAttributes> : AbsSeekBarFactory<TView, TAttributes>() {
    override val elementName: String = "ratingBar"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = RatingBar(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as RatingBar
        view.init {
            attributes.isIndicator?.let {
                if (isIndicator != it) {
                    setIsIndicator(it)
                }
            }
        }
    }
}
