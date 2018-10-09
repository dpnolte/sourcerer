package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.TextClock
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.String

open class TextClockFactory<TView : TextClock, TAttributes : TextClockAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : TextViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "textClock"

    override fun createInstance(context: Context): View {
        if (Build.VERSION.SDK_INT >= 17) {
            return TextClock(context)
        }
        else {
            return super.createInstance(context)
        }
    }

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (Build.VERSION.SDK_INT >= 17) {
            if (view is TextClock) {
                view.init {
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

    companion object {
        init {
            InflaterComponent.addFactory(TextClockFactory<TextClock, TextClockAttributes>())
        }

        inline operator fun <reified TView : TextClock, reified TAttributes : TextClockAttributes> invoke() = TextClockFactory(TView::class.java, TAttributes::class.java)
    }
}
