package com.laidpack.sourcerer.generated

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import androidx.core.content.ContextCompat
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.services.api.BaseViewFactory
import com.laidpack.sourcerer.services.api.toPorterDuffMode
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = ViewFactory.elementType,
        attributesClazz = ViewAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
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
            attributes.tag?.let {
                val localTag = it
                if (tag != localTag) {
                    tag = localTag
                }
            }
            if (attributes.scrollX != null || attributes.scrollY != null) {
                val localScrollX = attributes.scrollX ?: scrollX
                val localScrollY = attributes.scrollY ?: scrollY
                if (scrollX != localScrollX || scrollY != localScrollY) {
                    scrollTo(localScrollX, localScrollY)
                }
            }
            attributes.focusableInTouchMode?.let {
                if (isFocusableInTouchMode != it) {
                    isFocusableInTouchMode = it
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
            attributes.clickable?.let {
                if (isClickable != it) {
                    isClickable = it
                }
            }
            attributes.longClickable?.let {
                if (isLongClickable != it) {
                    isLongClickable = it
                }
            }
            attributes.saveEnabled?.let {
                if (isEnabled != it) {
                    isSaveEnabled = it
                }
            }
            attributes.keepScreenOn?.let {
                if (keepScreenOn != it) {
                    keepScreenOn = it
                }
            }
            attributes.soundEffectsEnabled?.let {
                if (isSoundEffectsEnabled != it) {
                    isSoundEffectsEnabled = it
                }
            }
            attributes.drawingCacheQuality?.let {
                if (drawingCacheQuality != it.value) {
                    drawingCacheQuality = it.value
                }
            }
            attributes.hapticFeedbackEnabled?.let {
                if (isHapticFeedbackEnabled != it) {
                    isHapticFeedbackEnabled = it
                }
            }
            attributes.contentDescription?.let {
                if (contentDescription != it) {
                    contentDescription = it
                }
            }
            attributes.fadeScrollbars?.let {
                if (isScrollbarFadingEnabled != it) {
                    isScrollbarFadingEnabled = it
                }
            }
            attributes.filterTouchesWhenObscured?.let {
                if (isEnabled != it) {
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
                if (alpha != it) {
                    alpha = it
                }
            }
            attributes.translationX?.let {
                val localTranslationX = it.toFloat()
                if (translationX != localTranslationX) {
                    translationX = localTranslationX
                }
            }
            attributes.translationY?.let {
                val localTranslationY = it.toFloat()
                if (translationY != localTranslationY) {
                    translationY = localTranslationY
                }
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
                if (rotation != it) {
                    rotation = it
                }
            }
            attributes.rotationX?.let {
                if (rotationX != it) {
                    rotationX = it
                }
            }
            attributes.rotationY?.let {
                if (rotationY != it) {
                    rotationY = it
                }
            }
            attributes.scaleX?.let {
                if (scaleX != it) {
                    scaleX = it
                }
            }
            attributes.scaleY?.let {
                if (scaleY != it) {
                    scaleY = it
                }
            }
            attributes.verticalScrollbarPosition?.let {
                if (verticalScrollbarPosition != it.value) {
                    verticalScrollbarPosition = it.value
                }
            }
            attributes.layerType?.let {
                if (layerType != it.value) {
                    setLayerType(it.value, null)
                }
            }
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
            attributes.isScrollContainer?.let {
                if (isScrollContainer != it) {
                    isScrollContainer = it
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
            if (attributes.paddingLeft != null || attributes.paddingBottom != null || attributes.paddingEnd != null || attributes.paddingStart != null || attributes.paddingTop != null || attributes.paddingRight != null) {
                val localPaddingBottomDimension = attributes.paddingBottom ?: bottom
                val localPaddingLeftDimension = attributes.paddingLeft ?: left
                val localPaddingRightDimension = attributes.paddingRight ?: right
                val localPaddingTopDimension = attributes.paddingTop ?: top
                if (bottom != localPaddingBottomDimension || left != localPaddingLeftDimension || right != localPaddingRightDimension || top != localPaddingTopDimension) {
                    setPadding(localPaddingLeftDimension, localPaddingTopDimension, localPaddingRightDimension, localPaddingBottomDimension)
                }
                val localPaddingEndDimension = attributes.paddingEnd ?: paddingEnd
                val localPaddingStartDimension = attributes.paddingStart ?: paddingStart
                if (bottom != localPaddingBottomDimension || paddingEnd != localPaddingEndDimension || paddingStart != localPaddingStartDimension || top != localPaddingTopDimension) {
                    setPaddingRelative(localPaddingStartDimension, localPaddingTopDimension, localPaddingEndDimension, localPaddingBottomDimension)
                }
            }
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
            if (attributes.textAlignment.hasInteger || attributes.textAlignment.hasEnum) {
                val localTextAlignment = when {
                    attributes.textAlignment.hasInteger -> attributes.textAlignment.integer
                    else -> attributes.textAlignment.enum
                }
                if (textAlignment != localTextAlignment) {
                    textAlignment = localTextAlignment
                }
            }
            attributes.labelFor?.let {
                if (labelFor != it) {
                    labelFor = it
                }
            }
            if (attributes.accessibilityLiveRegion.hasInteger || attributes.accessibilityLiveRegion.hasEnum) {
                val localAccessibilityLiveRegion = when {
                    attributes.accessibilityLiveRegion.hasInteger -> attributes.accessibilityLiveRegion.integer
                    else -> attributes.accessibilityLiveRegion.enum
                }
                if (accessibilityLiveRegion != localAccessibilityLiveRegion) {
                    accessibilityLiveRegion = localAccessibilityLiveRegion
                }
            }
            attributes.elevation?.let {
                val localElevation = it.toFloat()
                if (elevation != localElevation) {
                    elevation = localElevation
                }
            }
            attributes.translationZ?.let {
                val localTranslationZ = it.toFloat()
                if (translationZ != localTranslationZ) {
                    translationZ = localTranslationZ
                }
            }
            attributes.transitionName?.let {
                if (transitionName != it) {
                    transitionName = it
                }
            }
            attributes.nestedScrollingEnabled?.let {
                if (isEnabled != it) {
                    isNestedScrollingEnabled = it
                }
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
            attributes.contextClickable?.let {
                if (isContextClickable != it) {
                    isContextClickable = it
                }
            }
            if (attributes.foreground.hasColor || attributes.foreground.hasReference) {
                val localForeground = when {
                    attributes.foreground.hasColor -> ColorDrawable(attributes.foreground.color)
                    else -> ContextCompat.getDrawable(context, attributes.foreground.reference) as Drawable
                }
                if (foreground != localForeground) {
                    foreground = localForeground
                }
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
            attributes.scrollIndicators?.let {
                val localScrollIndicators = it.value
                if (scrollIndicators != localScrollIndicators) {
                    scrollIndicators = localScrollIndicators
                }
            }
            attributes.forceHasOverlappingRendering?.let {
                if (hasOverlappingRendering != it) {
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
                    if (isKeyboardNavigationCluster != it) {
                        isKeyboardNavigationCluster = it
                    }
                }
                attributes.nextClusterForward?.let {
                    if (nextClusterForwardId != it) {
                        nextClusterForwardId = it
                    }
                }
                attributes.focusedByDefault?.let {
                    if (isFocusedByDefault != it) {
                        isFocusedByDefault = it
                    }
                }
                attributes.defaultFocusHighlightEnabled?.let {
                    if (defaultFocusHighlightEnabled != it) {
                        defaultFocusHighlightEnabled = it
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= 28) {
                attributes.screenReaderFocusable?.let {
                    if (isScreenReaderFocusable != it) {
                        isScreenReaderFocusable = it
                    }
                }
                attributes.accessibilityPaneTitle?.let {
                    if (accessibilityPaneTitle != it) {
                        accessibilityPaneTitle = it
                    }
                }
                attributes.accessibilityHeading?.let {
                    if (isAccessibilityHeading != it) {
                        isAccessibilityHeading = it
                    }
                }
                attributes.outlineSpotShadowColor?.let {
                    if (outlineSpotShadowColor != it) {
                        outlineSpotShadowColor = it
                    }
                }
                attributes.outlineAmbientShadowColor?.let {
                    if (outlineAmbientShadowColor != it) {
                        outlineAmbientShadowColor = it
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "view"

        inline operator fun <reified TView : View, reified TAttributes : ViewAttributes> invoke() = ViewFactory(TView::class.java, TAttributes::class.java)
    }
}
