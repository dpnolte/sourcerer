package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import com.google.android.material.circularreveal.CircularRevealFrameLayout
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class CircularRevealFrameLayoutFactory<TView : CircularRevealFrameLayout, TAttributes : CircularRevealFrameLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "circularRevealFrameLayout"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = CircularRevealFrameLayout(context)

    companion object {
        init {
            InflaterComponent.addFactory(CircularRevealFrameLayoutFactory<CircularRevealFrameLayout, CircularRevealFrameLayoutAttributes>())
        }

        inline operator fun <reified TView : CircularRevealFrameLayout, reified TAttributes : CircularRevealFrameLayoutAttributes> invoke() = CircularRevealFrameLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
