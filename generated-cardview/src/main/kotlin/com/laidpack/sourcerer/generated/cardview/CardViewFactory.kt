package com.laidpack.sourcerer.generated.cardview

import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import androidx.cardview.widget.CardView
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
                    val localCardBackgroundColor = ColorStateList.valueOf(it)
                    if (cardBackgroundColor != localCardBackgroundColor) {
                        setCardBackgroundColor(localCardBackgroundColor)
                    }
                }
                attributes.cardCornerRadius?.let {
                    val localCardCornerRadius = it.toFloat()
                    if (radius != localCardCornerRadius) {
                        radius = localCardCornerRadius
                    }
                }
                attributes.cardElevation?.let {
                    val localCardElevation = it.toFloat()
                    if (elevation != localCardElevation) {
                        cardElevation = localCardElevation
                    }
                }
                attributes.cardMaxElevation?.let {
                    val localCardMaxElevation = it.toFloat()
                    if (maxCardElevation != localCardMaxElevation) {
                        maxCardElevation = localCardMaxElevation
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
                if (attributes.contentPaddingLeft != null || attributes.contentPaddingBottom != null || attributes.contentPaddingRight != null || attributes.contentPaddingTop != null) {
                    val localContentPaddingLeft = attributes.contentPaddingLeft ?: left
                    val localContentPaddingBottom = attributes.contentPaddingBottom ?: bottom
                    val localContentPaddingRight = attributes.contentPaddingRight ?: right
                    val localContentPaddingTop = attributes.contentPaddingTop ?: top
                    if (left != localContentPaddingLeft || bottom != localContentPaddingBottom || right != localContentPaddingRight || top != localContentPaddingTop) {
                        setContentPadding(localContentPaddingLeft, localContentPaddingTop, localContentPaddingRight, localContentPaddingBottom)
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
