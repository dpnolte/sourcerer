package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.wear.widget.CircularProgressLayout
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class CircularProgressLayoutFactory<TView : CircularProgressLayout, TAttributes : CircularProgressLayoutAttributes> : FrameLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "circularProgressLayout"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = CircularProgressLayout(context)
}
