package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.navigation.NavigationView
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.FrameLayoutFactory
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = NavigationViewFactory.elementType,
        attributesClazz = NavigationViewAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
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
                    val localItemIconTint = ColorStateList.valueOf(it)
                    if (itemIconTintList != localItemIconTint) {
                        itemIconTintList = localItemIconTint
                    }
                }
                attributes.itemTextColor?.let {
                    val localItemTextColor = ColorStateList.valueOf(it)
                    if (itemTextColor != localItemTextColor) {
                        itemTextColor = localItemTextColor
                    }
                }
                attributes.itemBackground?.let {
                    val localItemBackground = ContextCompat.getDrawable(context, it) as Drawable
                    if (itemBackground != localItemBackground) {
                        itemBackground = localItemBackground
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
