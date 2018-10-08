package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.Int
import kotlin.String

open class CardViewFactory<TView : CardView, TAttributes : CardViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "cardView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = CardView(context)

    override fun init(
        view: TView,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
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
                if (contentPaddingLeft != immutableContentPaddingLeft || contentPaddingRight != immutableContentPaddingRight || contentPaddingTop != immutableContentPaddingTop || contentPaddingBottom != immutableContentPaddingBottom) {
                    setContentPadding(immutableContentPaddingLeft, immutableContentPaddingTop, immutableContentPaddingRight, immutableContentPaddingBottom)
                }
            }
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(CardViewFactory<CardView, CardViewAttributes>())
        }

        inline operator fun <reified TView : CardView, reified TAttributes : CardViewAttributes> invoke() = CardViewFactory(TView::class.java, TAttributes::class.java)
    }
}
