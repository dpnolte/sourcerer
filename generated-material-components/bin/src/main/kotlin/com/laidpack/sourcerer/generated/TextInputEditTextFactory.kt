package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import com.google.android.material.textfield.TextInputEditText
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class TextInputEditTextFactory<TView : TextInputEditText, TAttributes : TextInputEditTextAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : EditTextFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "textInputEditText"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = TextInputEditText(context)

    companion object {
        init {
            InflaterComponent.addFactory(TextInputEditTextFactory<TextInputEditText, TextInputEditTextAttributes>())
        }

        inline operator fun <reified TView : TextInputEditText, reified TAttributes : TextInputEditTextAttributes> invoke() = TextInputEditTextFactory(TView::class.java, TAttributes::class.java)
    }
}
