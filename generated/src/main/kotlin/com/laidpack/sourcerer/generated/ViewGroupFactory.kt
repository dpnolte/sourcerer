package com.laidpack.sourcerer.generated

import android.animation.LayoutTransition
import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import java.lang.Class
import kotlin.String

open class ViewGroupFactory<TView : ViewGroup, TAttributes : ViewGroupAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is ViewGroup) {
            view.apply {
                attributes.addStatesFromChildren?.let {
                    if (addStatesFromChildren() != it) {
                        setAddStatesFromChildren(it)
                    }
                }
                attributes.animationCache?.let {
                    if (isEnabled != it) {
                        isAnimationCacheEnabled = it
                    }
                }
                attributes.persistentDrawingCache?.let {
                    val localPersistentDrawingCache = it.value
                    if (persistentDrawingCache != localPersistentDrawingCache) {
                        persistentDrawingCache = localPersistentDrawingCache
                    }
                }
                attributes.alwaysDrawnWithCache?.let {
                    if (isAlwaysDrawnWithCacheEnabled != it) {
                        isAlwaysDrawnWithCacheEnabled = it
                    }
                }
                attributes.animateLayoutChanges?.let {
                    val localAnimateLayoutChanges = if (it) LayoutTransition() else null
                    if (layoutTransition != localAnimateLayoutChanges) {
                        layoutTransition = localAnimateLayoutChanges
                    }
                }
                attributes.splitMotionEvents?.let {
                    if (isMotionEventSplittingEnabled != it) {
                        isMotionEventSplittingEnabled = it
                    }
                }
                if (Build.VERSION.SDK_INT >= 18) {
                    attributes.clipChildren?.let {
                        if (clipChildren != it) {
                            clipChildren = it
                        }
                    }
                    attributes.layoutMode?.let {
                        if (layoutMode != it.value) {
                            layoutMode = it.value
                        }
                    }
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    attributes.clipToPadding?.let {
                        if (clipToPadding != it) {
                            clipToPadding = it
                        }
                    }
                    attributes.touchscreenBlocksFocus?.let {
                        if (touchscreenBlocksFocus != it) {
                            touchscreenBlocksFocus = it
                        }
                    }
                    attributes.transitionGroup?.let {
                        if (isTransitionGroup != it) {
                            isTransitionGroup = it
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "viewGroup"

        inline operator fun <reified TView : ViewGroup, reified TAttributes : ViewGroupAttributes> invoke() = ViewGroupFactory(TView::class.java, TAttributes::class.java)
    }
}
