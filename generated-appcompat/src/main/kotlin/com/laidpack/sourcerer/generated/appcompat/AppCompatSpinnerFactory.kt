package com.laidpack.sourcerer.generated.appcompat

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatSpinner
import com.laidpack.sourcerer.generated.AdapterViewFactory
import java.lang.Class
import kotlin.String

open class AppCompatSpinnerFactory<TView : AppCompatSpinner, TAttributes : AppCompatSpinnerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AdapterViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = AppCompatSpinner(context)

    companion object {
        const val elementType: String = "appCompatSpinner"

        inline operator fun <reified TView : AppCompatSpinner, reified TAttributes : AppCompatSpinnerAttributes> invoke() = AppCompatSpinnerFactory(TView::class.java, TAttributes::class.java)
    }
}
