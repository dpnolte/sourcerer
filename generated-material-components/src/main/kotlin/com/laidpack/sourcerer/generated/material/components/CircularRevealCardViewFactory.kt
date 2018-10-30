package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.view.View
import com.google.android.material.circularreveal.cardview.CircularRevealCardView
import com.laidpack.sourcerer.generated.cardview.CardViewFactory
import java.lang.Class
import kotlin.String

open class CircularRevealCardViewFactory<TView : CircularRevealCardView, TAttributes : CircularRevealCardViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : CardViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = CircularRevealCardView(context)

    companion object {
        const val elementType: String = "circularRevealCardView"

        inline operator fun <reified TView : CircularRevealCardView, reified TAttributes : CircularRevealCardViewAttributes> invoke() = CircularRevealCardViewFactory(TView::class.java, TAttributes::class.java)
    }
}
