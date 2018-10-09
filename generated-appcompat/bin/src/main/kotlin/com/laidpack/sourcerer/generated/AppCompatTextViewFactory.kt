package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class AppCompatTextViewFactory<TView : AppCompatTextView, TAttributes : AppCompatTextViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : TextViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "appCompatTextView"

    override fun createInstance(context: Context): View = AppCompatTextView(context)

    companion object {
        init {
            InflaterComponent.addFactory(AppCompatTextViewFactory<AppCompatTextView, AppCompatTextViewAttributes>())
        }

        inline operator fun <reified TView : AppCompatTextView, reified TAttributes : AppCompatTextViewAttributes> invoke() = AppCompatTextViewFactory(TView::class.java, TAttributes::class.java)
    }
}
