package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import com.laidpack.sourcerer.service.api.init
import kotlin.String

open class CardViewFactory<TView : CardView, TAttributes : CardViewAttributes> : FrameLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "cardView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = CardView(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as CardView
        view.init {
            attributes.cardBackgroundColor?.let {
                val immutableCardBackgroundColor = ResourcesCompat.getColorStateList(context.resources, it, null)
                if (cardBackgroundColor != immutableCardBackgroundColor) {
                    setCardBackgroundColor(immutableCardBackgroundColor)
                }
            }
            attributes.cardCornerRadius?.let {
                val immutableCardCornerRadius = it.toFloat()
                if (radius != immutableCardCornerRadius) {
                    radius = immutableCardCornerRadius
                }
            }
            attributes.cardElevation?.let {
                val immutableCardElevation = it.toFloat()
                if (cardElevation != immutableCardElevation) {
                    cardElevation = immutableCardElevation
                }
            }
            attributes.cardMaxElevation?.let {
                val immutableCardMaxElevation = it.toFloat()
                if (maxCardElevation != immutableCardMaxElevation) {
                    maxCardElevation = immutableCardMaxElevation
                }
            }
            attributes.cardUseCompatPadding?.let {
                if (useCompatPadding != it) {
                    useCompatPadding = it
                }
            }
            attributes.cardPreventCornerOverlap?.let {
                if (preventCornerOverlap != it) {
                    preventCornerOverlap = it
                }
            }
            if (attributes.contentPaddingLeft != null || attributes.contentPaddingRight != null || attributes.contentPaddingTop != null || attributes.contentPaddingBottom != null) {
                val immutableContentPaddingLeft = attributes.contentPaddingLeft ?: contentPaddingLeft
                val immutableContentPaddingRight = attributes.contentPaddingRight ?: contentPaddingRight
                val immutableContentPaddingTop = attributes.contentPaddingTop ?: contentPaddingTop
                val immutableContentPaddingBottom = attributes.contentPaddingBottom ?: contentPaddingBottom
                if (contentPaddingLeft != attributes.contentPaddingLeft || contentPaddingRight != attributes.contentPaddingRight || contentPaddingTop != attributes.contentPaddingTop || contentPaddingBottom != attributes.contentPaddingBottom) {
                    setContentPadding(immutableContentPaddingLeft, immutableContentPaddingTop, immutableContentPaddingRight, immutableContentPaddingBottom)
                }
            }
        }
    }
}
