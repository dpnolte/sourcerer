package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.TextClock
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import com.laidpack.sourcerer.service.api.init
import kotlin.String

open class TextClockFactory<TView : TextClock, TAttributes : TextClockAttributes> : TextViewFactory<TView, TAttributes>() {
    override val elementName: String = "textClock"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = TextClock(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as TextClock
        view.init {
            if (Build.VERSION.SDK_INT >= 17) {
                attributes.format12Hour?.let {
                    if (format12Hour != it) {
                        format12Hour = it
                    }
                }
                attributes.format24Hour?.let {
                    if (format24Hour != it) {
                        format24Hour = it
                    }
                }
                attributes.timeZone?.let {
                    if (timeZone != it) {
                        timeZone = it
                    }
                }
            }
        }
    }
}
