package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class SurfaceViewFactory<TView : SurfaceView, TAttributes : SurfaceViewAttributes> : ViewFactory<TView, TAttributes>() {
    override val elementName: String = "surfaceView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = SurfaceView(context)
}
