package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import com.google.android.material.circularreveal.cardview.CircularRevealCardView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class CircularRevealCardViewFactory<TView : CircularRevealCardView, TAttributes : CircularRevealCardViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : CardViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "circularRevealCardView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = CircularRevealCardView(context)

    companion object {
        init {
            InflaterComponent.addFactory(CircularRevealCardViewFactory<CircularRevealCardView, CircularRevealCardViewAttributes>())
        }

        inline operator fun <reified TView : CircularRevealCardView, reified TAttributes : CircularRevealCardViewAttributes> invoke() = CircularRevealCardViewFactory(TView::class.java, TAttributes::class.java)
    }
}
