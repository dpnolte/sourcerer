package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.AdapterViewFlipper
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.String

open class AdapterViewFlipperFactory<TView : AdapterViewFlipper, TAttributes : AdapterViewFlipperAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AdapterViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "adapterViewFlipper"

    override fun createInstance(context: Context): View = AdapterViewFlipper(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is AdapterViewFlipper) {
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

    companion object {
        init {
            InflaterComponent.addFactory(AdapterViewFlipperFactory<AdapterViewFlipper, AdapterViewFlipperAttributes>())
        }

        inline operator fun <reified TView : AdapterViewFlipper, reified TAttributes : AdapterViewFlipperAttributes> invoke() = AdapterViewFlipperFactory(TView::class.java, TAttributes::class.java)
    }
}
