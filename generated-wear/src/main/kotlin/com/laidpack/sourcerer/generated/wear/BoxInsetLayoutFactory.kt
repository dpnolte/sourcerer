package com.laidpack.sourcerer.generated.wear

import android.content.Context
import android.view.View
import androidx.wear.widget.BoxInsetLayout
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.ViewGroupFactory
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = BoxInsetLayoutFactory.elementType,
        attributesClazz = BoxInsetLayoutAttributes::class,
        layoutParamAttributesClazz = BoxInsetLayoutLayoutParamsAttributes::class
)
open class BoxInsetLayoutFactory<TView : BoxInsetLayout, TAttributes : BoxInsetLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = BoxInsetLayout(context)

    companion object {
        const val elementType: String = "boxInsetLayout"

        inline operator fun <reified TView : BoxInsetLayout, reified TAttributes : BoxInsetLayoutAttributes> invoke() = BoxInsetLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
