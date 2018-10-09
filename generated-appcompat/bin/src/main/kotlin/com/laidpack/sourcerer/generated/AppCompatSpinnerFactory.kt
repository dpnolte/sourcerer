package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatSpinner
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class AppCompatSpinnerFactory<TView : AppCompatSpinner, TAttributes : AppCompatSpinnerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AdapterViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "appCompatSpinner"

    override fun createInstance(context: Context): View = AppCompatSpinner(context)

    companion object {
        init {
            InflaterComponent.addFactory(AppCompatSpinnerFactory<AppCompatSpinner, AppCompatSpinnerAttributes>())
        }

        inline operator fun <reified TView : AppCompatSpinner, reified TAttributes : AppCompatSpinnerAttributes> invoke() = AppCompatSpinnerFactory(TView::class.java, TAttributes::class.java)
    }
}
