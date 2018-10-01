package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class ScrollViewFactory<TView : ScrollView, TAttributes : ScrollViewAttributes> : FrameLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "scrollView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = ScrollView(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as ScrollView
        view.init {
            attributes.fillViewport?.let {
                if (isFillViewport != it) {
                    isFillViewport = it
                }
            }
        }
    }
}
