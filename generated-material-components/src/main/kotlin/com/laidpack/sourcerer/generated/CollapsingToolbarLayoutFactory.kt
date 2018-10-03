package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import com.laidpack.sourcerer.service.api.init
import kotlin.String

open class CollapsingToolbarLayoutFactory<TView : CollapsingToolbarLayout, TAttributes : CollapsingToolbarLayoutAttributes> : FrameLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "collapsingToolbarLayout"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = CollapsingToolbarLayout(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as CollapsingToolbarLayout
        view.init {
            attributes.expandedTitleMarginStart?.let {
                if (expandedTitleMarginStart != it) {
                    expandedTitleMarginStart = it
                }
            }
            attributes.expandedTitleMarginTop?.let {
                if (expandedTitleMarginTop != it) {
                    expandedTitleMarginTop = it
                }
            }
            attributes.expandedTitleMarginEnd?.let {
                if (expandedTitleMarginEnd != it) {
                    expandedTitleMarginEnd = it
                }
            }
            attributes.expandedTitleMarginBottom?.let {
                if (expandedTitleMarginBottom != it) {
                    expandedTitleMarginBottom = it
                }
            }
            attributes.contentScrim?.let {
                val immutableContentScrim = ColorDrawable(it)
                if (contentScrim != immutableContentScrim) {
                    contentScrim = immutableContentScrim
                }
            }
            attributes.statusBarScrim?.let {
                val immutableStatusBarScrim = ColorDrawable(it)
                if (statusBarScrim != immutableStatusBarScrim) {
                    statusBarScrim = immutableStatusBarScrim
                }
            }
            attributes.scrimVisibleHeightTrigger?.let {
                if (scrimVisibleHeightTrigger != it) {
                    scrimVisibleHeightTrigger = it
                }
            }
            attributes.scrimAnimationDuration?.let {
                val immutableScrimAnimationDuration = it.toLong()
                if (scrimAnimationDuration != immutableScrimAnimationDuration) {
                    scrimAnimationDuration = immutableScrimAnimationDuration
                }
            }
            attributes.collapsedTitleGravity?.let {
                if (collapsedTitleGravity != it.value) {
                    collapsedTitleGravity = it.value
                }
            }
            attributes.expandedTitleGravity?.let {
                if (expandedTitleGravity != it.value) {
                    expandedTitleGravity = it.value
                }
            }
            attributes.titleEnabled?.let {
                if (isTitleEnabled != it) {
                    isTitleEnabled = it
                }
            }
            attributes.title?.let {
                if (title != it) {
                    title = it
                }
            }
        }
    }
}
