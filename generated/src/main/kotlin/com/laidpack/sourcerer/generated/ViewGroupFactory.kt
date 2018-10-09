package com.laidpack.sourcerer.generated

import android.animation.LayoutTransition
import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.String

open class ViewGroupFactory<TView : ViewGroup, TAttributes : ViewGroupAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "viewGroup"

    override fun createInstance(context: Context): View {
        // // ViewGroup is abstract
        return super.createInstance(context)
    }

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is ViewGroup) {
            view.init {
                attributes.addStatesFromChildren?.let {
                    if (addStatesFromChildren() != it) {
                        setAddStatesFromChildren(it)
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
                attributes.animateLayoutChanges?.let {
                    val immutableAnimateLayoutChanges = if (it) LayoutTransition() else null
                    if (layoutTransition != immutableAnimateLayoutChanges) {
                        layoutTransition = immutableAnimateLayoutChanges
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
        init {
            InflaterComponent.addFactory(ViewGroupFactory<ViewGroup, ViewGroupAttributes>())
        }

        inline operator fun <reified TView : ViewGroup, reified TAttributes : ViewGroupAttributes> invoke() = ViewGroupFactory(TView::class.java, TAttributes::class.java)
    }
}
