package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.view.View
import com.google.android.material.transformation.TransformationChildCard
import com.laidpack.sourcerer.generated.FrameLayoutFactory
import java.lang.Class
import kotlin.String

open class TransformationChildCardFactory<TView : TransformationChildCard, TAttributes : TransformationChildCardAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = TransformationChildCard(context)

    companion object {
        const val elementType: String = "transformationChildCard"

        inline operator fun <reified TView : TransformationChildCard, reified TAttributes : TransformationChildCardAttributes> invoke() = TransformationChildCardFactory(TView::class.java, TAttributes::class.java)
    }
}
