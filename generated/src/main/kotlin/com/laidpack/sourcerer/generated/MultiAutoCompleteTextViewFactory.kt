package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.MultiAutoCompleteTextView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class MultiAutoCompleteTextViewFactory<TView : MultiAutoCompleteTextView, TAttributes : MultiAutoCompleteTextViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : EditTextFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "multiAutoCompleteTextView"

    override fun createInstance(context: Context): View = MultiAutoCompleteTextView(context)

    companion object {
        init {
            InflaterComponent.addFactory(MultiAutoCompleteTextViewFactory<MultiAutoCompleteTextView, MultiAutoCompleteTextViewAttributes>())
        }

        inline operator fun <reified TView : MultiAutoCompleteTextView, reified TAttributes : MultiAutoCompleteTextViewAttributes> invoke() = MultiAutoCompleteTextViewFactory(TView::class.java, TAttributes::class.java)
    }
}
