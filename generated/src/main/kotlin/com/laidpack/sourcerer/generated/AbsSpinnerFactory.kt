package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AbsSpinner
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class AbsSpinnerFactory<TView : AbsSpinner, TAttributes : AbsSpinnerAttributes> : AdapterViewFactory<TView, TAttributes>() {
    override val elementName: String = "absSpinner"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View {
        throw IllegalStateException("android.widget.AbsSpinner is abstract and cannot be instantiated")
    }

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
    }
}
