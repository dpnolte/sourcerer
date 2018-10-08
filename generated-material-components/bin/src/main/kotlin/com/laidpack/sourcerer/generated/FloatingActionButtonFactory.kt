package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import com.google.android.material.animation.MotionSpec
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.Int
import kotlin.String

open class FloatingActionButtonFactory<TView : FloatingActionButton, TAttributes : FloatingActionButtonAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ImageViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "floatingActionButton"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = FloatingActionButton(context)

    override fun init(
        view: TView,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
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

    companion object {
        init {
            InflaterComponent.addFactory(FloatingActionButtonFactory<FloatingActionButton, FloatingActionButtonAttributes>())
        }

        inline operator fun <reified TView : FloatingActionButton, reified TAttributes : FloatingActionButtonAttributes> invoke() = FloatingActionButtonFactory(TView::class.java, TAttributes::class.java)
    }
}
