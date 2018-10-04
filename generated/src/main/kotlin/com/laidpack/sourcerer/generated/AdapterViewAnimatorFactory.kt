package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterViewAnimator
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class AdapterViewAnimatorFactory<TView : AdapterViewAnimator, TAttributes : AdapterViewAnimatorAttributes> : AdapterViewFactory<TView, TAttributes>() {
    override val elementName: String = "adapterViewAnimator"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View {
        throw IllegalStateException("android.widget.AdapterViewAnimator is abstract and cannot be instantiated")
    }
}
