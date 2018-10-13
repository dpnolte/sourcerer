package com.laidpack.sourcerer.generated.appcompat

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import com.laidpack.sourcerer.generated.EditTextFactory
import java.lang.Class
import kotlin.String

open class AppCompatAutoCompleteTextViewFactory<TView : AppCompatAutoCompleteTextView, TAttributes : AppCompatAutoCompleteTextViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : EditTextFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = AppCompatAutoCompleteTextView(context)

    companion object {
        const val elementType: String = "appCompatAutoCompleteTextView"

        inline operator fun <reified TView : AppCompatAutoCompleteTextView, reified TAttributes : AppCompatAutoCompleteTextViewAttributes> invoke() = AppCompatAutoCompleteTextViewFactory(TView::class.java, TAttributes::class.java)
    }
}
