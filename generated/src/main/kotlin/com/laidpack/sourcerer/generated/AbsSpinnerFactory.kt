package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.AbsSpinner
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class AbsSpinnerFactory<TView : AbsSpinner, TAttributes : AbsSpinnerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AdapterViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "absSpinner"

    override fun createInstance(context: Context): View {
        // // AbsSpinner is abstract
        return super.createInstance(context)
    }

    companion object {
        init {
            InflaterComponent.addFactory(AbsSpinnerFactory<AbsSpinner, AbsSpinnerAttributes>())
        }

        inline operator fun <reified TView : AbsSpinner, reified TAttributes : AbsSpinnerAttributes> invoke() = AbsSpinnerFactory(TView::class.java, TAttributes::class.java)
    }
}
