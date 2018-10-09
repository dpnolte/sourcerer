package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.String

open class BottomNavigationViewFactory<TView : BottomNavigationView, TAttributes : BottomNavigationViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "bottomNavigationView"

    override fun createInstance(context: Context): View = BottomNavigationView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is BottomNavigationView) {
            view.init {
                attributes.labelVisibilityMode?.let {
                    if (labelVisibilityMode != it.value) {
                        labelVisibilityMode = it.value
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

    companion object {
        init {
            InflaterComponent.addFactory(BottomNavigationViewFactory<BottomNavigationView, BottomNavigationViewAttributes>())
        }

        inline operator fun <reified TView : BottomNavigationView, reified TAttributes : BottomNavigationViewAttributes> invoke() = BottomNavigationViewFactory(TView::class.java, TAttributes::class.java)
    }
}
