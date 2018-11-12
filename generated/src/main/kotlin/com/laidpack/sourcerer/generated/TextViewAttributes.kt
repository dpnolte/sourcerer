package com.laidpack.sourcerer.generated

import com.laidpack.sourcerer.services.api.AttributeEnum
import com.laidpack.sourcerer.services.api.ColorQualifier
import com.laidpack.sourcerer.services.api.DimensionQualifier
import com.laidpack.sourcerer.services.api.FlagsAccumulator
import com.laidpack.sourcerer.services.api.FlagsQualifier
import com.laidpack.sourcerer.services.api.Format
import com.laidpack.sourcerer.services.api.IAttributes
import com.laidpack.sourcerer.services.api.MultiFormat
import com.laidpack.sourcerer.services.api.MultiFormatQualifier
import com.laidpack.sourcerer.services.api.ReferenceQualifier
import com.laidpack.typescript.annotation.TypeScript
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class TextViewAttributes(
    val text: String? = null,
    val hint: String? = null,
    @field:MultiFormatQualifier(formats = [Format.Color, Format.Reference]) val textColor: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference)),
    @field:ColorQualifier val textColorHighlight: Int? = null,
    @field:MultiFormatQualifier(formats = [Format.Color, Format.Reference]) val textColorHint: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference)),
    @field:DimensionQualifier val textSize: Int? = null,
    val textScaleX: Float? = null,
    @field:MultiFormatQualifier(formats = [Format.Color, Format.Reference]) val textColorLink: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference)),
    val cursorVisible: Boolean? = null,
    val maxLines: Int? = null,
    @field:DimensionQualifier val maxHeight: Int? = null,
    val lines: Int? = null,
    @field:DimensionQualifier val height: Int? = null,
    val minLines: Int? = null,
    val maxEms: Int? = null,
    @field:DimensionQualifier val maxWidth: Int? = null,
    val ems: Int? = null,
    @field:DimensionQualifier val width: Int? = null,
    val minEms: Int? = null,
    @field:FlagsQualifier(flagsType = GravityFlagsEnum::class) val gravity: FlagsAccumulator? = null,
    val enabled: Boolean? = null,
    val includeFontPadding: Boolean? = null,
    @field:ColorQualifier val shadowColor: Int? = null,
    val shadowDx: Float? = null,
    val shadowDy: Float? = null,
    val shadowRadius: Float? = null,
    @field:FlagsQualifier(flagsType = AutoLinkFlagsEnum::class) val autoLink: FlagsAccumulator? = null,
    val linksClickable: Boolean? = null,
    val freezesText: Boolean? = null,
    val ellipsize: EllipsizeEnum? = null,
    @field:MultiFormatQualifier(formats = [Format.Color, Format.Reference]) val drawableTop: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference)),
    @field:MultiFormatQualifier(formats = [Format.Color, Format.Reference]) val drawableLeft: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference)),
    @field:MultiFormatQualifier(formats = [Format.Color, Format.Reference]) val drawableBottom: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference)),
    @field:MultiFormatQualifier(formats = [Format.Color, Format.Reference]) val drawableRight: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference)),
    @field:DimensionQualifier val drawablePadding: Int? = null,
    @field:ColorQualifier val drawableTint: Int? = null,
    val drawableTintMode: DrawableTintModeEnum? = null,
    @field:DimensionQualifier val lineHeight: Int? = null,
    @field:DimensionQualifier val firstBaselineToTopHeight: Int? = null,
    @field:DimensionQualifier val lastBaselineToBottomHeight: Int? = null,
    @field:MultiFormatQualifier(formats = [Format.Integer, Format.Enum], enumType = MarqueeRepeatLimitEnum::class) val marqueeRepeatLimit: MultiFormat = MultiFormat(setOf(Format.Integer, Format.Enum)),
    @field:FlagsQualifier(flagsType = InputTypeFlagsEnum::class) val inputType: FlagsAccumulator? = null,
    @field:FlagsQualifier(flagsType = ImeOptionsFlagsEnum::class) val imeOptions: FlagsAccumulator? = null,
    val privateImeOptions: String? = null,
    @field:ReferenceQualifier val editorExtras: Int? = null,
    val textIsSelectable: Boolean? = null,
    val textAllCaps: Boolean? = null,
    val elegantTextHeight: Boolean? = null,
    val fallbackLineSpacing: Boolean? = null,
    val letterSpacing: Float? = null,
    val fontFeatureSettings: String? = null,
    val breakStrategy: BreakStrategyEnum? = null,
    val hyphenationFrequency: HyphenationFrequencyEnum? = null,
    val autoSizeTextType: AutoSizeTextTypeEnum? = null,
    val justificationMode: JustificationModeEnum? = null,
    val View_clickable: Boolean? = null,
    val View_longClickable: Boolean? = null,
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
) : ViewAttributes(id = id, tag = tag, scrollX = scrollX, scrollY = scrollY, background = background, paddingLeft = paddingLeft, paddingBottom = paddingBottom, paddingEnd = paddingEnd, paddingStart = paddingStart, paddingTop = paddingTop, paddingRight = paddingRight, focusable = focusable, autofillHints = autofillHints, importantForAutofill = importantForAutofill, focusableInTouchMode = focusableInTouchMode, visibility = visibility, fitsSystemWindows = fitsSystemWindows, scrollbarStyle = scrollbarStyle, isScrollContainer = isScrollContainer, fadeScrollbars = fadeScrollbars, scrollbarFadeDuration = scrollbarFadeDuration, scrollbarDefaultDelayBeforeFade = scrollbarDefaultDelayBeforeFade, scrollbarSize = scrollbarSize, nextFocusLeft = nextFocusLeft, nextFocusRight = nextFocusRight, nextFocusUp = nextFocusUp, nextFocusDown = nextFocusDown, nextFocusForward = nextFocusForward, clickable = clickable, longClickable = longClickable, contextClickable = contextClickable, saveEnabled = saveEnabled, filterTouchesWhenObscured = filterTouchesWhenObscured, drawingCacheQuality = drawingCacheQuality, keepScreenOn = keepScreenOn, minHeight = minHeight, minWidth = minWidth, soundEffectsEnabled = soundEffectsEnabled, hapticFeedbackEnabled = hapticFeedbackEnabled, contentDescription = contentDescription, accessibilityTraversalBefore = accessibilityTraversalBefore, accessibilityTraversalAfter = accessibilityTraversalAfter, overScrollMode = overScrollMode, alpha = alpha, elevation = elevation, translationX = translationX, translationY = translationY, translationZ = translationZ, transformPivotX = transformPivotX, transformPivotY = transformPivotY, rotation = rotation, rotationX = rotationX, rotationY = rotationY, scaleX = scaleX, scaleY = scaleY, verticalScrollbarPosition = verticalScrollbarPosition, layerType = layerType, layoutDirection = layoutDirection, textDirection = textDirection, textAlignment = textAlignment, importantForAccessibility = importantForAccessibility, accessibilityLiveRegion = accessibilityLiveRegion, labelFor = labelFor, transitionName = transitionName, nestedScrollingEnabled = nestedScrollingEnabled, backgroundTint = backgroundTint, backgroundTintMode = backgroundTintMode, foreground = foreground, foregroundGravity = foregroundGravity, foregroundTint = foregroundTint, foregroundTintMode = foregroundTintMode, scrollIndicators = scrollIndicators, forceHasOverlappingRendering = forceHasOverlappingRendering, tooltipText = tooltipText, keyboardNavigationCluster = keyboardNavigationCluster, nextClusterForward = nextClusterForward, focusedByDefault = focusedByDefault, defaultFocusHighlightEnabled = defaultFocusHighlightEnabled, screenReaderFocusable = screenReaderFocusable, accessibilityPaneTitle = accessibilityPaneTitle, accessibilityHeading = accessibilityHeading, outlineSpotShadowColor = outlineSpotShadowColor, outlineAmbientShadowColor = outlineAmbientShadowColor),
        IAttributes

@TypeScript
enum class GravityFlagsEnum(override val key: String, override val value: Int) : AttributeEnum {
    @Json(name = "top")
    Top("top", 48),

    @Json(name = "bottom")
    Bottom("bottom", 80),

    @Json(name = "left")
    Left("left", 3),

    @Json(name = "right")
    Right("right", 5),

    @Json(name = "center_vertical")
    CenterVertical("center_vertical", 16),

    @Json(name = "fill_vertical")
    FillVertical("fill_vertical", 112),

    @Json(name = "center_horizontal")
    CenterHorizontal("center_horizontal", 1),

    @Json(name = "fill_horizontal")
    FillHorizontal("fill_horizontal", 7),

    @Json(name = "center")
    Center("center", 17),

    @Json(name = "fill")
    Fill("fill", 119),

    @Json(name = "clip_vertical")
    ClipVertical("clip_vertical", 128),

    @Json(name = "clip_horizontal")
    ClipHorizontal("clip_horizontal", 8),

    @Json(name = "start")
    Start("start", 8388611),

    @Json(name = "end")
    End("end", 8388613);
}

@TypeScript
enum class AutoLinkFlagsEnum(override val key: String, override val value: Int) : AttributeEnum {
    @Json(name = "none")
    None("none", 0),

    @Json(name = "web")
    Web("web", 1),

    @Json(name = "email")
    Email("email", 2),

    @Json(name = "phone")
    Phone("phone", 4),

    @Json(name = "map")
    Map("map", 8),

    @Json(name = "all")
    All("all", 15);
}

@TypeScript
enum class EllipsizeEnum(override val key: String, override val value: Int) : AttributeEnum {
    @Json(name = "none")
    None("none", 0),

    @Json(name = "start")
    Start("start", 1),

    @Json(name = "middle")
    Middle("middle", 2),

    @Json(name = "end")
    End("end", 3),

    @Json(name = "marquee")
    Marquee("marquee", 4);
}

@TypeScript
enum class DrawableTintModeEnum(override val key: String, override val value: Int) : AttributeEnum {
    @Json(name = "src_over")
    SrcOver("src_over", 3),

    @Json(name = "src_in")
    SrcIn("src_in", 5),

    @Json(name = "src_atop")
    SrcAtop("src_atop", 9),

    @Json(name = "multiply")
    Multiply("multiply", 14),

    @Json(name = "screen")
    Screen("screen", 15),

    @Json(name = "add")
    Add("add", 16);
}

@TypeScript
enum class MarqueeRepeatLimitEnum(override val key: String, override val value: Int) : AttributeEnum {
    @Json(name = "marquee_forever")
    MarqueeForever("marquee_forever", -1);
}

@TypeScript
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

@TypeScript
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

@TypeScript
enum class BreakStrategyEnum(override val key: String, override val value: Int) : AttributeEnum {
    @Json(name = "simple")
    Simple("simple", 0),

    @Json(name = "high_quality")
    HighQuality("high_quality", 1),

    @Json(name = "balanced")
    Balanced("balanced", 2);
}

@TypeScript
enum class HyphenationFrequencyEnum(override val key: String, override val value: Int) : AttributeEnum {
    @Json(name = "none")
    None("none", 0),

    @Json(name = "normal")
    Normal("normal", 1),

    @Json(name = "full")
    Full("full", 2);
}

@TypeScript
enum class AutoSizeTextTypeEnum(override val key: String, override val value: Int) : AttributeEnum {
    @Json(name = "none")
    None("none", 0),

    @Json(name = "uniform")
    Uniform("uniform", 1);
}

@TypeScript
enum class JustificationModeEnum(override val key: String, override val value: Int) : AttributeEnum {
    @Json(name = "none")
    None("none", 0),

    @Json(name = "inter_word")
    InterWord("inter_word", 1);
}
