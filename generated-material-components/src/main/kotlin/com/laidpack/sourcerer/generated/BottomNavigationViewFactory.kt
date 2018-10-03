package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import com.laidpack.sourcerer.service.api.init
import kotlin.String

open class BottomNavigationViewFactory<TView : BottomNavigationView, TAttributes : BottomNavigationViewAttributes> : FrameLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "bottomNavigationView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = BottomNavigationView(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as BottomNavigationView
        view.init {
            attributes.labelVisibilityMode?.let {
                if (labelVisibilityMode != it.value) {
                    labelVisibilityMode = it.value
                }
            }
            attributes.itemBackground?.let {
                if (itemBackgroundResource != it) {
                    itemBackgroundResource = it
                }
            }
            attributes.itemIconSize?.let {
                if (itemIconSize != it) {
                    itemIconSize = it
                }
            }
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
        }
    }
}
