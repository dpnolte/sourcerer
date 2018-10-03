package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterViewFlipper
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import com.laidpack.sourcerer.service.api.init
import kotlin.String

open class AdapterViewFlipperFactory<TView : AdapterViewFlipper, TAttributes : AdapterViewFlipperAttributes> : AdapterViewFactory<TView, TAttributes>() {
    override val elementName: String = "adapterViewFlipper"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = AdapterViewFlipper(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as AdapterViewFlipper
        view.init {
            if (Build.VERSION.SDK_INT >= 16) {
                attributes.flipInterval?.let {
                    if (flipInterval != it) {
                        flipInterval = it
                    }
                }
            }
        }
    }
}
