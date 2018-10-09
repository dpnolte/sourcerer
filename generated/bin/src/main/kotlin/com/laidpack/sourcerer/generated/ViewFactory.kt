package com.laidpack.sourcerer.generated

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.BaseViewFactory
import com.laidpack.sourcerer.service.api.init
import com.laidpack.sourcerer.service.api.toPorterDuffMode
import java.lang.Class
import kotlin.String

open class ViewFactory<TView : View, TAttributes : ViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : BaseViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "view"

    override fun createInstance(context: Context): View = View(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        view.init {
            attributes.id?.let {
                if (id != it) {
                    id = it
                }
            }
            if (attributes.scrollX != null || attributes.scrollY != null) {
                val immutableScrollX = attributes.scrollX ?: scrollX
                val immutableScrollY = attributes.scrollY ?: scrollY
                if (scrollX != immutableScrollX || scrollY != immutableScrollY) {
                    scrollTo(immutableScrollX, immutableScrollY)
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
            attributes.requiresFadingEdge?.let {
                val immutableRequiresFadingEdge = it.value == 1
                if (isHorizontalFadingEdgeEnabled != immutableRequiresFadingEdge) {
                    isHorizontalFadingEdgeEnabled = immutableRequiresFadingEdge
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
                if (isSaveEnabled != it) {
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
                if (alpha != it) {
                    alpha = it
                }
            }
            attributes.translationX?.let {
                val immutableTranslationX = it.toFloat()
                if (translationX != immutableTranslationX) {
                    translationX = immutableTranslationX
                }
            }
            attributes.translationY?.let {
                val immutableTranslationY = it.toFloat()
                if (translationY != immutableTranslationY) {
                    translationY = immutableTranslationY
                }
            }
            attributes.transformPivotX?.let {
                val immutableTransformPivotX = it.toFloat()
                if (pivotX != immutableTransformPivotX) {
                    pivotX = immutableTransformPivotX
                }
            }
            attributes.transformPivotY?.let {
                val immutableTransformPivotY = it.toFloat()
                if (pivotY != immutableTransformPivotY) {
                    pivotY = immutableTransformPivotY
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
            attributes.layerType?.let {
                if (layerType != it.value) {
                    setLayerType(it.value, null)
                }
            }
            if (Build.VERSION.SDK_INT >= 16) {
                when {
                    attributes.background.hasColor || attributes.background.hasReference ->  {
                        val immutableBackground = when {
                            attributes.background.hasColor -> ColorDrawable(attributes.background.color)
                            else -> ContextCompat.getDrawable(context, attributes.background.reference) as Drawable
                        }
                        background = immutableBackground
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
                attributes.importantForAccessibility?.let {
                    if (importantForAccessibility != it.value) {
                        importantForAccessibility = it.value
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= 17) {
                if (attributes.paddingLeft != null || attributes.paddingTop != null || attributes.paddingRight != null || attributes.paddingBottom != null || attributes.paddingStart != null || attributes.paddingEnd != null) {
                    val immutablePaddingBottomDimension = attributes.paddingBottom ?: paddingBottom
                    val immutablePaddingLeftDimension = attributes.paddingLeft ?: paddingLeft
                    val immutablePaddingRightDimension = attributes.paddingRight ?: paddingRight
                    val immutablePaddingTopDimension = attributes.paddingTop ?: paddingTop
                    if (paddingBottom != immutablePaddingBottomDimension || paddingLeft != immutablePaddingLeftDimension || paddingRight != immutablePaddingRightDimension || paddingTop != immutablePaddingTopDimension) {
                        setPadding(immutablePaddingLeftDimension, immutablePaddingTopDimension, immutablePaddingRightDimension, immutablePaddingBottomDimension)
                    }
                    val immutablePaddingStartDimension = attributes.paddingStart ?: paddingStart
                    val immutablePaddingEndDimension = attributes.paddingEnd ?: paddingEnd
                    if (paddingBottom != immutablePaddingBottomDimension || paddingStart != immutablePaddingStartDimension || paddingEnd != immutablePaddingEndDimension || paddingTop != immutablePaddingTopDimension) {
                        setPaddingRelative(immutablePaddingStartDimension, immutablePaddingTopDimension, immutablePaddingEndDimension, immutablePaddingBottomDimension)
                    }
                }
                attributes.layoutDirection?.let {
                    if (layoutDirection != it.value) {
                        layoutDirection = it.value
                    }
                }
                attributes.textDirection?.let {
                    if (textDirection != it.value) {
                        textDirection = it.value
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
                attributes.accessibilityLiveRegion?.let {
                    if (accessibilityLiveRegion != it.value) {
                        accessibilityLiveRegion = it.value
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= 21) {
                attributes.elevation?.let {
                    val immutableElevation = it.toFloat()
                    if (elevation != immutableElevation) {
                        elevation = immutableElevation
                    }
                }
                attributes.translationZ?.let {
                    val immutableTranslationZ = it.toFloat()
                    if (translationZ != immutableTranslationZ) {
                        translationZ = immutableTranslationZ
                    }
                }
                attributes.transitionName?.let {
                    if (transitionName != it) {
                        transitionName = it
                    }
                }
                attributes.nestedScrollingEnabled?.let {
                    if (isNestedScrollingEnabled != it) {
                        isNestedScrollingEnabled = it
                    }
                }
                attributes.backgroundTint?.let {
                    val immutableBackgroundTint = ResourcesCompat.getColorStateList(context.resources, it, null)
                    if (backgroundTintList != immutableBackgroundTint) {
                        backgroundTintList = immutableBackgroundTint
                    }
                }
                attributes.backgroundTintMode?.let {
                    val immutableBackgroundTintMode = it.value.toPorterDuffMode()
                    if (backgroundTintMode != immutableBackgroundTintMode) {
                        backgroundTintMode = immutableBackgroundTintMode
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
                attributes.contextClickable?.let {
                    if (isContextClickable != it) {
                        isContextClickable = it
                    }
                }
                if (attributes.foreground.hasColor || attributes.foreground.hasReference) {
                    val immutableForeground = when {
                        attributes.foreground.hasColor -> ColorDrawable(attributes.foreground.color)
                        else -> ContextCompat.getDrawable(context, attributes.foreground.reference) as Drawable
                    }
                    if (foreground != immutableForeground) {
                        foreground = immutableForeground
                    }
                }
                attributes.foregroundGravity?.let {
                    if (foregroundGravity != it.value) {
                        foregroundGravity = it.value
                    }
                }
                attributes.foregroundTint?.let {
                    val immutableForegroundTint = ResourcesCompat.getColorStateList(context.resources, it, null)
                    if (foregroundTintList != immutableForegroundTint) {
                        foregroundTintList = immutableForegroundTint
                    }
                }
                attributes.foregroundTintMode?.let {
                    val immutableForegroundTintMode = it.value.toPorterDuffMode()
                    if (foregroundTintMode != immutableForegroundTintMode) {
                        foregroundTintMode = immutableForegroundTintMode
                    }
                }
                attributes.scrollIndicators?.let {
                    if (scrollIndicators != it.value) {
                        scrollIndicators = it.value
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= 24) {
                attributes.forceHasOverlappingRendering?.let {
                    if (hasOverlappingRendering != it) {
                        forceHasOverlappingRendering(it)
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= 26) {
                when {
                    attributes.focusable.hasBoolean ->  {
                        val immutableFocusable = attributes.focusable.boolean
                        isFocusable = immutableFocusable
                    }
                    attributes.focusable.hasEnum ->  {
                        val immutableFocusable = attributes.focusable.enum
                        focusable = immutableFocusable
                    }
                }
                attributes.importantForAutofill?.let {
                    if (importantForAutofill != it.value) {
                        importantForAutofill = it.value
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
        init {
            InflaterComponent.addFactory(ViewFactory<View, ViewAttributes>())
        }

        inline operator fun <reified TView : View, reified TAttributes : ViewAttributes> invoke() = ViewFactory(TView::class.java, TAttributes::class.java)
    }
}
