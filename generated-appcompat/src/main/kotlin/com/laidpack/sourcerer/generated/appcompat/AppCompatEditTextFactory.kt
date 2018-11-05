package com.laidpack.sourcerer.generated.appcompat

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import com.laidpack.generator.api.ViewElement
import com.laidpack.sourcerer.generated.EditTextFactory
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = AppCompatEditTextFactory.elementType,
        attributesClazz = AppCompatEditTextAttributes::class
)
open class AppCompatEditTextFactory<TView : AppCompatEditText, TAttributes : AppCompatEditTextAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : EditTextFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = AppCompatEditText(context)

    companion object {
        const val elementType: String = "appCompatEditText"

        inline operator fun <reified TView : AppCompatEditText, reified TAttributes : AppCompatEditTextAttributes> invoke() = AppCompatEditTextFactory(TView::class.java, TAttributes::class.java)
    }
}
