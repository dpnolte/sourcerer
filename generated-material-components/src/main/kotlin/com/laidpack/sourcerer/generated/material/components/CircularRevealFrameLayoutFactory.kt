package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.view.View
import com.google.android.material.circularreveal.CircularRevealFrameLayout
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.FrameLayoutFactory
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = CircularRevealFrameLayoutFactory.elementType,
        attributesClazz = CircularRevealFrameLayoutAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class CircularRevealFrameLayoutFactory<TView : CircularRevealFrameLayout, TAttributes : CircularRevealFrameLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = CircularRevealFrameLayout(context)

    companion object {
        const val elementType: String = "circularRevealFrameLayout"

        inline operator fun <reified TView : CircularRevealFrameLayout, reified TAttributes : CircularRevealFrameLayoutAttributes> invoke() = CircularRevealFrameLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
