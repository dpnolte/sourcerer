package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.view.View
import com.google.android.material.circularreveal.CircularRevealLinearLayout
import com.laidpack.sourcerer.generated.LinearLayoutFactory
import java.lang.Class
import kotlin.String

open class CircularRevealLinearLayoutFactory<TView : CircularRevealLinearLayout, TAttributes : CircularRevealLinearLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = CircularRevealLinearLayout(context)

    companion object {
        const val elementType: String = "circularRevealLinearLayout"

        inline operator fun <reified TView : CircularRevealLinearLayout, reified TAttributes : CircularRevealLinearLayoutAttributes> invoke() = CircularRevealLinearLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
