package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.Chronometer
import java.lang.Class
import kotlin.String

open class ChronometerFactory<TView : Chronometer, TAttributes : ChronometerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewFactory<TView, TAttributes>(instanceType, attributesType) {
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
                    format = it
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    attributes.countDown?.let {
                        if (isCountDown != it) {
                            isCountDown = it
                        }
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
