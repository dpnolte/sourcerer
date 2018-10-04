package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageSwitcher
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class ImageSwitcherFactory<TView : ImageSwitcher, TAttributes : ImageSwitcherAttributes> : ViewSwitcherFactory<TView, TAttributes>() {
    override val elementName: String = "imageSwitcher"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = ImageSwitcher(context)
}
