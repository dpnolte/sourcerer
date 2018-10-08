package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.SurfaceView
import android.view.View
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class SurfaceViewFactory<TView : SurfaceView, TAttributes : SurfaceViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "surfaceView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = SurfaceView(context)

    companion object {
        init {
            InflaterComponent.addFactory(SurfaceViewFactory<SurfaceView, SurfaceViewAttributes>())
        }

        inline operator fun <reified TView : SurfaceView, reified TAttributes : SurfaceViewAttributes> invoke() = SurfaceViewFactory(TView::class.java, TAttributes::class.java)
    }
}
