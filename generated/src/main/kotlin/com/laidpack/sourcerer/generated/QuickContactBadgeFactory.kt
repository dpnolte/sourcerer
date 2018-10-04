package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.QuickContactBadge
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class QuickContactBadgeFactory<TView : QuickContactBadge, TAttributes : QuickContactBadgeAttributes> : ImageViewFactory<TView, TAttributes>() {
    override val elementName: String = "quickContactBadge"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = QuickContactBadge(context)
}
