package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.Chronometer
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.String

open class ChronometerFactory<TView : Chronometer, TAttributes : ChronometerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : TextViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "chronometer"

    override fun createInstance(context: Context): View = Chronometer(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is Chronometer) {
            view.init {
                attributes.format?.let {
                    if (format != it) {
                        format = it
                    }
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
        init {
            InflaterComponent.addFactory(ChronometerFactory<Chronometer, ChronometerAttributes>())
        }

        inline operator fun <reified TView : Chronometer, reified TAttributes : ChronometerAttributes> invoke() = ChronometerFactory(TView::class.java, TAttributes::class.java)
    }
}
