package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import com.google.android.material.transformation.TransformationChildCard
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class TransformationChildCardFactory<TView : TransformationChildCard, TAttributes : TransformationChildCardAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "transformationChildCard"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = TransformationChildCard(context)

    companion object {
        init {
            InflaterComponent.addFactory(TransformationChildCardFactory<TransformationChildCard, TransformationChildCardAttributes>())
        }

        inline operator fun <reified TView : TransformationChildCard, reified TAttributes : TransformationChildCardAttributes> invoke() = TransformationChildCardFactory(TView::class.java, TAttributes::class.java)
    }
}
