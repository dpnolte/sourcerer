package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.ZoomControls
import java.lang.Class
import kotlin.String

open class ZoomControlsFactory<TView : ZoomControls, TAttributes : ZoomControlsAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = ZoomControls(context)

    companion object {
        const val elementType: String = "zoomControls"

        inline operator fun <reified TView : ZoomControls, reified TAttributes : ZoomControlsAttributes> invoke() = ZoomControlsFactory(TView::class.java, TAttributes::class.java)
    }
}
