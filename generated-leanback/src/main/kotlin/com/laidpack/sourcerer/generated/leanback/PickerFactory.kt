package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.view.View
import androidx.leanback.widget.picker.Picker
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.FrameLayoutFactory
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = PickerFactory.elementType,
        attributesClazz = PickerAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class PickerFactory<TView : Picker, TAttributes : PickerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = Picker(context, null, 0)

    companion object {
        const val elementType: String = "picker"

        inline operator fun <reified TView : Picker, reified TAttributes : PickerAttributes> invoke() = PickerFactory(TView::class.java, TAttributes::class.java)
    }
}
