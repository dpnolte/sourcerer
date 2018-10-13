package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.view.View
import androidx.leanback.widget.picker.TimePicker
import com.laidpack.sourcerer.generated.FrameLayoutFactory
import java.lang.Class
import kotlin.String

open class TimePickerFactory<TView : TimePicker, TAttributes : TimePickerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = TimePicker(context, null)

    companion object {
        const val elementType: String = "leanback.timePicker"

        inline operator fun <reified TView : TimePicker, reified TAttributes : TimePickerAttributes> invoke() = TimePickerFactory(TView::class.java, TAttributes::class.java)
    }
}
