package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.EditText
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class EditTextFactory<TView : EditText, TAttributes : EditTextAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : TextViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "editText"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = EditText(context)

    companion object {
        init {
            InflaterComponent.addFactory(EditTextFactory<EditText, EditTextAttributes>())
        }

        inline operator fun <reified TView : EditText, reified TAttributes : EditTextAttributes> invoke() = EditTextFactory(TView::class.java, TAttributes::class.java)
    }
}
