package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.view.View
import com.google.android.material.appbar.AppBarLayout
import com.laidpack.sourcerer.generated.LinearLayoutFactory
import java.lang.Class
import kotlin.String

open class AppBarLayoutFactory<TView : AppBarLayout, TAttributes : AppBarLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = AppBarLayout(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is AppBarLayout) {
            view.apply {
                attributes.elevation?.let {
                    val localElevation = it.toFloat()
                    if (elevation != localElevation) {
                        targetElevation = localElevation
                    }
                }
                attributes.liftOnScroll?.let {
                    if (isLiftOnScroll != it) {
                        isLiftOnScroll = it
                    }
                }
                attributes.android_keyboardNavigationCluster?.let {
                    if (isKeyboardNavigationCluster != it) {
                        isKeyboardNavigationCluster = it
                    }
                }
                attributes.android_touchscreenBlocksFocus?.let {
                    if (touchscreenBlocksFocus != it) {
                        touchscreenBlocksFocus = it
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "appBarLayout"

        inline operator fun <reified TView : AppBarLayout, reified TAttributes : AppBarLayoutAttributes> invoke() = AppBarLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
