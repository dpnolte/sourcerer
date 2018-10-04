package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerTabStrip
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class PagerTabStripFactory<TView : PagerTabStrip, TAttributes : PagerTabStripAttributes> : ViewGroupFactory<TView, TAttributes>() {
    override val elementName: String = "pagerTabStrip"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = PagerTabStrip(context)
}
