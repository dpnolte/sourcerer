package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.google.android.material.circularreveal.CircularRevealLinearLayout
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class CircularRevealLinearLayoutFactory<TView : CircularRevealLinearLayout, TAttributes : CircularRevealLinearLayoutAttributes> : LinearLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "circularRevealLinearLayout"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = CircularRevealLinearLayout(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
    }
}
