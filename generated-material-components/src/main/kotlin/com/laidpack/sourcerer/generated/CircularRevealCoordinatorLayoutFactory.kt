package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.google.android.material.circularreveal.coordinatorlayout.CircularRevealCoordinatorLayout
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class CircularRevealCoordinatorLayoutFactory<TView : CircularRevealCoordinatorLayout, TAttributes : CircularRevealCoordinatorLayoutAttributes> : ViewGroupFactory<TView, TAttributes>() {
    override val elementName: String = "circularRevealCoordinatorLayout"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = CircularRevealCoordinatorLayout(context)
}
