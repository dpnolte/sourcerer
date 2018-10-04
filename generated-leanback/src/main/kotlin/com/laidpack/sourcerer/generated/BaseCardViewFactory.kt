package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.leanback.widget.BaseCardView
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class BaseCardViewFactory<TView : BaseCardView, TAttributes : BaseCardViewAttributes> : FrameLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "baseCardView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = BaseCardView(context)
}
