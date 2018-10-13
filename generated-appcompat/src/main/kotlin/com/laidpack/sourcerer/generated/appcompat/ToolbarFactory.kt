package com.laidpack.sourcerer.generated.appcompat

import android.content.Context
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.laidpack.sourcerer.generated.ViewGroupFactory
import java.lang.Class
import kotlin.String

open class ToolbarFactory<TView : Toolbar, TAttributes : ToolbarAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = Toolbar(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is Toolbar) {
            view.apply {
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
                attributes.navigationContentDescription?.let {
                    if (navigationContentDescription != it) {
                        navigationContentDescription = it
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "appcompat.toolbar"

        inline operator fun <reified TView : Toolbar, reified TAttributes : ToolbarAttributes> invoke() = ToolbarFactory(TView::class.java, TAttributes::class.java)
    }
}
