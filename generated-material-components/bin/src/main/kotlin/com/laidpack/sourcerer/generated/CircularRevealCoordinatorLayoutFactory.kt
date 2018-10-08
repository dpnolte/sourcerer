package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import com.google.android.material.circularreveal.coordinatorlayout.CircularRevealCoordinatorLayout
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class CircularRevealCoordinatorLayoutFactory<TView : CircularRevealCoordinatorLayout, TAttributes : CircularRevealCoordinatorLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "circularRevealCoordinatorLayout"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = CircularRevealCoordinatorLayout(context)

    companion object {
        init {
            InflaterComponent.addFactory(CircularRevealCoordinatorLayoutFactory<CircularRevealCoordinatorLayout, CircularRevealCoordinatorLayoutAttributes>())
        }

        inline operator fun <reified TView : CircularRevealCoordinatorLayout, reified TAttributes : CircularRevealCoordinatorLayoutAttributes> invoke() = CircularRevealCoordinatorLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
