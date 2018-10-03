package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.Gallery
import kotlin.String

open class GalleryLayoutParamsFactory<TLayoutParams : Gallery.LayoutParams, TAttributes : GalleryLayoutParamsAttributes> : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = Gallery.LayoutParams(Gallery.LayoutParams.MATCH_PARENT, Gallery.LayoutParams.MATCH_PARENT)

    override fun init(
        lp: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(lp, context, attributes)
    }
}
