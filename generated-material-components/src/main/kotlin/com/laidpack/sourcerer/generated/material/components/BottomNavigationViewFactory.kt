package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.laidpack.sourcerer.generated.FrameLayoutFactory
import java.lang.Class
import kotlin.String

open class BottomNavigationViewFactory<TView : BottomNavigationView, TAttributes : BottomNavigationViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = BottomNavigationView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is BottomNavigationView) {
            view.apply {
                attributes.labelVisibilityMode?.let {
                    if (labelVisibilityMode != it.value) {
                        labelVisibilityMode = it.value
                    }
                }
                attributes.itemBackground?.let {
                    itemBackgroundResource = it
                }
                attributes.itemIconSize?.let {
                    if (itemIconSize != it) {
                        itemIconSize = it
                    }
                }
                attributes.itemIconTint?.let {
                    val localItemIconTint = ResourcesCompat.getColorStateList(context.resources, it, null)
                    if (itemIconTintList != localItemIconTint) {
                        itemIconTintList = localItemIconTint
                    }
                }
                attributes.itemTextColor?.let {
                    val localItemTextColor = ResourcesCompat.getColorStateList(context.resources, it, null)
                    if (itemTextColor != localItemTextColor) {
                        itemTextColor = localItemTextColor
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "bottomNavigationView"

        inline operator fun <reified TView : BottomNavigationView, reified TAttributes : BottomNavigationViewAttributes> invoke() = BottomNavigationViewFactory(TView::class.java, TAttributes::class.java)
    }
}
