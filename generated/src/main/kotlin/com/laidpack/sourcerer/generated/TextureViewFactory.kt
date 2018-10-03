package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class TextureViewFactory<TView : TextureView, TAttributes : TextureViewAttributes> : ViewFactory<TView, TAttributes>() {
    override val elementName: String = "textureView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = TextureView(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
    }
}
