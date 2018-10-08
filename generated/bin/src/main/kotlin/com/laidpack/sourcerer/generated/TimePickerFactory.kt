package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.TimePicker
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class TimePickerFactory<TView : TimePicker, TAttributes : TimePickerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "timePicker"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = TimePicker(context)

    companion object {
        init {
            InflaterComponent.addFactory(TimePickerFactory<TimePicker, TimePickerAttributes>())
        }

        inline operator fun <reified TView : TimePicker, reified TAttributes : TimePickerAttributes> invoke() = TimePickerFactory(TView::class.java, TAttributes::class.java)
    }
}
