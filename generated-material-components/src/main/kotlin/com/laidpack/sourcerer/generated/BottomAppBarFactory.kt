package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomappbar.BottomAppBar
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class BottomAppBarFactory<TView : BottomAppBar, TAttributes : BottomAppBarAttributes> : ViewGroupFactory<TView, TAttributes>() {
    override val elementName: String = "bottomAppBar"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = BottomAppBar(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
    }
}
