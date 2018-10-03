package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class AdapterViewFactory<TView : AdapterView<*>, TAttributes : AdapterViewAttributes> : ViewGroupFactory<TView, TAttributes>() {
    override val elementName: String = "adapterView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View {
        throw IllegalStateException("android.widget.AdapterView is abstract and cannot be instantiated")
    }

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
    }
}
