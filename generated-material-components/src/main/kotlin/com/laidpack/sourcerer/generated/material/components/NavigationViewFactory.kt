package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.navigation.NavigationView
import com.laidpack.sourcerer.generated.FrameLayoutFactory
import java.lang.Class
import kotlin.String

open class NavigationViewFactory<TView : NavigationView, TAttributes : NavigationViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = NavigationView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is NavigationView) {
            view.apply {
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
    }

    companion object {
        const val elementType: String = "navigationView"

        inline operator fun <reified TView : NavigationView, reified TAttributes : NavigationViewAttributes> invoke() = NavigationViewFactory(TView::class.java, TAttributes::class.java)
    }
}
