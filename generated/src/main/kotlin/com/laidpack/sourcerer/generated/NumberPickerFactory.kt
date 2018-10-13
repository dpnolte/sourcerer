package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.NumberPicker
import java.lang.Class
import kotlin.String

open class NumberPickerFactory<TView : NumberPicker, TAttributes : NumberPickerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = NumberPicker(context)

    companion object {
        const val elementType: String = "numberPicker"

        inline operator fun <reified TView : NumberPicker, reified TAttributes : NumberPickerAttributes> invoke() = NumberPickerFactory(TView::class.java, TAttributes::class.java)
    }
}
