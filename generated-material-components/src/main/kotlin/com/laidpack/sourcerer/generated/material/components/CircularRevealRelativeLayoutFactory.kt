package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.view.View
import com.google.android.material.circularreveal.CircularRevealRelativeLayout
import com.laidpack.sourcerer.generated.RelativeLayoutFactory
import java.lang.Class
import kotlin.String

open class CircularRevealRelativeLayoutFactory<TView : CircularRevealRelativeLayout, TAttributes : CircularRevealRelativeLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : RelativeLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = CircularRevealRelativeLayout(context)

    companion object {
        const val elementType: String = "circularRevealRelativeLayout"

        inline operator fun <reified TView : CircularRevealRelativeLayout, reified TAttributes : CircularRevealRelativeLayoutAttributes> invoke() = CircularRevealRelativeLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
