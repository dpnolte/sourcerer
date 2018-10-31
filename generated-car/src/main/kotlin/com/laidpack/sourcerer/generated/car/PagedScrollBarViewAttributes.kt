package com.laidpack.sourcerer.generated.car

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.generated.BackgroundTintModeEnum
import com.laidpack.sourcerer.generated.DrawingCacheQualityEnum
import com.laidpack.sourcerer.generated.ForegroundTintModeEnum
import com.laidpack.sourcerer.generated.LayerTypeEnum
import com.laidpack.sourcerer.generated.LayoutDirectionEnum
import com.laidpack.sourcerer.generated.LayoutModeEnum
import com.laidpack.sourcerer.generated.OverScrollModeEnum
import com.laidpack.sourcerer.generated.ScrollbarStyleEnum
import com.laidpack.sourcerer.generated.VerticalScrollbarPositionEnum
import com.laidpack.sourcerer.generated.ViewGroupAttributes
import com.laidpack.sourcerer.generated.VisibilityEnum
import com.laidpack.sourcerer.services.api.FlagsAccumulator
import com.laidpack.sourcerer.services.api.Format
import com.laidpack.sourcerer.services.api.IAttributes
import com.laidpack.sourcerer.services.api.MultiFormat
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class PagedScrollBarViewAttributes(
    animateLayoutChanges: Boolean? = null,
    clipChildren: Boolean? = null,
    clipToPadding: Boolean? = null,
    animationCache: Boolean? = null,
    persistentDrawingCache: FlagsAccumulator? = null,
    alwaysDrawnWithCache: Boolean? = null,
    addStatesFromChildren: Boolean? = null,
    touchscreenBlocksFocus: Boolean? = null,
    splitMotionEvents: Boolean? = null,
    layoutMode: LayoutModeEnum? = null,
    transitionGroup: Boolean? = null,
    id: Int? = null,
    tag: String? = null,
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
) : ViewGroupAttributes(animateLayoutChanges = animateLayoutChanges, clipChildren = clipChildren, clipToPadding = clipToPadding, animationCache = animationCache, persistentDrawingCache = persistentDrawingCache, alwaysDrawnWithCache = alwaysDrawnWithCache, addStatesFromChildren = addStatesFromChildren, touchscreenBlocksFocus = touchscreenBlocksFocus, splitMotionEvents = splitMotionEvents, layoutMode = layoutMode, transitionGroup = transitionGroup, id = id, tag = tag, background = background, paddingLeft = paddingLeft, paddingBottom = paddingBottom, paddingEnd = paddingEnd, paddingStart = paddingStart, paddingTop = paddingTop, paddingRight = paddingRight, focusable = focusable, autofillHints = autofillHints, importantForAutofill = importantForAutofill, focusableInTouchMode = focusableInTouchMode, visibility = visibility, fitsSystemWindows = fitsSystemWindows, scrollbarStyle = scrollbarStyle, isScrollContainer = isScrollContainer, fadeScrollbars = fadeScrollbars, scrollbarFadeDuration = scrollbarFadeDuration, scrollbarDefaultDelayBeforeFade = scrollbarDefaultDelayBeforeFade, scrollbarSize = scrollbarSize, nextFocusLeft = nextFocusLeft, nextFocusRight = nextFocusRight, nextFocusUp = nextFocusUp, nextFocusDown = nextFocusDown, nextFocusForward = nextFocusForward, clickable = clickable, longClickable = longClickable, contextClickable = contextClickable, saveEnabled = saveEnabled, filterTouchesWhenObscured = filterTouchesWhenObscured, drawingCacheQuality = drawingCacheQuality, keepScreenOn = keepScreenOn, minHeight = minHeight, minWidth = minWidth, soundEffectsEnabled = soundEffectsEnabled, hapticFeedbackEnabled = hapticFeedbackEnabled, contentDescription = contentDescription, accessibilityTraversalBefore = accessibilityTraversalBefore, accessibilityTraversalAfter = accessibilityTraversalAfter, overScrollMode = overScrollMode, alpha = alpha, elevation = elevation, translationX = translationX, translationY = translationY, translationZ = translationZ, transformPivotX = transformPivotX, transformPivotY = transformPivotY, rotation = rotation, rotationX = rotationX, rotationY = rotationY, scaleX = scaleX, scaleY = scaleY, verticalScrollbarPosition = verticalScrollbarPosition, layerType = layerType, layoutDirection = layoutDirection, textDirection = textDirection, textAlignment = textAlignment, importantForAccessibility = importantForAccessibility, accessibilityLiveRegion = accessibilityLiveRegion, labelFor = labelFor, transitionName = transitionName, nestedScrollingEnabled = nestedScrollingEnabled, backgroundTint = backgroundTint, backgroundTintMode = backgroundTintMode, foreground = foreground, foregroundGravity = foregroundGravity, foregroundTint = foregroundTint, foregroundTintMode = foregroundTintMode, scrollIndicators = scrollIndicators, forceHasOverlappingRendering = forceHasOverlappingRendering, tooltipText = tooltipText, keyboardNavigationCluster = keyboardNavigationCluster, nextClusterForward = nextClusterForward, focusedByDefault = focusedByDefault, defaultFocusHighlightEnabled = defaultFocusHighlightEnabled, screenReaderFocusable = screenReaderFocusable, accessibilityPaneTitle = accessibilityPaneTitle, accessibilityHeading = accessibilityHeading, outlineSpotShadowColor = outlineSpotShadowColor, outlineAmbientShadowColor = outlineAmbientShadowColor),
        IAttributes
