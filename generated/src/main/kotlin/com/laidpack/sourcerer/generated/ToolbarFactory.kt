package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import java.lang.Class
import kotlin.String

open class ToolbarFactory<TView : Toolbar, TAttributes : ToolbarAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

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
                view.apply {
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
                        val localContentInsetStart = attributes.contentInsetStart ?: contentInsetStart
                        val localContentInsetEnd = attributes.contentInsetEnd ?: contentInsetEnd
                        if (contentInsetStart != localContentInsetStart || contentInsetEnd != localContentInsetEnd) {
                            setContentInsetsRelative(localContentInsetStart, localContentInsetEnd)
                        }
                    }
                    if (attributes.contentInsetLeft != null || attributes.contentInsetRight != null) {
                        val localContentInsetLeft = attributes.contentInsetLeft ?: contentInsetLeft
                        val localContentInsetRight = attributes.contentInsetRight ?: contentInsetRight
                        if (contentInsetLeft != localContentInsetLeft || contentInsetRight != localContentInsetRight) {
                            setContentInsetsAbsolute(localContentInsetLeft, localContentInsetRight)
                        }
                    }
                    attributes.popupTheme?.let {
                        if (popupTheme != it) {
                            popupTheme = it
                        }
                    }
                    attributes.navigationIcon?.let {
                        val localNavigationIcon = ContextCompat.getDrawable(context, it) as Drawable
                        if (navigationIcon != localNavigationIcon) {
                            navigationIcon = localNavigationIcon
                        }
                    }
                    attributes.navigationContentDescription?.let {
                        if (navigationContentDescription != it) {
                            navigationContentDescription = it
                        }
                    }
                    attributes.logo?.let {
                        val localLogo = ContextCompat.getDrawable(context, it) as Drawable
                        if (logo != localLogo) {
                            logo = localLogo
                        }
                    }
                    attributes.logoDescription?.let {
                        if (logoDescription != it) {
                            logoDescription = it
                        }
                    }
                    attributes.titleTextColor?.let {
                        setTitleTextColor(it)
                    }
                    attributes.subtitleTextColor?.let {
                        setSubtitleTextColor(it)
                    }
                    if (Build.VERSION.SDK_INT >= 24) {
                        attributes.titleMargin?.let {
                            if (titleMarginBottom != it) {
                                titleMarginBottom = it
                            }
                        }
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
        const val elementType: String = "toolbar"

        inline operator fun <reified TView : Toolbar, reified TAttributes : ToolbarAttributes> invoke() = ToolbarFactory(TView::class.java, TAttributes::class.java)
    }
}
