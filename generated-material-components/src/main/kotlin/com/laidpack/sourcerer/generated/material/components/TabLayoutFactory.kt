package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import com.google.android.material.tabs.TabLayout
import com.laidpack.sourcerer.generated.HorizontalScrollViewFactory
import java.lang.Class
import kotlin.String

open class TabLayoutFactory<TView : TabLayout, TAttributes : TabLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : HorizontalScrollViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = TabLayout(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is TabLayout) {
            view.apply {
                attributes.tabIndicatorColor?.let {
                    setSelectedTabIndicatorColor(it)
                }
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
                    val localTabRippleColor = ColorStateList.valueOf(it)
                    if (tabRippleColor != localTabRippleColor) {
                        tabRippleColor = localTabRippleColor
                    }
                }
                attributes.tabUnboundedRipple?.let {
                    setUnboundedRipple(it)
                }
            }
        }
    }

    companion object {
        const val elementType: String = "tabLayout"

        inline operator fun <reified TView : TabLayout, reified TAttributes : TabLayoutAttributes> invoke() = TabLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
