package com.laidpack.sourcerer.generated.cardview

import android.content.Context
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
                attributes.cardElevation?.let {
                    val localCardElevation = it.toFloat()
                    if (elevation != localCardElevation) {
                        cardElevation = localCardElevation
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
                    val localContentPaddingLeft = attributes.contentPaddingLeft ?: left
                    val localContentPaddingTop = attributes.contentPaddingTop ?: top
                    val localContentPaddingRight = attributes.contentPaddingRight ?: right
                    val localContentPaddingBottom = attributes.contentPaddingBottom ?: bottom
                    if (left != localContentPaddingLeft || top != localContentPaddingTop || right != localContentPaddingRight || bottom != localContentPaddingBottom) {
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
