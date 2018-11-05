package com.laidpack.sourcerer.generated.appcompat

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatSpinner
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.SpinnerFactory
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = AppCompatSpinnerFactory.elementType,
        attributesClazz = AppCompatSpinnerAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class AppCompatSpinnerFactory<TView : AppCompatSpinner, TAttributes : AppCompatSpinnerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : SpinnerFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = AppCompatSpinner(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is AppCompatSpinner) {
            view.apply {
                attributes.Spinner_android_dropDownWidth?.let {
                    if (dropDownWidth != it) {
                        dropDownWidth = it
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "appCompatSpinner"

        inline operator fun <reified TView : AppCompatSpinner, reified TAttributes : AppCompatSpinnerAttributes> invoke() = AppCompatSpinnerFactory(TView::class.java, TAttributes::class.java)
    }
}
