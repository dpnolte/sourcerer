package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.view.View
import com.google.android.material.circularreveal.coordinatorlayout.CircularRevealCoordinatorLayout
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import com.laidpack.sourcerer.generated.coordinatorlayout.CoordinatorLayoutFactory
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = CircularRevealCoordinatorLayoutFactory.elementType,
        attributesClazz = CircularRevealCoordinatorLayoutAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class CircularRevealCoordinatorLayoutFactory<TView : CircularRevealCoordinatorLayout, TAttributes : CircularRevealCoordinatorLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : CoordinatorLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = CircularRevealCoordinatorLayout(context)

    companion object {
        const val elementType: String = "circularRevealCoordinatorLayout"

        inline operator fun <reified TView : CircularRevealCoordinatorLayout, reified TAttributes : CircularRevealCoordinatorLayoutAttributes> invoke() = CircularRevealCoordinatorLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
