package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.AdapterViewFlipper
import com.laidpack.generator.api.ViewGroupElement
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = AdapterViewFlipperFactory.elementType,
        attributesClazz = AdapterViewFlipperAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class AdapterViewFlipperFactory<TView : AdapterViewFlipper, TAttributes : AdapterViewFlipperAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AdapterViewAnimatorFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = AdapterViewFlipper(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is AdapterViewFlipper) {
            view.apply {
                attributes.autoStart?.let {
                    if (isAutoStart != it) {
                        isAutoStart = it
                    }
                }
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
        const val elementType: String = "adapterViewFlipper"

        inline operator fun <reified TView : AdapterViewFlipper, reified TAttributes : AdapterViewFlipperAttributes> invoke() = AdapterViewFlipperFactory(TView::class.java, TAttributes::class.java)
    }
}
