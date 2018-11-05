package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.MultiAutoCompleteTextView
import com.laidpack.generator.api.ViewElement
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = MultiAutoCompleteTextViewFactory.elementType,
        attributesClazz = MultiAutoCompleteTextViewAttributes::class
)
open class MultiAutoCompleteTextViewFactory<TView : MultiAutoCompleteTextView, TAttributes : MultiAutoCompleteTextViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AutoCompleteTextViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = MultiAutoCompleteTextView(context)

    companion object {
        const val elementType: String = "multiAutoCompleteTextView"

        inline operator fun <reified TView : MultiAutoCompleteTextView, reified TAttributes : MultiAutoCompleteTextViewAttributes> invoke() = MultiAutoCompleteTextViewFactory(TView::class.java, TAttributes::class.java)
    }
}
