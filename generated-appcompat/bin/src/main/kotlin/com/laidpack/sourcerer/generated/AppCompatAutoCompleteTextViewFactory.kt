package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class AppCompatAutoCompleteTextViewFactory<TView : AppCompatAutoCompleteTextView, TAttributes : AppCompatAutoCompleteTextViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : EditTextFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "appCompatAutoCompleteTextView"

    override fun createInstance(context: Context): View = AppCompatAutoCompleteTextView(context)

    companion object {
        init {
            InflaterComponent.addFactory(AppCompatAutoCompleteTextViewFactory<AppCompatAutoCompleteTextView, AppCompatAutoCompleteTextViewAttributes>())
        }

        inline operator fun <reified TView : AppCompatAutoCompleteTextView, reified TAttributes : AppCompatAutoCompleteTextViewAttributes> invoke() = AppCompatAutoCompleteTextViewFactory(TView::class.java, TAttributes::class.java)
    }
}
