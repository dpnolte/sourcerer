package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.navigation.NavigationView
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class NavigationViewFactory<TView : NavigationView, TAttributes : NavigationViewAttributes> : FrameLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "navigationView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = NavigationView(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as NavigationView
        view.init {
            attributes.itemIconTint?.let {
                val immutableItemIconTint = ResourcesCompat.getColorStateList(context.resources, it, null)
                if (itemIconTintList != immutableItemIconTint) {
                    setItemIconTintList(immutableItemIconTint)
                }
            }
            attributes.itemTextColor?.let {
                val immutableItemTextColor = ResourcesCompat.getColorStateList(context.resources, it, null)
                if (itemTextColor != immutableItemTextColor) {
                    setItemTextColor(immutableItemTextColor)
                }
            }
            attributes.itemBackground?.let {
                val immutableItemBackground = ContextCompat.getDrawable(context, it) as Drawable
                if (itemBackground != immutableItemBackground) {
                    setItemBackground(immutableItemBackground)
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
