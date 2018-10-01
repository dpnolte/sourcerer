package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.leanback.widget.picker.TimePicker
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class TimePickerFactory<TView : TimePicker, TAttributes : TimePickerAttributes> : FrameLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "timePicker"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = TimePicker(context, null)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
    }
}
