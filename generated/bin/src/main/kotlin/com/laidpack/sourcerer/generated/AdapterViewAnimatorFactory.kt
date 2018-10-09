package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.AdapterViewAnimator
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class AdapterViewAnimatorFactory<TView : AdapterViewAnimator, TAttributes : AdapterViewAnimatorAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AdapterViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "adapterViewAnimator"

    override fun createInstance(context: Context): View {
        // // AdapterViewAnimator is abstract
        return super.createInstance(context)
    }

    companion object {
        init {
            InflaterComponent.addFactory(AdapterViewAnimatorFactory<AdapterViewAnimator, AdapterViewAnimatorAttributes>())
        }

        inline operator fun <reified TView : AdapterViewAnimator, reified TAttributes : AdapterViewAnimatorAttributes> invoke() = AdapterViewAnimatorFactory(TView::class.java, TAttributes::class.java)
    }
}
