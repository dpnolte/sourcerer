package com.laidpack.sourcerer.generated.appcompat

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatCheckedTextView
import com.laidpack.sourcerer.generated.CheckedTextViewFactory
import java.lang.Class
import kotlin.String

open class AppCompatCheckedTextViewFactory<TView : AppCompatCheckedTextView, TAttributes : AppCompatCheckedTextViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : CheckedTextViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = AppCompatCheckedTextView(context)

    companion object {
        const val elementType: String = "appCompatCheckedTextView"

        inline operator fun <reified TView : AppCompatCheckedTextView, reified TAttributes : AppCompatCheckedTextViewAttributes> invoke() = AppCompatCheckedTextViewFactory(TView::class.java, TAttributes::class.java)
    }
}
