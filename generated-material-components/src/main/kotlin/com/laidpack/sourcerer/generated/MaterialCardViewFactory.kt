package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.google.android.material.card.MaterialCardView
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class MaterialCardViewFactory<TView : MaterialCardView, TAttributes : MaterialCardViewAttributes> : FrameLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "materialCardView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = MaterialCardView(context)
}
