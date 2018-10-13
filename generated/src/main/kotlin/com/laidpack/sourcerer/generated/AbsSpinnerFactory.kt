package com.laidpack.sourcerer.generated

import android.widget.AbsSpinner
import java.lang.Class
import kotlin.String

open class AbsSpinnerFactory<TView : AbsSpinner, TAttributes : AbsSpinnerAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AdapterViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    companion object {
        const val elementType: String = "absSpinner"

        inline operator fun <reified TView : AbsSpinner, reified TAttributes : AbsSpinnerAttributes> invoke() = AbsSpinnerFactory(TView::class.java, TAttributes::class.java)
    }
}
