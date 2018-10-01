package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class DatePickerFactory<TView : DatePicker, TAttributes : DatePickerAttributes> : FrameLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "datePicker"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = DatePicker(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as DatePicker
        view.init {
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
