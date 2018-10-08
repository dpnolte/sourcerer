package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.DatePicker
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.Int
import kotlin.String

open class DatePickerFactory<TView : DatePicker, TAttributes : DatePickerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "datePicker"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = DatePicker(context)

    override fun init(
        view: TView,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        view.init {
            attributes.firstDayOfWeek?.let {
                if (firstDayOfWeek != it) {
                    firstDayOfWeek = it
                }
            }
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(DatePickerFactory<DatePicker, DatePickerAttributes>())
        }

        inline operator fun <reified TView : DatePicker, reified TAttributes : DatePickerAttributes> invoke() = DatePickerFactory(TView::class.java, TAttributes::class.java)
    }
}
