package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.NumberPicker
import java.lang.Class
import kotlin.String

open class NumberPickerFactory<TView : NumberPicker, TAttributes : NumberPickerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = NumberPicker(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is NumberPicker) {
            view.apply {
                if (Build.VERSION.SDK_INT >= 16) {
                    attributes.internalMinHeight?.let {
                        if (minimumHeight != it) {
                            minimumHeight = it
                        }
                    }
                    attributes.internalMinWidth?.let {
                        if (minimumWidth != it) {
                            minimumWidth = it
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "numberPicker"

        inline operator fun <reified TView : NumberPicker, reified TAttributes : NumberPickerAttributes> invoke() = NumberPickerFactory(TView::class.java, TAttributes::class.java)
    }
}
