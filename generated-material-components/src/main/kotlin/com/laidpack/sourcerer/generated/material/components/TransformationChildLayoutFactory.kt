package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.view.View
import com.google.android.material.transformation.TransformationChildLayout
import com.laidpack.sourcerer.generated.FrameLayoutFactory
import java.lang.Class
import kotlin.String

open class TransformationChildLayoutFactory<TView : TransformationChildLayout, TAttributes : TransformationChildLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = TransformationChildLayout(context)

    companion object {
        const val elementType: String = "transformationChildLayout"

        inline operator fun <reified TView : TransformationChildLayout, reified TAttributes : TransformationChildLayoutAttributes> invoke() = TransformationChildLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
