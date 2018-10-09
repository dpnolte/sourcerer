package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.String

open class ToolbarFactory<TView : Toolbar, TAttributes : ToolbarAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "toolbar"

    override fun createInstance(context: Context): View {
        if (Build.VERSION.SDK_INT >= 21) {
            return Toolbar(context)
        }
        else {
            return super.createInstance(context)
        }
    }

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (Build.VERSION.SDK_INT >= 21) {
            if (view is Toolbar) {
                view.init {
                    attributes.title?.let {
                        if (title != it) {
                            title = it
                        }
                    }
                    attributes.subtitle?.let {
                        if (subtitle != it) {
                            subtitle = it
                        }
                    }
                    if (attributes.contentInsetStart != null || attributes.contentInsetEnd != null) {
                        val immutableContentInsetStart = attributes.contentInsetStart ?: contentInsetStart
                        val immutableContentInsetEnd = attributes.contentInsetEnd ?: contentInsetEnd
                        if (contentInsetStart != immutableContentInsetStart || contentInsetEnd != immutableContentInsetEnd) {
                            setContentInsetsRelative(immutableContentInsetStart, immutableContentInsetEnd)
                        }
                    }
                    if (attributes.contentInsetLeft != null || attributes.contentInsetRight != null) {
                        val immutableContentInsetLeft = attributes.contentInsetLeft ?: contentInsetLeft
                        val immutableContentInsetRight = attributes.contentInsetRight ?: contentInsetRight
                        if (contentInsetLeft != immutableContentInsetLeft || contentInsetRight != immutableContentInsetRight) {
                            setContentInsetsAbsolute(immutableContentInsetLeft, immutableContentInsetRight)
                        }
                    }
                    attributes.popupTheme?.let {
                        if (popupTheme != it) {
                            popupTheme = it
                        }
                    }
                    attributes.navigationIcon?.let {
                        val immutableNavigationIcon = ContextCompat.getDrawable(context, it) as Drawable
                        if (navigationIcon != immutableNavigationIcon) {
                            navigationIcon = immutableNavigationIcon
                        }
                    }
                    attributes.navigationContentDescription?.let {
                        if (navigationContentDescription != it) {
                            navigationContentDescription = it
                        }
                    }
                    attributes.logo?.let {
                        val immutableLogo = ContextCompat.getDrawable(context, it) as Drawable
                        if (logo != immutableLogo) {
                            logo = immutableLogo
                        }
                    }
                    attributes.logoDescription?.let {
                        if (logoDescription != it) {
                            logoDescription = it
                        }
                    }
                    if (Build.VERSION.SDK_INT >= 24) {
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
                    }
                }
            }
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(ToolbarFactory<Toolbar, ToolbarAttributes>())
        }

        inline operator fun <reified TView : Toolbar, reified TAttributes : ToolbarAttributes> invoke() = ToolbarFactory(TView::class.java, TAttributes::class.java)
    }
}
