package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.NumberPicker
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class NumberPickerFactory<TView : NumberPicker, TAttributes : NumberPickerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "numberPicker"

    override fun createInstance(context: Context): View = NumberPicker(context)

    companion object {
        init {
            InflaterComponent.addFactory(NumberPickerFactory<NumberPicker, NumberPickerAttributes>())
        }

        inline operator fun <reified TView : NumberPicker, reified TAttributes : NumberPickerAttributes> invoke() = NumberPickerFactory(TView::class.java, TAttributes::class.java)
    }
}
