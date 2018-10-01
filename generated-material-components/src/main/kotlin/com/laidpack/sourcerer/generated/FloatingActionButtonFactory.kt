package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.google.android.material.animation.MotionSpec
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class FloatingActionButtonFactory<TView : FloatingActionButton, TAttributes : FloatingActionButtonAttributes> : ImageViewFactory<TView, TAttributes>() {
    override val elementName: String = "floatingActionButton"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = FloatingActionButton(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as FloatingActionButton
        view.init {
            attributes.rippleColor?.let {
                if (rippleColor != it) {
                    rippleColor = it
                }
            }
            attributes.fabSize?.let {
                if (size != it.value) {
                    size = it.value
                }
            }
            attributes.fabCustomSize?.let {
                if (customSize != it) {
                    customSize = it
                }
            }
            attributes.elevation?.let {
                val immutableElevation = it.toFloat()
                if (compatElevation != immutableElevation) {
                    compatElevation = immutableElevation
                }
            }
            attributes.hoveredFocusedTranslationZ?.let {
                val immutableHoveredFocusedTranslationZ = it.toFloat()
                if (compatHoveredFocusedTranslationZ != immutableHoveredFocusedTranslationZ) {
                    compatHoveredFocusedTranslationZ = immutableHoveredFocusedTranslationZ
                }
            }
            attributes.pressedTranslationZ?.let {
                val immutablePressedTranslationZ = it.toFloat()
                if (compatPressedTranslationZ != immutablePressedTranslationZ) {
                    compatPressedTranslationZ = immutablePressedTranslationZ
                }
            }
            attributes.useCompatPadding?.let {
                if (useCompatPadding != it) {
                    useCompatPadding = it
                }
            }
            attributes.showMotionSpec?.let {
                val immutableShowMotionSpec = MotionSpec.createFromResource(context, it)
                if (showMotionSpec != immutableShowMotionSpec) {
                    showMotionSpec = immutableShowMotionSpec
                }
            }
            attributes.hideMotionSpec?.let {
                val immutableHideMotionSpec = MotionSpec.createFromResource(context, it)
                if (hideMotionSpec != immutableHideMotionSpec) {
                    hideMotionSpec = immutableHideMotionSpec
                }
            }
        }
    }
}
