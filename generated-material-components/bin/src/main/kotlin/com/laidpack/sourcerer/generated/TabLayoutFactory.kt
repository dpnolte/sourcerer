package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.tabs.TabLayout
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.Int
import kotlin.String

open class TabLayoutFactory<TView : TabLayout, TAttributes : TabLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : HorizontalScrollViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "tabLayout"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = TabLayout(context)

    override fun init(
        view: TView,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
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
                    tabRippleColor = immutableTabRippleColor
                }
            }
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(TabLayoutFactory<TabLayout, TabLayoutAttributes>())
        }

        inline operator fun <reified TView : TabLayout, reified TAttributes : TabLayoutAttributes> invoke() = TabLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
