package com.laidpack.sourcerer.generated.emoji

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.generated.AutoSizeTextTypeEnum
import com.laidpack.sourcerer.generated.BackgroundTintModeEnum
import com.laidpack.sourcerer.generated.BreakStrategyEnum
import com.laidpack.sourcerer.generated.ButtonAttributes
import com.laidpack.sourcerer.generated.DrawableTintModeEnum
import com.laidpack.sourcerer.generated.DrawingCacheQualityEnum
import com.laidpack.sourcerer.generated.EllipsizeEnum
import com.laidpack.sourcerer.generated.ForegroundTintModeEnum
import com.laidpack.sourcerer.generated.HyphenationFrequencyEnum
import com.laidpack.sourcerer.generated.JustificationModeEnum
import com.laidpack.sourcerer.generated.LayerTypeEnum
import com.laidpack.sourcerer.generated.LayoutDirectionEnum
import com.laidpack.sourcerer.generated.OverScrollModeEnum
import com.laidpack.sourcerer.generated.ScrollbarStyleEnum
import com.laidpack.sourcerer.generated.VerticalScrollbarPositionEnum
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
open class EmojiButtonAttributes(
    text: String? = null,
    hint: String? = null,
    textColor: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference)),
    textColorHighlight: Int? = null,
    textColorHint: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference)),
    textSize: Int? = null,
    textScaleX: Float? = null,
    textColorLink: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference)),
    cursorVisible: Boolean? = null,
    maxLines: Int? = null,
    maxHeight: Int? = null,
    lines: Int? = null,
    height: Int? = null,
    minLines: Int? = null,
    maxEms: Int? = null,
    maxWidth: Int? = null,
    ems: Int? = null,
    width: Int? = null,
    minEms: Int? = null,
    gravity: FlagsAccumulator? = null,
    enabled: Boolean? = null,
    includeFontPadding: Boolean? = null,
    shadowColor: Int? = null,
    shadowDx: Float? = null,
    shadowDy: Float? = null,
    shadowRadius: Float? = null,
    autoLink: FlagsAccumulator? = null,
    linksClickable: Boolean? = null,
    freezesText: Boolean? = null,
    ellipsize: EllipsizeEnum? = null,
    drawableTop: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference)),
    drawableLeft: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference)),
    drawableBottom: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference)),
    drawableRight: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference)),
    drawablePadding: Int? = null,
    drawableTint: Int? = null,
    drawableTintMode: DrawableTintModeEnum? = null,
    lineHeight: Int? = null,
    firstBaselineToTopHeight: Int? = null,
    lastBaselineToBottomHeight: Int? = null,
    marqueeRepeatLimit: MultiFormat = MultiFormat(setOf(Format.Integer, Format.Enum)),
    inputType: FlagsAccumulator? = null,
    imeOptions: FlagsAccumulator? = null,
    privateImeOptions: String? = null,
    editorExtras: Int? = null,
    textIsSelectable: Boolean? = null,
    textAllCaps: Boolean? = null,
    elegantTextHeight: Boolean? = null,
    fallbackLineSpacing: Boolean? = null,
    letterSpacing: Float? = null,
    fontFeatureSettings: String? = null,
    breakStrategy: BreakStrategyEnum? = null,
    hyphenationFrequency: HyphenationFrequencyEnum? = null,
    autoSizeTextType: AutoSizeTextTypeEnum? = null,
    justificationMode: JustificationModeEnum? = null,
    View_clickable: Boolean? = null,
    View_longClickable: Boolean? = null,
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
) : ButtonAttributes(text = text, hint = hint, textColor = textColor, textColorHighlight = textColorHighlight, textColorHint = textColorHint, textSize = textSize, textScaleX = textScaleX, textColorLink = textColorLink, cursorVisible = cursorVisible, maxLines = maxLines, maxHeight = maxHeight, lines = lines, height = height, minLines = minLines, maxEms = maxEms, maxWidth = maxWidth, ems = ems, width = width, minEms = minEms, gravity = gravity, enabled = enabled, includeFontPadding = includeFontPadding, shadowColor = shadowColor, shadowDx = shadowDx, shadowDy = shadowDy, shadowRadius = shadowRadius, autoLink = autoLink, linksClickable = linksClickable, freezesText = freezesText, ellipsize = ellipsize, drawableTop = drawableTop, drawableLeft = drawableLeft, drawableBottom = drawableBottom, drawableRight = drawableRight, drawablePadding = drawablePadding, drawableTint = drawableTint, drawableTintMode = drawableTintMode, lineHeight = lineHeight, firstBaselineToTopHeight = firstBaselineToTopHeight, lastBaselineToBottomHeight = lastBaselineToBottomHeight, marqueeRepeatLimit = marqueeRepeatLimit, inputType = inputType, imeOptions = imeOptions, privateImeOptions = privateImeOptions, editorExtras = editorExtras, textIsSelectable = textIsSelectable, textAllCaps = textAllCaps, elegantTextHeight = elegantTextHeight, fallbackLineSpacing = fallbackLineSpacing, letterSpacing = letterSpacing, fontFeatureSettings = fontFeatureSettings, breakStrategy = breakStrategy, hyphenationFrequency = hyphenationFrequency, autoSizeTextType = autoSizeTextType, justificationMode = justificationMode, View_clickable = View_clickable, View_longClickable = View_longClickable, id = id, tag = tag, scrollX = scrollX, scrollY = scrollY, background = background, paddingLeft = paddingLeft, paddingBottom = paddingBottom, paddingEnd = paddingEnd, paddingStart = paddingStart, paddingTop = paddingTop, paddingRight = paddingRight, focusable = focusable, autofillHints = autofillHints, importantForAutofill = importantForAutofill, focusableInTouchMode = focusableInTouchMode, visibility = visibility, fitsSystemWindows = fitsSystemWindows, scrollbarStyle = scrollbarStyle, isScrollContainer = isScrollContainer, fadeScrollbars = fadeScrollbars, scrollbarFadeDuration = scrollbarFadeDuration, scrollbarDefaultDelayBeforeFade = scrollbarDefaultDelayBeforeFade, scrollbarSize = scrollbarSize, nextFocusLeft = nextFocusLeft, nextFocusRight = nextFocusRight, nextFocusUp = nextFocusUp, nextFocusDown = nextFocusDown, nextFocusForward = nextFocusForward, clickable = clickable, longClickable = longClickable, contextClickable = contextClickable, saveEnabled = saveEnabled, filterTouchesWhenObscured = filterTouchesWhenObscured, drawingCacheQuality = drawingCacheQuality, keepScreenOn = keepScreenOn, minHeight = minHeight, minWidth = minWidth, soundEffectsEnabled = soundEffectsEnabled, hapticFeedbackEnabled = hapticFeedbackEnabled, contentDescription = contentDescription, accessibilityTraversalBefore = accessibilityTraversalBefore, accessibilityTraversalAfter = accessibilityTraversalAfter, overScrollMode = overScrollMode, alpha = alpha, elevation = elevation, translationX = translationX, translationY = translationY, translationZ = translationZ, transformPivotX = transformPivotX, transformPivotY = transformPivotY, rotation = rotation, rotationX = rotationX, rotationY = rotationY, scaleX = scaleX, scaleY = scaleY, verticalScrollbarPosition = verticalScrollbarPosition, layerType = layerType, layoutDirection = layoutDirection, textDirection = textDirection, textAlignment = textAlignment, importantForAccessibility = importantForAccessibility, accessibilityLiveRegion = accessibilityLiveRegion, labelFor = labelFor, transitionName = transitionName, nestedScrollingEnabled = nestedScrollingEnabled, backgroundTint = backgroundTint, backgroundTintMode = backgroundTintMode, foreground = foreground, foregroundGravity = foregroundGravity, foregroundTint = foregroundTint, foregroundTintMode = foregroundTintMode, scrollIndicators = scrollIndicators, forceHasOverlappingRendering = forceHasOverlappingRendering, tooltipText = tooltipText, keyboardNavigationCluster = keyboardNavigationCluster, nextClusterForward = nextClusterForward, focusedByDefault = focusedByDefault, defaultFocusHighlightEnabled = defaultFocusHighlightEnabled, screenReaderFocusable = screenReaderFocusable, accessibilityPaneTitle = accessibilityPaneTitle, accessibilityHeading = accessibilityHeading, outlineSpotShadowColor = outlineSpotShadowColor, outlineAmbientShadowColor = outlineAmbientShadowColor),
        IAttributes
