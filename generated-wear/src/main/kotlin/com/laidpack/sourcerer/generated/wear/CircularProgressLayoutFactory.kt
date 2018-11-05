package com.laidpack.sourcerer.generated.wear

import android.content.Context
import android.view.View
import androidx.wear.widget.CircularProgressLayout
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.FrameLayoutFactory
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = CircularProgressLayoutFactory.elementType,
        attributesClazz = CircularProgressLayoutAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class CircularProgressLayoutFactory<TView : CircularProgressLayout, TAttributes : CircularProgressLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = CircularProgressLayout(context)

    companion object {
        const val elementType: String = "circularProgressLayout"

        inline operator fun <reified TView : CircularProgressLayout, reified TAttributes : CircularProgressLayoutAttributes> invoke() = CircularProgressLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
