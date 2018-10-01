package com.laidpack.sourcerer.generated

import android.animation.LayoutTransition
import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class ViewGroupFactory<TView : ViewGroup, TAttributes : ViewGroupAttributes> : ViewFactory<TView, TAttributes>() {
    override val elementName: String = "viewGroup"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View {
        throw IllegalStateException("android.view.ViewGroup is abstract and cannot be instantiated")
    }

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as ViewGroup
        view.init {
            attributes.animateLayoutChanges?.let {
                val immutableAnimateLayoutChanges = if (it) LayoutTransition() else null
                if (layoutTransition != immutableAnimateLayoutChanges) {
                    layoutTransition = immutableAnimateLayoutChanges
                }
            }
            attributes.animationCache?.let {
                if (isAnimationCacheEnabled != it) {
                    isAnimationCacheEnabled = it
                }
            }
            attributes.persistentDrawingCache?.let {
                if (persistentDrawingCache != it.value) {
                    persistentDrawingCache = it.value
                }
            }
            attributes.alwaysDrawnWithCache?.let {
                if (isAlwaysDrawnWithCacheEnabled != it) {
                    isAlwaysDrawnWithCacheEnabled = it
                }
            }
            attributes.addStatesFromChildren?.let {
                if (addStatesFromChildren() != it) {
                    setAddStatesFromChildren(it)
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
