package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import com.google.android.material.circularreveal.cardview.CircularRevealCardView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class CircularRevealCardViewFactory<TView : CircularRevealCardView, TAttributes : CircularRevealCardViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "circularRevealCardView"

    override fun createInstance(context: Context): View = CircularRevealCardView(context)

    companion object {
        init {
            InflaterComponent.addFactory(CircularRevealCardViewFactory<CircularRevealCardView, CircularRevealCardViewAttributes>())
        }

        inline operator fun <reified TView : CircularRevealCardView, reified TAttributes : CircularRevealCardViewAttributes> invoke() = CircularRevealCardViewFactory(TView::class.java, TAttributes::class.java)
    }
}
