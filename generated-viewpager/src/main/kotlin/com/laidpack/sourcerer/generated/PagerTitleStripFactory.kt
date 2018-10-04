package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerTitleStrip
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class PagerTitleStripFactory<TView : PagerTitleStrip, TAttributes : PagerTitleStripAttributes> : ViewGroupFactory<TView, TAttributes>() {
    override val elementName: String = "pagerTitleStrip"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = PagerTitleStrip(context)
}
