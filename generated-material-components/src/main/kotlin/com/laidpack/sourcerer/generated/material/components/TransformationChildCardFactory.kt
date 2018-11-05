package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.view.View
import com.google.android.material.transformation.TransformationChildCard
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = TransformationChildCardFactory.elementType,
        attributesClazz = TransformationChildCardAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class TransformationChildCardFactory<TView : TransformationChildCard, TAttributes : TransformationChildCardAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : CircularRevealCardViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = TransformationChildCard(context)

    companion object {
        const val elementType: String = "transformationChildCard"

        inline operator fun <reified TView : TransformationChildCard, reified TAttributes : TransformationChildCardAttributes> invoke() = TransformationChildCardFactory(TView::class.java, TAttributes::class.java)
    }
}
