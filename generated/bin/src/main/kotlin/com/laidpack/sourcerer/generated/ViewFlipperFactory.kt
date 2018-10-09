package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.ViewFlipper
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class ViewFlipperFactory<TView : ViewFlipper, TAttributes : ViewFlipperAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewAnimatorFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "viewFlipper"

    override fun createInstance(context: Context): View = ViewFlipper(context)

    companion object {
        init {
            InflaterComponent.addFactory(ViewFlipperFactory<ViewFlipper, ViewFlipperAttributes>())
        }

        inline operator fun <reified TView : ViewFlipper, reified TAttributes : ViewFlipperAttributes> invoke() = ViewFlipperFactory(TView::class.java, TAttributes::class.java)
    }
}
