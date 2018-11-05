package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.Chronometer
import com.laidpack.generator.api.ViewElement
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = ChronometerFactory.elementType,
        attributesClazz = ChronometerAttributes::class
)
open class ChronometerFactory<TView : Chronometer, TAttributes : ChronometerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : TextViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = Chronometer(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is Chronometer) {
            view.apply {
                attributes.format?.let {
                    if (format != it) {
                        format = it
                    }
                }
                attributes.countDown?.let {
                    if (isCountDown != it) {
                        isCountDown = it
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "chronometer"

        inline operator fun <reified TView : Chronometer, reified TAttributes : ChronometerAttributes> invoke() = ChronometerFactory(TView::class.java, TAttributes::class.java)
    }
}
