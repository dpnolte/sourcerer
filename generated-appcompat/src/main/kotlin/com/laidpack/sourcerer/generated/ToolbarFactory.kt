package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class ToolbarFactory<TView : Toolbar, TAttributes : ToolbarAttributes> : ViewGroupFactory<TView, TAttributes>() {
    override val elementName: String = "toolbar"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = Toolbar(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as Toolbar
        view.init {
            if (Build.VERSION.SDK_INT >= 21) {
                attributes.titleMarginStart?.let {
                    if (titleMarginStart != it) {
                        titleMarginStart = it
                    }
                }
                attributes.titleMarginEnd?.let {
                    if (titleMarginEnd != it) {
                        titleMarginEnd = it
                    }
                }
                attributes.titleMarginTop?.let {
                    if (titleMarginTop != it) {
                        titleMarginTop = it
                    }
                }
                attributes.titleMarginBottom?.let {
                    if (titleMarginBottom != it) {
                        titleMarginBottom = it
                    }
                }
                if (attributes.contentInsetStart != null || attributes.contentInsetEnd != null) {
                    val immutableContentInsetStart = attributes.contentInsetStart ?: contentInsetStart
                    val immutableContentInsetEnd = attributes.contentInsetEnd ?: contentInsetEnd
                    if (contentInsetStart != attributes.contentInsetStart || contentInsetEnd != attributes.contentInsetEnd) {
                        setContentInsetsRelative(immutableContentInsetStart, immutableContentInsetEnd)
                    }
                }
                if (attributes.contentInsetLeft != null || attributes.contentInsetRight != null) {
                    val immutableContentInsetLeft = attributes.contentInsetLeft ?: contentInsetLeft
                    val immutableContentInsetRight = attributes.contentInsetRight ?: contentInsetRight
                    if (contentInsetLeft != attributes.contentInsetLeft || contentInsetRight != attributes.contentInsetRight) {
                        setContentInsetsAbsolute(immutableContentInsetLeft, immutableContentInsetRight)
                    }
                }
                attributes.contentInsetStartWithNavigation?.let {
                    if (contentInsetStartWithNavigation != it) {
                        contentInsetStartWithNavigation = it
                    }
                }
                attributes.contentInsetEndWithActions?.let {
                    if (contentInsetEndWithActions != it) {
                        contentInsetEndWithActions = it
                    }
                }
                attributes.navigationIcon?.let {
                    val immutableNavigationIcon = ContextCompat.getDrawable(context, it) as Drawable
                    if (navigationIcon != immutableNavigationIcon) {
                        setNavigationIcon(immutableNavigationIcon)
                    }
                }
                attributes.navigationContentDescription?.let {
                    if (navigationContentDescription != it) {
                        setNavigationContentDescription(it)
                    }
                }
            }
        }
    }
}
