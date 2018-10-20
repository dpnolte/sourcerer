package com.laidpack.sourcerer.generated

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import androidx.core.content.ContextCompat
import com.laidpack.sourcerer.services.api.BaseViewFactory
import com.laidpack.sourcerer.services.api.toPorterDuffMode
import java.lang.Class
import kotlin.String

open class ViewFactory<TView : View, TAttributes : ViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : BaseViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = View(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        view.apply {
            attributes.id?.let {
                if (id != it) {
                    id = it
                }
            }
            if (attributes.scrollX != null || attributes.scrollY != null) {
                val localScrollX = attributes.scrollX ?: scrollX
                val localScrollY = attributes.scrollY ?: scrollY
                if (scrollX != localScrollX || scrollY != localScrollY) {
                    scrollTo(localScrollX, localScrollY)
                }
            }
            attributes.visibility?.let {
                if (visibility != it.value) {
                    visibility = it.value
                }
            }
            attributes.scrollbarStyle?.let {
                if (scrollBarStyle != it.value) {
                    scrollBarStyle = it.value
                }
            }
            attributes.nextFocusLeft?.let {
                if (nextFocusLeftId != it) {
                    nextFocusLeftId = it
                }
            }
            attributes.nextFocusRight?.let {
                if (nextFocusRightId != it) {
                    nextFocusRightId = it
                }
            }
            attributes.nextFocusUp?.let {
                if (nextFocusUpId != it) {
                    nextFocusUpId = it
                }
            }
            attributes.nextFocusDown?.let {
                if (nextFocusDownId != it) {
                    nextFocusDownId = it
                }
            }
            attributes.keepScreenOn?.let {
                if (keepScreenOn != it) {
                    keepScreenOn = it
                }
            }
            attributes.drawingCacheQuality?.let {
                if (drawingCacheQuality != it.value) {
                    drawingCacheQuality = it.value
                }
            }
            attributes.contentDescription?.let {
                if (contentDescription != it) {
                    contentDescription = it
                }
            }
            attributes.filterTouchesWhenObscured?.let {
                if (filterTouchesWhenObscured != it) {
                    filterTouchesWhenObscured = it
                }
            }
            attributes.overScrollMode?.let {
                if (overScrollMode != it.value) {
                    overScrollMode = it.value
                }
            }
            attributes.nextFocusForward?.let {
                if (nextFocusForwardId != it) {
                    nextFocusForwardId = it
                }
            }
            attributes.alpha?.let {
                alpha = it
            }
            attributes.translationX?.let {
                val localTranslationX = it.toFloat()
                translationX = localTranslationX
            }
            attributes.translationY?.let {
                val localTranslationY = it.toFloat()
                translationY = localTranslationY
            }
            attributes.transformPivotX?.let {
                val localTransformPivotX = it.toFloat()
                if (pivotX != localTransformPivotX) {
                    pivotX = localTransformPivotX
                }
            }
            attributes.transformPivotY?.let {
                val localTransformPivotY = it.toFloat()
                if (pivotY != localTransformPivotY) {
                    pivotY = localTransformPivotY
                }
            }
            attributes.rotation?.let {
                rotation = it
            }
            attributes.rotationX?.let {
                rotationX = it
            }
            attributes.rotationY?.let {
                rotationY = it
            }
            attributes.scaleX?.let {
                scaleX = it
            }
            attributes.scaleY?.let {
                scaleY = it
            }
            attributes.layerType?.let {
                if (layerType != it.value) {
                    setLayerType(it.value, null)
                }
            }
            if (Build.VERSION.SDK_INT >= 16) {
                if (attributes.background.hasColor || attributes.background.hasReference) {
                    val localBackground = when {
                        attributes.background.hasColor -> ColorDrawable(attributes.background.color)
                        else -> ContextCompat.getDrawable(context, attributes.background.reference) as Drawable
                    }
                    if (background != localBackground) {
                        background = localBackground
                    }
                }
                attributes.fitsSystemWindows?.let {
                    if (fitsSystemWindows != it) {
                        fitsSystemWindows = it
                    }
                }
                attributes.scrollbarFadeDuration?.let {
                    if (scrollBarFadeDuration != it) {
                        scrollBarFadeDuration = it
                    }
                }
                attributes.scrollbarDefaultDelayBeforeFade?.let {
                    if (scrollBarDefaultDelayBeforeFade != it) {
                        scrollBarDefaultDelayBeforeFade = it
                    }
                }
                attributes.scrollbarSize?.let {
                    if (scrollBarSize != it) {
                        scrollBarSize = it
                    }
                }
                attributes.minHeight?.let {
                    if (minimumHeight != it) {
                        minimumHeight = it
                    }
                }
                attributes.minWidth?.let {
                    if (minimumWidth != it) {
                        minimumWidth = it
                    }
                }
                if (attributes.importantForAccessibility.hasInteger || attributes.importantForAccessibility.hasEnum) {
                    val localImportantForAccessibility = when {
                        attributes.importantForAccessibility.hasInteger -> attributes.importantForAccessibility.integer
                        else -> attributes.importantForAccessibility.enum
                    }
                    if (importantForAccessibility != localImportantForAccessibility) {
                        importantForAccessibility = localImportantForAccessibility
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= 17) {
                attributes.layoutDirection?.let {
                    if (layoutDirection != it.value) {
                        layoutDirection = it.value
                    }
                }
                if (attributes.textDirection.hasInteger || attributes.textDirection.hasEnum) {
                    val localTextDirection = when {
                        attributes.textDirection.hasInteger -> attributes.textDirection.integer
                        else -> attributes.textDirection.enum
                    }
                    if (textDirection != localTextDirection) {
                        textDirection = localTextDirection
                    }
                }
                attributes.textAlignment?.let {
                    if (textAlignment != it.value) {
                        textAlignment = it.value
                    }
                }
                attributes.labelFor?.let {
                    if (labelFor != it) {
                        labelFor = it
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= 19) {
                if (attributes.accessibilityLiveRegion.hasInteger || attributes.accessibilityLiveRegion.hasEnum) {
                    val localAccessibilityLiveRegion = when {
                        attributes.accessibilityLiveRegion.hasInteger -> attributes.accessibilityLiveRegion.integer
                        else -> attributes.accessibilityLiveRegion.enum
                    }
                    if (accessibilityLiveRegion != localAccessibilityLiveRegion) {
                        accessibilityLiveRegion = localAccessibilityLiveRegion
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= 21) {
                attributes.elevation?.let {
                    val localElevation = it.toFloat()
                    elevation = localElevation
                }
                attributes.translationZ?.let {
                    val localTranslationZ = it.toFloat()
                    translationZ = localTranslationZ
                }
                attributes.transitionName?.let {
                    if (transitionName != it) {
                        transitionName = it
                    }
                }
                attributes.nestedScrollingEnabled?.let {
                    isNestedScrollingEnabled = it
                }
                attributes.backgroundTint?.let {
                    val localBackgroundTint = ColorStateList.valueOf(it)
                    if (backgroundTintList != localBackgroundTint) {
                        backgroundTintList = localBackgroundTint
                    }
                }
                attributes.backgroundTintMode?.let {
                    val localBackgroundTintMode = it.value.toPorterDuffMode()
                    if (backgroundTintMode != localBackgroundTintMode) {
                        backgroundTintMode = localBackgroundTintMode
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= 22) {
                attributes.accessibilityTraversalBefore?.let {
                    if (accessibilityTraversalBefore != it) {
                        accessibilityTraversalBefore = it
                    }
                }
                attributes.accessibilityTraversalAfter?.let {
                    if (accessibilityTraversalAfter != it) {
                        accessibilityTraversalAfter = it
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= 23) {
                if (attributes.foreground.hasColor || attributes.foreground.hasReference) {
                    val localForeground = when {
                        attributes.foreground.hasColor -> ColorDrawable(attributes.foreground.color)
                        else -> ContextCompat.getDrawable(context, attributes.foreground.reference) as Drawable
                    }
                    foreground = localForeground
                }
                attributes.foregroundGravity?.let {
                    val localForegroundGravity = it.value
                    if (foregroundGravity != localForegroundGravity) {
                        foregroundGravity = localForegroundGravity
                    }
                }
                attributes.foregroundTint?.let {
                    val localForegroundTint = ColorStateList.valueOf(it)
                    if (foregroundTintList != localForegroundTint) {
                        foregroundTintList = localForegroundTint
                    }
                }
                attributes.foregroundTintMode?.let {
                    val localForegroundTintMode = it.value.toPorterDuffMode()
                    if (foregroundTintMode != localForegroundTintMode) {
                        foregroundTintMode = localForegroundTintMode
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= 24) {
                attributes.forceHasOverlappingRendering?.let {
                    forceHasOverlappingRendering(it)
                }
            }
            if (Build.VERSION.SDK_INT >= 26) {
                when {
                    attributes.focusable.hasBoolean ->  {
                        val localFocusable = attributes.focusable.boolean
                        isFocusable = localFocusable
                    }
                    attributes.focusable.hasEnum ->  {
                        val localFocusable = attributes.focusable.enum
                        focusable = localFocusable
                    }
                }
                if (attributes.autofillHints.hasReference || attributes.autofillHints.hasString) {
                    val localAutofillHints = when {
                        attributes.autofillHints.hasReference -> context.resources.getString(attributes.autofillHints.reference)
                        else -> attributes.autofillHints.string
                    }
                    setAutofillHints(localAutofillHints)
                }
                attributes.importantForAutofill?.let {
                    val localImportantForAutofill = it.value
                    if (importantForAutofill != localImportantForAutofill) {
                        importantForAutofill = localImportantForAutofill
                    }
                }
                attributes.tooltipText?.let {
                    if (tooltipText != it) {
                        tooltipText = it
                    }
                }
                attributes.keyboardNavigationCluster?.let {
                    isKeyboardNavigationCluster = it
                }
                attributes.nextClusterForward?.let {
                    if (nextClusterForwardId != it) {
                        nextClusterForwardId = it
                    }
                }
                attributes.focusedByDefault?.let {
                    isFocusedByDefault = it
                }
                attributes.defaultFocusHighlightEnabled?.let {
                    if (defaultFocusHighlightEnabled != it) {
                        defaultFocusHighlightEnabled = it
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= 28) {
                attributes.screenReaderFocusable?.let {
                    isScreenReaderFocusable = it
                }
                attributes.accessibilityPaneTitle?.let {
                    if (accessibilityPaneTitle != it) {
                        accessibilityPaneTitle = it
                    }
                }
                attributes.accessibilityHeading?.let {
                    isAccessibilityHeading = it
                }
                attributes.outlineSpotShadowColor?.let {
                    outlineSpotShadowColor = it
                }
                attributes.outlineAmbientShadowColor?.let {
                    outlineAmbientShadowColor = it
                }
            }
        }
    }

    companion object {
        const val elementType: String = "view"

        inline operator fun <reified TView : View, reified TAttributes : ViewAttributes> invoke() = ViewFactory(TView::class.java, TAttributes::class.java)
    }
}
