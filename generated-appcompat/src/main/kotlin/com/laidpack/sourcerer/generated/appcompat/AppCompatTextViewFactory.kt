package com.laidpack.sourcerer.generated.appcompat

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.laidpack.generator.api.ViewElement
import com.laidpack.sourcerer.generated.TextViewFactory
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = AppCompatTextViewFactory.elementType,
        attributesClazz = AppCompatTextViewAttributes::class
)
open class AppCompatTextViewFactory<TView : AppCompatTextView, TAttributes : AppCompatTextViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : TextViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = AppCompatTextView(context)

    companion object {
        const val elementType: String = "appCompatTextView"

        inline operator fun <reified TView : AppCompatTextView, reified TAttributes : AppCompatTextViewAttributes> invoke() = AppCompatTextViewFactory(TView::class.java, TAttributes::class.java)
    }
}
