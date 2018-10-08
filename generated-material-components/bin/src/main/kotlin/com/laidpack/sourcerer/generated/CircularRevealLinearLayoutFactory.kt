package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import com.google.android.material.circularreveal.CircularRevealLinearLayout
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class CircularRevealLinearLayoutFactory<TView : CircularRevealLinearLayout, TAttributes : CircularRevealLinearLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "circularRevealLinearLayout"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = CircularRevealLinearLayout(context)

    companion object {
        init {
            InflaterComponent.addFactory(CircularRevealLinearLayoutFactory<CircularRevealLinearLayout, CircularRevealLinearLayoutAttributes>())
        }

        inline operator fun <reified TView : CircularRevealLinearLayout, reified TAttributes : CircularRevealLinearLayoutAttributes> invoke() = CircularRevealLinearLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
