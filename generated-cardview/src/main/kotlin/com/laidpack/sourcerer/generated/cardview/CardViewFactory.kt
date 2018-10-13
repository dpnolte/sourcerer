package com.laidpack.sourcerer.generated.cardview

import android.content.Context
import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import com.laidpack.sourcerer.generated.FrameLayoutFactory
import java.lang.Class
import kotlin.String

open class CardViewFactory<TView : CardView, TAttributes : CardViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = CardView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is CardView) {
            view.apply {
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
                if (attributes.contentPaddingLeft != null || attributes.contentPaddingTop != null || attributes.contentPaddingRight != null || attributes.contentPaddingBottom != null) {
                    val immutableContentPaddingLeft = attributes.contentPaddingLeft ?: contentPaddingLeft
                    val immutableContentPaddingTop = attributes.contentPaddingTop ?: contentPaddingTop
                    val immutableContentPaddingRight = attributes.contentPaddingRight ?: contentPaddingRight
                    val immutableContentPaddingBottom = attributes.contentPaddingBottom ?: contentPaddingBottom
                    if (contentPaddingLeft != immutableContentPaddingLeft || contentPaddingTop != immutableContentPaddingTop || contentPaddingRight != immutableContentPaddingRight || contentPaddingBottom != immutableContentPaddingBottom) {
                        setContentPadding(immutableContentPaddingLeft, immutableContentPaddingTop, immutableContentPaddingRight, immutableContentPaddingBottom)
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "cardView"

        inline operator fun <reified TView : CardView, reified TAttributes : CardViewAttributes> invoke() = CardViewFactory(TView::class.java, TAttributes::class.java)
    }
}
