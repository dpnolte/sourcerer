package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.EditText
import java.lang.Class
import kotlin.String

open class EditTextFactory<TView : EditText, TAttributes : EditTextAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = EditText(context)

    companion object {
        const val elementType: String = "editText"

        inline operator fun <reified TView : EditText, reified TAttributes : EditTextAttributes> invoke() = EditTextFactory(TView::class.java, TAttributes::class.java)
    }
}
