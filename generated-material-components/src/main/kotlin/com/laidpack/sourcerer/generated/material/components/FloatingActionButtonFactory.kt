package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.laidpack.sourcerer.generated.ImageViewFactory
import com.laidpack.sourcerer.services.api.toPorterDuffMode
import java.lang.Class
import kotlin.String

open class FloatingActionButtonFactory<TView : FloatingActionButton, TAttributes : FloatingActionButtonAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ImageViewFactory<TView, TAttributes>(instanceType, attributesType) {
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
                    val localRippleColor = ResourcesCompat.getColorStateList(context.resources, it, null)
                    if (rippleColorStateList != localRippleColor) {
                        setRippleColor(localRippleColor)
                    }
                }
                attributes.fabSize?.let {
                    size = it.value
                }
                attributes.fabCustomSize?.let {
                    if (customSize != it) {
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
                    if (id != it) {
                        setShowMotionSpecResource(it)
                    }
                }
                attributes.hideMotionSpec?.let {
                    if (id != it) {
                        setHideMotionSpecResource(it)
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
