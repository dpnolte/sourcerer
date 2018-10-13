package com.laidpack.sourcerer.generated.appcompat

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView
import com.laidpack.sourcerer.generated.MultiAutoCompleteTextViewFactory
import java.lang.Class
import kotlin.String

open class AppCompatMultiAutoCompleteTextViewFactory<TView : AppCompatMultiAutoCompleteTextView, TAttributes : AppCompatMultiAutoCompleteTextViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : MultiAutoCompleteTextViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = AppCompatMultiAutoCompleteTextView(context)

    companion object {
        const val elementType: String = "appCompatMultiAutoCompleteTextView"

        inline operator fun <reified TView : AppCompatMultiAutoCompleteTextView, reified TAttributes : AppCompatMultiAutoCompleteTextViewAttributes> invoke() = AppCompatMultiAutoCompleteTextViewFactory(TView::class.java, TAttributes::class.java)
    }
}
