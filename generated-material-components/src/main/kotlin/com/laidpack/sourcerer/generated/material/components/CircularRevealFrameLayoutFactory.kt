package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.view.View
import com.google.android.material.circularreveal.CircularRevealFrameLayout
import com.laidpack.sourcerer.generated.FrameLayoutFactory
import java.lang.Class
import kotlin.String

open class CircularRevealFrameLayoutFactory<TView : CircularRevealFrameLayout, TAttributes : CircularRevealFrameLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = CircularRevealFrameLayout(context)

    companion object {
        const val elementType: String = "circularRevealFrameLayout"

        inline operator fun <reified TView : CircularRevealFrameLayout, reified TAttributes : CircularRevealFrameLayoutAttributes> invoke() = CircularRevealFrameLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
