package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.AdapterViewAnimator
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class AdapterViewAnimatorFactory<TView : AdapterViewAnimator, TAttributes : AdapterViewAnimatorAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AdapterViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "adapterViewAnimator"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View {
        throw IllegalStateException("android.widget.AdapterViewAnimator is abstract and cannot be instantiated")
    }

    companion object {
        init {
            InflaterComponent.addFactory(AdapterViewAnimatorFactory<AdapterViewAnimator, AdapterViewAnimatorAttributes>())
        }

        inline operator fun <reified TView : AdapterViewAnimator, reified TAttributes : AdapterViewAnimatorAttributes> invoke() = AdapterViewAnimatorFactory(TView::class.java, TAttributes::class.java)
    }
}
