package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.TimePicker
import com.laidpack.generator.api.ViewGroupElement
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = TimePickerFactory.elementType,
        attributesClazz = TimePickerAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class TimePickerFactory<TView : TimePicker, TAttributes : TimePickerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = TimePicker(context)

    companion object {
        const val elementType: String = "timePicker"

        inline operator fun <reified TView : TimePicker, reified TAttributes : TimePickerAttributes> invoke() = TimePickerFactory(TView::class.java, TAttributes::class.java)
    }
}
