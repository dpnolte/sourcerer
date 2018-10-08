package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class AppCompatEditTextFactory<TView : AppCompatEditText, TAttributes : AppCompatEditTextAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : EditTextFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "appCompatEditText"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = AppCompatEditText(context)

    companion object {
        init {
            InflaterComponent.addFactory(AppCompatEditTextFactory<AppCompatEditText, AppCompatEditTextAttributes>())
        }

        inline operator fun <reified TView : AppCompatEditText, reified TAttributes : AppCompatEditTextAttributes> invoke() = AppCompatEditTextFactory(TView::class.java, TAttributes::class.java)
    }
}
