package com.laidpack.sourcerer.generated

import android.widget.AdapterViewAnimator
import java.lang.Class
import kotlin.String

open class AdapterViewAnimatorFactory<TView : AdapterViewAnimator, TAttributes : AdapterViewAnimatorAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AdapterViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    companion object {
        const val elementType: String = "adapterViewAnimator"

        inline operator fun <reified TView : AdapterViewAnimator, reified TAttributes : AdapterViewAnimatorAttributes> invoke() = AdapterViewAnimatorFactory(TView::class.java, TAttributes::class.java)
    }
}
