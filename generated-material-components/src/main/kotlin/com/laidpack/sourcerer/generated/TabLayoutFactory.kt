package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.tabs.TabLayout
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class TabLayoutFactory<TView : TabLayout, TAttributes : TabLayoutAttributes> : HorizontalScrollViewFactory<TView, TAttributes>() {
    override val elementName: String = "tabLayout"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = TabLayout(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as TabLayout
        view.init {
            attributes.tabIndicatorHeight?.let {
                if (height != it) {
                    setSelectedTabIndicatorHeight(it)
                }
            }
            attributes.tabIndicatorGravity?.let {
                if (tabIndicatorGravity != it.value) {
                    setSelectedTabIndicatorGravity(it.value)
                }
            }
            attributes.tabIndicatorFullWidth?.let {
                if (isTabIndicatorFullWidth != it) {
                    isTabIndicatorFullWidth = it
                }
            }
            attributes.tabMode?.let {
                if (tabMode != it.value) {
                    tabMode = it.value
                }
            }
            attributes.tabGravity?.let {
                if (tabGravity != it.value) {
                    tabGravity = it.value
                }
            }
            attributes.tabInlineLabel?.let {
                if (isInlineLabel != it) {
                    isInlineLabel = it
                }
            }
            attributes.tabRippleColor?.let {
                val immutableTabRippleColor = ResourcesCompat.getColorStateList(context.resources, it, null)
                if (tabRippleColor != immutableTabRippleColor) {
                    setTabRippleColor(immutableTabRippleColor)
                }
            }
        }
    }
}
