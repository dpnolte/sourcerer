package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.SurfaceView
import android.view.View
import com.laidpack.generator.api.ViewElement
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = SurfaceViewFactory.elementType,
        attributesClazz = SurfaceViewAttributes::class
)
open class SurfaceViewFactory<TView : SurfaceView, TAttributes : SurfaceViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = SurfaceView(context)

    companion object {
        const val elementType: String = "surfaceView"

        inline operator fun <reified TView : SurfaceView, reified TAttributes : SurfaceViewAttributes> invoke() = SurfaceViewFactory(TView::class.java, TAttributes::class.java)
    }
}
