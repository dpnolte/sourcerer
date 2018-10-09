package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class AppCompatMultiAutoCompleteTextViewFactory<TView : AppCompatMultiAutoCompleteTextView, TAttributes : AppCompatMultiAutoCompleteTextViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : MultiAutoCompleteTextViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "appCompatMultiAutoCompleteTextView"

    override fun createInstance(context: Context): View = AppCompatMultiAutoCompleteTextView(context)

    companion object {
        init {
            InflaterComponent.addFactory(AppCompatMultiAutoCompleteTextViewFactory<AppCompatMultiAutoCompleteTextView, AppCompatMultiAutoCompleteTextViewAttributes>())
        }

        inline operator fun <reified TView : AppCompatMultiAutoCompleteTextView, reified TAttributes : AppCompatMultiAutoCompleteTextViewAttributes> invoke() = AppCompatMultiAutoCompleteTextViewFactory(TView::class.java, TAttributes::class.java)
    }
}
