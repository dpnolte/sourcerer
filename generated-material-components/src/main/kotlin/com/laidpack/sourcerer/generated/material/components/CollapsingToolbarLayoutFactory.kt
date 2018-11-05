package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.View
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.FrameLayoutFactory
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = CollapsingToolbarLayoutFactory.elementType,
        attributesClazz = CollapsingToolbarLayoutAttributes::class,
        layoutParamAttributesClazz = CollapsingToolbarLayoutLayoutParamsAttributes::class
)
open class CollapsingToolbarLayoutFactory<TView : CollapsingToolbarLayout, TAttributes : CollapsingToolbarLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = CollapsingToolbarLayout(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is CollapsingToolbarLayout) {
            view.apply {
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
                attributes.expandedTitleTextAppearance?.let {
                    setExpandedTitleTextAppearance(it)
                }
                attributes.collapsedTitleTextAppearance?.let {
                    setCollapsedTitleTextAppearance(it)
                }
                attributes.contentScrim?.let {
                    val localContentScrim = ColorDrawable(it)
                    if (contentScrim != localContentScrim) {
                        contentScrim = localContentScrim
                    }
                }
                attributes.statusBarScrim?.let {
                    val localStatusBarScrim = ColorDrawable(it)
                    if (statusBarScrim != localStatusBarScrim) {
                        statusBarScrim = localStatusBarScrim
                    }
                }
                attributes.scrimVisibleHeightTrigger?.let {
                    if (height != it) {
                        scrimVisibleHeightTrigger = it
                    }
                }
                attributes.scrimAnimationDuration?.let {
                    val localScrimAnimationDuration = it.toLong()
                    if (scrimAnimationDuration != localScrimAnimationDuration) {
                        scrimAnimationDuration = localScrimAnimationDuration
                    }
                }
                attributes.collapsedTitleGravity?.let {
                    val localCollapsedTitleGravity = it.value
                    if (collapsedTitleGravity != localCollapsedTitleGravity) {
                        collapsedTitleGravity = localCollapsedTitleGravity
                    }
                }
                attributes.expandedTitleGravity?.let {
                    val localExpandedTitleGravity = it.value
                    if (expandedTitleGravity != localExpandedTitleGravity) {
                        expandedTitleGravity = localExpandedTitleGravity
                    }
                }
                attributes.titleEnabled?.let {
                    if (isEnabled != it) {
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

    companion object {
        const val elementType: String = "collapsingToolbarLayout"

        inline operator fun <reified TView : CollapsingToolbarLayout, reified TAttributes : CollapsingToolbarLayoutAttributes> invoke() = CollapsingToolbarLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
