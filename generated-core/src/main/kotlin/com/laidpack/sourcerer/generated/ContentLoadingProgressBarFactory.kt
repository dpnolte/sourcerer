package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class ContentLoadingProgressBarFactory<TView : ContentLoadingProgressBar, TAttributes : ContentLoadingProgressBarAttributes> : ProgressBarFactory<TView, TAttributes>() {
    override val elementName: String = "contentLoadingProgressBar"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = ContentLoadingProgressBar(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
    }
}
