package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.view.View
import com.google.android.material.animation.MotionSpec
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.laidpack.generator.api.ViewElement
import com.laidpack.sourcerer.generated.ImageButtonFactory
import com.laidpack.sourcerer.services.api.toPorterDuffMode
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = FloatingActionButtonFactory.elementType,
        attributesClazz = FloatingActionButtonAttributes::class
)
open class FloatingActionButtonFactory<TView : FloatingActionButton, TAttributes : FloatingActionButtonAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ImageButtonFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = FloatingActionButton(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is FloatingActionButton) {
            view.apply {
                attributes.backgroundTintMode?.let {
                    val localBackgroundTintMode = it.value.toPorterDuffMode()
                    if (backgroundTintMode != localBackgroundTintMode) {
                        backgroundTintMode = localBackgroundTintMode
                    }
                }
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
                    if (size != it) {
                        customSize = it
                    }
                }
                attributes.elevation?.let {
                    val localElevation = it.toFloat()
                    if (compatElevation != localElevation) {
                        compatElevation = localElevation
                    }
                }
                attributes.hoveredFocusedTranslationZ?.let {
                    val localHoveredFocusedTranslationZ = it.toFloat()
                    if (compatHoveredFocusedTranslationZ != localHoveredFocusedTranslationZ) {
                        compatHoveredFocusedTranslationZ = localHoveredFocusedTranslationZ
                    }
                }
                attributes.pressedTranslationZ?.let {
                    val localPressedTranslationZ = it.toFloat()
                    if (compatPressedTranslationZ != localPressedTranslationZ) {
                        compatPressedTranslationZ = localPressedTranslationZ
                    }
                }
                attributes.useCompatPadding?.let {
                    if (useCompatPadding != it) {
                        useCompatPadding = it
                    }
                }
                attributes.showMotionSpec?.let {
                    val localShowMotionSpec = MotionSpec.createFromResource(context, it)
                    if (showMotionSpec != localShowMotionSpec) {
                        showMotionSpec = localShowMotionSpec
                    }
                }
                attributes.hideMotionSpec?.let {
                    val localHideMotionSpec = MotionSpec.createFromResource(context, it)
                    if (hideMotionSpec != localHideMotionSpec) {
                        hideMotionSpec = localHideMotionSpec
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "floatingActionButton"

        inline operator fun <reified TView : FloatingActionButton, reified TAttributes : FloatingActionButtonAttributes> invoke() = FloatingActionButtonFactory(TView::class.java, TAttributes::class.java)
    }
}
