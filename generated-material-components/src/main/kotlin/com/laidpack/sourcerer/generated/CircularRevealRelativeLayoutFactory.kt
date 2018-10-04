package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.google.android.material.circularreveal.CircularRevealRelativeLayout
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class CircularRevealRelativeLayoutFactory<TView : CircularRevealRelativeLayout, TAttributes : CircularRevealRelativeLayoutAttributes> : RelativeLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "circularRevealRelativeLayout"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = CircularRevealRelativeLayout(context)
}
