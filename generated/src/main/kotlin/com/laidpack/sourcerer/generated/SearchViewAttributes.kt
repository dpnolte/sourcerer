package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.services.api.AttributeEnum
import com.laidpack.sourcerer.services.api.FlagsAccumulator
import com.laidpack.sourcerer.services.api.FlagsQualifier
import com.laidpack.sourcerer.services.api.Format
import com.laidpack.sourcerer.services.api.IAttributes
import com.laidpack.sourcerer.services.api.MultiFormat
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class SearchViewAttributes(
    val iconifiedByDefault: Boolean? = null,
    val maxWidth: Int? = null,
    val queryHint: String? = null,
    @field:FlagsQualifier(flagsType = ImeOptionsFlagsEnum::class) val imeOptions: FlagsAccumulator? = null,
    @field:FlagsQualifier(flagsType = InputTypeFlagsEnum::class) val inputType: FlagsAccumulator? = null,
    orientation: OrientationEnum? = null,
    gravity: FlagsAccumulator? = null,
    baselineAligned: Boolean? = null,
    baselineAlignedChildIndex: Int? = null,
    measureWithLargestChild: Boolean? = null,
    divider: Int? = null,
    showDividers: FlagsAccumulator? = null,
    dividerPadding: Int? = null,
    animateLayoutChanges: Boolean? = null,
    clipChildren: Boolean? = null,
    clipToPadding: Boolean? = null,
    animationCache: Boolean? = null,
    persistentDrawingCache: FlagsAccumulator? = null,
    alwaysDrawnWithCache: Boolean? = null,
    addStatesFromChildren: Boolean? = null,
    touchscreenBlocksFocus: Boolean? = null,
    splitMotionEvents: Boolean? = null,
    transitionGroup: Boolean? = null,
    id: Int? = null,
    scrollX: Int? = null,
    scrollY: Int? = null,
    background: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference)),
    focusable: MultiFormat = MultiFormat(setOf(Format.Boolean, Format.Enum)),
    autofillHints: MultiFormat = MultiFormat(setOf(Format.Reference, Format.String)),
    importantForAutofill: FlagsAccumulator? = null,
    visibility: VisibilityEnum? = null,
    fitsSystemWindows: Boolean? = null,
    scrollbarStyle: ScrollbarStyleEnum? = null,
    scrollbarFadeDuration: Int? = null,
    scrollbarDefaultDelayBeforeFade: Int? = null,
    scrollbarSize: Int? = null,
    nextFocusLeft: Int? = null,
    nextFocusRight: Int? = null,
    nextFocusUp: Int? = null,
    nextFocusDown: Int? = null,
    nextFocusForward: Int? = null,
    filterTouchesWhenObscured: Boolean? = null,
    drawingCacheQuality: DrawingCacheQualityEnum? = null,
    keepScreenOn: Boolean? = null,
    minHeight: Int? = null,
    minWidth: Int? = null,
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
    layerType: LayerTypeEnum? = null,
    layoutDirection: LayoutDirectionEnum? = null,
    textDirection: MultiFormat = MultiFormat(setOf(Format.Integer, Format.Enum)),
    textAlignment: TextAlignmentEnum? = null,
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
) : LinearLayoutAttributes(orientation = orientation, gravity = gravity, baselineAligned = baselineAligned, baselineAlignedChildIndex = baselineAlignedChildIndex, measureWithLargestChild = measureWithLargestChild, divider = divider, showDividers = showDividers, dividerPadding = dividerPadding, animateLayoutChanges = animateLayoutChanges, clipChildren = clipChildren, clipToPadding = clipToPadding, animationCache = animationCache, persistentDrawingCache = persistentDrawingCache, alwaysDrawnWithCache = alwaysDrawnWithCache, addStatesFromChildren = addStatesFromChildren, touchscreenBlocksFocus = touchscreenBlocksFocus, splitMotionEvents = splitMotionEvents, transitionGroup = transitionGroup, id = id, scrollX = scrollX, scrollY = scrollY, background = background, focusable = focusable, autofillHints = autofillHints, importantForAutofill = importantForAutofill, visibility = visibility, fitsSystemWindows = fitsSystemWindows, scrollbarStyle = scrollbarStyle, scrollbarFadeDuration = scrollbarFadeDuration, scrollbarDefaultDelayBeforeFade = scrollbarDefaultDelayBeforeFade, scrollbarSize = scrollbarSize, nextFocusLeft = nextFocusLeft, nextFocusRight = nextFocusRight, nextFocusUp = nextFocusUp, nextFocusDown = nextFocusDown, nextFocusForward = nextFocusForward, filterTouchesWhenObscured = filterTouchesWhenObscured, drawingCacheQuality = drawingCacheQuality, keepScreenOn = keepScreenOn, minHeight = minHeight, minWidth = minWidth, contentDescription = contentDescription, accessibilityTraversalBefore = accessibilityTraversalBefore, accessibilityTraversalAfter = accessibilityTraversalAfter, overScrollMode = overScrollMode, alpha = alpha, elevation = elevation, translationX = translationX, translationY = translationY, translationZ = translationZ, transformPivotX = transformPivotX, transformPivotY = transformPivotY, rotation = rotation, rotationX = rotationX, rotationY = rotationY, scaleX = scaleX, scaleY = scaleY, layerType = layerType, layoutDirection = layoutDirection, textDirection = textDirection, textAlignment = textAlignment, importantForAccessibility = importantForAccessibility, accessibilityLiveRegion = accessibilityLiveRegion, labelFor = labelFor, transitionName = transitionName, nestedScrollingEnabled = nestedScrollingEnabled, backgroundTint = backgroundTint, backgroundTintMode = backgroundTintMode, foreground = foreground, foregroundGravity = foregroundGravity, foregroundTint = foregroundTint, foregroundTintMode = foregroundTintMode, forceHasOverlappingRendering = forceHasOverlappingRendering, tooltipText = tooltipText, keyboardNavigationCluster = keyboardNavigationCluster, nextClusterForward = nextClusterForward, focusedByDefault = focusedByDefault, defaultFocusHighlightEnabled = defaultFocusHighlightEnabled, screenReaderFocusable = screenReaderFocusable, accessibilityPaneTitle = accessibilityPaneTitle, accessibilityHeading = accessibilityHeading, outlineSpotShadowColor = outlineSpotShadowColor, outlineAmbientShadowColor = outlineAmbientShadowColor),
        IAttributes

enum class ImeOptionsFlagsEnum(override val key: String, override val value: Int) : AttributeEnum {
    @Json(name = "normal")
    Normal("normal", 0),

    @Json(name = "actionUnspecified")
    ActionUnspecified("actionUnspecified", 0),

    @Json(name = "actionNone")
    ActionNone("actionNone", 1),

    @Json(name = "actionGo")
    ActionGo("actionGo", 2),

    @Json(name = "actionSearch")
    ActionSearch("actionSearch", 3),

    @Json(name = "actionSend")
    ActionSend("actionSend", 4),

    @Json(name = "actionNext")
    ActionNext("actionNext", 5),

    @Json(name = "actionDone")
    ActionDone("actionDone", 6),

    @Json(name = "actionPrevious")
    ActionPrevious("actionPrevious", 7),

    @Json(name = "flagNoPersonalizedLearning")
    FlagNoPersonalizedLearning("flagNoPersonalizedLearning", 16777216),

    @Json(name = "flagNoFullscreen")
    FlagNoFullscreen("flagNoFullscreen", 33554432),

    @Json(name = "flagNavigatePrevious")
    FlagNavigatePrevious("flagNavigatePrevious", 67108864),

    @Json(name = "flagNavigateNext")
    FlagNavigateNext("flagNavigateNext", 134217728),

    @Json(name = "flagNoExtractUi")
    FlagNoExtractUi("flagNoExtractUi", 268435456),

    @Json(name = "flagNoAccessoryAction")
    FlagNoAccessoryAction("flagNoAccessoryAction", 536870912),

    @Json(name = "flagNoEnterAction")
    FlagNoEnterAction("flagNoEnterAction", 1073741824);
}

enum class InputTypeFlagsEnum(override val key: String, override val value: Int) : AttributeEnum {
    @Json(name = "none")
    None("none", 0),

    @Json(name = "text")
    Text("text", 1),

    @Json(name = "textCapCharacters")
    TextCapCharacters("textCapCharacters", 4097),

    @Json(name = "textCapWords")
    TextCapWords("textCapWords", 8193),

    @Json(name = "textCapSentences")
    TextCapSentences("textCapSentences", 16385),

    @Json(name = "textAutoCorrect")
    TextAutoCorrect("textAutoCorrect", 32769),

    @Json(name = "textAutoComplete")
    TextAutoComplete("textAutoComplete", 65537),

    @Json(name = "textMultiLine")
    TextMultiLine("textMultiLine", 131073),

    @Json(name = "textImeMultiLine")
    TextImeMultiLine("textImeMultiLine", 262145),

    @Json(name = "textNoSuggestions")
    TextNoSuggestions("textNoSuggestions", 524289),

    @Json(name = "textUri")
    TextUri("textUri", 17),

    @Json(name = "textEmailAddress")
    TextEmailAddress("textEmailAddress", 33),

    @Json(name = "textEmailSubject")
    TextEmailSubject("textEmailSubject", 49),

    @Json(name = "textShortMessage")
    TextShortMessage("textShortMessage", 65),

    @Json(name = "textLongMessage")
    TextLongMessage("textLongMessage", 81),

    @Json(name = "textPersonName")
    TextPersonName("textPersonName", 97),

    @Json(name = "textPostalAddress")
    TextPostalAddress("textPostalAddress", 113),

    @Json(name = "textPassword")
    TextPassword("textPassword", 129),

    @Json(name = "textVisiblePassword")
    TextVisiblePassword("textVisiblePassword", 145),

    @Json(name = "textWebEditText")
    TextWebEditText("textWebEditText", 161),

    @Json(name = "textFilter")
    TextFilter("textFilter", 177),

    @Json(name = "textPhonetic")
    TextPhonetic("textPhonetic", 193),

    @Json(name = "textWebEmailAddress")
    TextWebEmailAddress("textWebEmailAddress", 209),

    @Json(name = "textWebPassword")
    TextWebPassword("textWebPassword", 225),

    @Json(name = "number")
    Number("number", 2),

    @Json(name = "numberSigned")
    NumberSigned("numberSigned", 4098),

    @Json(name = "numberDecimal")
    NumberDecimal("numberDecimal", 8194),

    @Json(name = "numberPassword")
    NumberPassword("numberPassword", 18),

    @Json(name = "phone")
    Phone("phone", 3),

    @Json(name = "datetime")
    Datetime("datetime", 4),

    @Json(name = "date")
    Date("date", 20),

    @Json(name = "time")
    Time("time", 36);
}
