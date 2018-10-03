package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import com.laidpack.sourcerer.service.api.init
import kotlin.String

open class FrameLayoutFactory<TView : FrameLayout, TAttributes : FrameLayoutAttributes> : ViewGroupFactory<TView, TAttributes>() {
    override val elementName: String = "frameLayout"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = FrameLayout(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as FrameLayout
        view.init {
            attributes.measureAllChildren?.let {
                if (measureAllChildren != it) {
                    measureAllChildren = it
                }
            }
        }
    }
}
