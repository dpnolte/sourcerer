package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatCheckedTextView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class AppCompatCheckedTextViewFactory<TView : AppCompatCheckedTextView, TAttributes : AppCompatCheckedTextViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : TextViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "appCompatCheckedTextView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = AppCompatCheckedTextView(context)

    companion object {
        init {
            InflaterComponent.addFactory(AppCompatCheckedTextViewFactory<AppCompatCheckedTextView, AppCompatCheckedTextViewAttributes>())
        }

        inline operator fun <reified TView : AppCompatCheckedTextView, reified TAttributes : AppCompatCheckedTextViewAttributes> invoke() = AppCompatCheckedTextViewFactory(TView::class.java, TAttributes::class.java)
    }
}
