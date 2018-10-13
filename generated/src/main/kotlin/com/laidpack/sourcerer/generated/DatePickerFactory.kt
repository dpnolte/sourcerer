package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.DatePicker
import java.lang.Class
import kotlin.String

open class DatePickerFactory<TView : DatePicker, TAttributes : DatePickerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = DatePicker(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is DatePicker) {
            view.apply {
                if (Build.VERSION.SDK_INT >= 21) {
                    attributes.firstDayOfWeek?.let {
                        if (firstDayOfWeek != it) {
                            firstDayOfWeek = it
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "datePicker"

        inline operator fun <reified TView : DatePicker, reified TAttributes : DatePickerAttributes> invoke() = DatePickerFactory(TView::class.java, TAttributes::class.java)
    }
}
