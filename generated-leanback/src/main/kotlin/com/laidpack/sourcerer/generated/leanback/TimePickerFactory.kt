package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.view.View
import androidx.leanback.widget.picker.TimePicker
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = TimePickerFactory.elementType,
        attributesClazz = TimePickerAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class TimePickerFactory<TView : TimePicker, TAttributes : TimePickerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : PickerFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = TimePicker(context, null)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is TimePicker) {
            view.apply {
                attributes.lbis24HourFormat?.let {
                    if (is24Hour != it) {
                        setIs24Hour(it)
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "leanback.timePicker"

        inline operator fun <reified TView : TimePicker, reified TAttributes : TimePickerAttributes> invoke() = TimePickerFactory(TView::class.java, TAttributes::class.java)
    }
}
