package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.navigation.NavigationView
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.Int
import kotlin.String

open class NavigationViewFactory<TView : NavigationView, TAttributes : NavigationViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "navigationView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = NavigationView(context)

    override fun init(
        view: TView,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        view.init {
            attributes.itemIconTint?.let {
                val immutableItemIconTint = ResourcesCompat.getColorStateList(context.resources, it, null)
                if (itemIconTintList != immutableItemIconTint) {
                    itemIconTintList = immutableItemIconTint
                }
            }
            attributes.itemTextColor?.let {
                val immutableItemTextColor = ResourcesCompat.getColorStateList(context.resources, it, null)
                if (itemTextColor != immutableItemTextColor) {
                    itemTextColor = immutableItemTextColor
                }
            }
            attributes.itemHorizontalPadding?.let {
                if (itemHorizontalPadding != it) {
                    itemHorizontalPadding = it
                }
            }
            attributes.itemIconPadding?.let {
                if (itemIconPadding != it) {
                    itemIconPadding = it
                }
            }
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(NavigationViewFactory<NavigationView, NavigationViewAttributes>())
        }

        inline operator fun <reified TView : NavigationView, reified TAttributes : NavigationViewAttributes> invoke() = NavigationViewFactory(TView::class.java, TAttributes::class.java)
    }
}
