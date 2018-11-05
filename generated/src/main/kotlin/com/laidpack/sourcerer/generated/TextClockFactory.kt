package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.TextClock
import com.laidpack.generator.api.ViewElement
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = TextClockFactory.elementType,
        attributesClazz = TextClockAttributes::class
)
open class TextClockFactory<TView : TextClock, TAttributes : TextClockAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : TextViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = TextClock(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is TextClock) {
            view.apply {
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

    companion object {
        const val elementType: String = "textClock"

        inline operator fun <reified TView : TextClock, reified TAttributes : TextClockAttributes> invoke() = TextClockFactory(TView::class.java, TAttributes::class.java)
    }
}
