package com.laidpack.sourcerer.generated.material.components

import com.laidpack.sourcerer.generated.BackgroundTintModeEnum
import com.laidpack.sourcerer.generated.DrawingCacheQualityEnum
import com.laidpack.sourcerer.generated.ForegroundTintModeEnum
import com.laidpack.sourcerer.generated.ImageButtonAttributes
import com.laidpack.sourcerer.generated.LayerTypeEnum
import com.laidpack.sourcerer.generated.LayoutDirectionEnum
import com.laidpack.sourcerer.generated.OverScrollModeEnum
import com.laidpack.sourcerer.generated.ScaleTypeEnum
import com.laidpack.sourcerer.generated.ScrollbarStyleEnum
import com.laidpack.sourcerer.generated.VerticalScrollbarPositionEnum
import com.laidpack.sourcerer.generated.VisibilityEnum
import com.laidpack.sourcerer.services.api.AttributeEnum
import com.laidpack.sourcerer.services.api.ColorQualifier
import com.laidpack.sourcerer.services.api.DimensionQualifier
import com.laidpack.sourcerer.services.api.FlagsAccumulator
import com.laidpack.sourcerer.services.api.Format
import com.laidpack.sourcerer.services.api.IAttributes
import com.laidpack.sourcerer.services.api.MultiFormat
import com.laidpack.typescript.annotation.TypeScript
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class FloatingActionButtonAttributes(
    @field:ColorQualifier val rippleColor: Int? = null,
    val fabSize: FabSizeEnum? = null,
    @field:DimensionQualifier val fabCustomSize: Int? = null,
    @field:DimensionQualifier val hoveredFocusedTranslationZ: Int? = null,
    @field:DimensionQualifier val pressedTranslationZ: Int? = null,
    val useCompatPadding: Boolean? = null,
    val showMotionSpec: Int? = null,
    val hideMotionSpec: Int? = null,
    src: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference)),
    scaleType: ScaleTypeEnum? = null,
    adjustViewBounds: Boolean? = null,
    maxWidth: Int? = null,
    maxHeight: Int? = null,
    tint: Int? = null,
    baselineAlignBottom: Boolean? = null,
    cropToPadding: Boolean? = null,
    baseline: Int? = null,
    drawableAlpha: Int? = null,
    tintMode: Int? = null,
    id: Int? = null,
    tag: String? = null,
    scrollX: Int? = null,
    scrollY: Int? = null,
    background: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference)),
    paddingLeft: Int? = null,
    paddingBottom: Int? = null,
    paddingEnd: Int? = null,
    paddingStart: Int? = null,
    paddingTop: Int? = null,
    paddingRight: Int? = null,
    focusable: MultiFormat = MultiFormat(setOf(Format.Boolean, Format.Enum)),
    autofillHints: MultiFormat = MultiFormat(setOf(Format.Reference, Format.String)),
    importantForAutofill: FlagsAccumulator? = null,
    focusableInTouchMode: Boolean? = null,
    visibility: VisibilityEnum? = null,
    fitsSystemWindows: Boolean? = null,
    scrollbarStyle: ScrollbarStyleEnum? = null,
    isScrollContainer: Boolean? = null,
    fadeScrollbars: Boolean? = null,
    scrollbarFadeDuration: Int? = null,
    scrollbarDefaultDelayBeforeFade: Int? = null,
    scrollbarSize: Int? = null,
    nextFocusLeft: Int? = null,
    nextFocusRight: Int? = null,
    nextFocusUp: Int? = null,
    nextFocusDown: Int? = null,
    nextFocusForward: Int? = null,
    clickable: Boolean? = null,
    longClickable: Boolean? = null,
    contextClickable: Boolean? = null,
    saveEnabled: Boolean? = null,
    filterTouchesWhenObscured: Boolean? = null,
    drawingCacheQuality: DrawingCacheQualityEnum? = null,
    keepScreenOn: Boolean? = null,
    minHeight: Int? = null,
    minWidth: Int? = null,
    soundEffectsEnabled: Boolean? = null,
    hapticFeedbackEnabled: Boolean? = null,
    contentDescription: String? = null,
    accessibilityTraversalBefore: Int? = null,
    accessibilityTraversalAfter: Int? = null,
    overScrollMode: OverScrollModeEnum? = null,
    alpha: Float? = null,
    elevation: Int? = null,
    translationX: Int? = null,
    translationY: Int? = null,
    translationZ: Int? = null,
    transformPivotX: Int? = null,
    transformPivotY: Int? = null,
    rotation: Float? = null,
    rotationX: Float? = null,
    rotationY: Float? = null,
    scaleX: Float? = null,
    scaleY: Float? = null,
    verticalScrollbarPosition: VerticalScrollbarPositionEnum? = null,
    layerType: LayerTypeEnum? = null,
    layoutDirection: LayoutDirectionEnum? = null,
    textDirection: MultiFormat = MultiFormat(setOf(Format.Integer, Format.Enum)),
    textAlignment: MultiFormat = MultiFormat(setOf(Format.Integer, Format.Enum)),
    importantForAccessibility: MultiFormat = MultiFormat(setOf(Format.Integer, Format.Enum)),
    accessibilityLiveRegion: MultiFormat = MultiFormat(setOf(Format.Integer, Format.Enum)),
    labelFor: Int? = null,
    transitionName: String? = null,
    nestedScrollingEnabled: Boolean? = null,
    backgroundTint: Int? = null,
    backgroundTintMode: BackgroundTintModeEnum? = null,
    foreground: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference)),
    foregroundGravity: FlagsAccumulator? = null,
    foregroundTint: Int? = null,
    foregroundTintMode: ForegroundTintModeEnum? = null,
    scrollIndicators: FlagsAccumulator? = null,
    forceHasOverlappingRendering: Boolean? = null,
    tooltipText: String? = null,
    keyboardNavigationCluster: Boolean? = null,
    nextClusterForward: Int? = null,
    focusedByDefault: Boolean? = null,
    defaultFocusHighlightEnabled: Boolean? = null,
    screenReaderFocusable: Boolean? = null,
    accessibilityPaneTitle: String? = null,
    accessibilityHeading: Boolean? = null,
    outlineSpotShadowColor: Int? = null,
    outlineAmbientShadowColor: Int? = null
) : ImageButtonAttributes(src = src, scaleType = scaleType, adjustViewBounds = adjustViewBounds, maxWidth = maxWidth, maxHeight = maxHeight, tint = tint, baselineAlignBottom = baselineAlignBottom, cropToPadding = cropToPadding, baseline = baseline, drawableAlpha = drawableAlpha, tintMode = tintMode, id = id, tag = tag, scrollX = scrollX, scrollY = scrollY, background = background, paddingLeft = paddingLeft, paddingBottom = paddingBottom, paddingEnd = paddingEnd, paddingStart = paddingStart, paddingTop = paddingTop, paddingRight = paddingRight, focusable = focusable, autofillHints = autofillHints, importantForAutofill = importantForAutofill, focusableInTouchMode = focusableInTouchMode, visibility = visibility, fitsSystemWindows = fitsSystemWindows, scrollbarStyle = scrollbarStyle, isScrollContainer = isScrollContainer, fadeScrollbars = fadeScrollbars, scrollbarFadeDuration = scrollbarFadeDuration, scrollbarDefaultDelayBeforeFade = scrollbarDefaultDelayBeforeFade, scrollbarSize = scrollbarSize, nextFocusLeft = nextFocusLeft, nextFocusRight = nextFocusRight, nextFocusUp = nextFocusUp, nextFocusDown = nextFocusDown, nextFocusForward = nextFocusForward, clickable = clickable, longClickable = longClickable, contextClickable = contextClickable, saveEnabled = saveEnabled, filterTouchesWhenObscured = filterTouchesWhenObscured, drawingCacheQuality = drawingCacheQuality, keepScreenOn = keepScreenOn, minHeight = minHeight, minWidth = minWidth, soundEffectsEnabled = soundEffectsEnabled, hapticFeedbackEnabled = hapticFeedbackEnabled, contentDescription = contentDescription, accessibilityTraversalBefore = accessibilityTraversalBefore, accessibilityTraversalAfter = accessibilityTraversalAfter, overScrollMode = overScrollMode, alpha = alpha, elevation = elevation, translationX = translationX, translationY = translationY, translationZ = translationZ, transformPivotX = transformPivotX, transformPivotY = transformPivotY, rotation = rotation, rotationX = rotationX, rotationY = rotationY, scaleX = scaleX, scaleY = scaleY, verticalScrollbarPosition = verticalScrollbarPosition, layerType = layerType, layoutDirection = layoutDirection, textDirection = textDirection, textAlignment = textAlignment, importantForAccessibility = importantForAccessibility, accessibilityLiveRegion = accessibilityLiveRegion, labelFor = labelFor, transitionName = transitionName, nestedScrollingEnabled = nestedScrollingEnabled, backgroundTint = backgroundTint, backgroundTintMode = backgroundTintMode, foreground = foreground, foregroundGravity = foregroundGravity, foregroundTint = foregroundTint, foregroundTintMode = foregroundTintMode, scrollIndicators = scrollIndicators, forceHasOverlappingRendering = forceHasOverlappingRendering, tooltipText = tooltipText, keyboardNavigationCluster = keyboardNavigationCluster, nextClusterForward = nextClusterForward, focusedByDefault = focusedByDefault, defaultFocusHighlightEnabled = defaultFocusHighlightEnabled, screenReaderFocusable = screenReaderFocusable, accessibilityPaneTitle = accessibilityPaneTitle, accessibilityHeading = accessibilityHeading, outlineSpotShadowColor = outlineSpotShadowColor, outlineAmbientShadowColor = outlineAmbientShadowColor),
        IAttributes

enum class FabSizeEnum(override val key: String, override val value: Int) : AttributeEnum {
    @Json(name = "auto")
    Auto("auto", -1),

    @Json(name = "normal")
    Normal("normal", 0),

    @Json(name = "mini")
    Mini("mini", 1);
}
