package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.view.View
import com.google.android.material.textfield.TextInputEditText
import com.laidpack.sourcerer.generated.appcompat.AppCompatEditTextFactory
import java.lang.Class
import kotlin.String

open class TextInputEditTextFactory<TView : TextInputEditText, TAttributes : TextInputEditTextAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AppCompatEditTextFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = TextInputEditText(context)

    companion object {
        const val elementType: String = "textInputEditText"

        inline operator fun <reified TView : TextInputEditText, reified TAttributes : TextInputEditTextAttributes> invoke() = TextInputEditTextFactory(TView::class.java, TAttributes::class.java)
    }
}
