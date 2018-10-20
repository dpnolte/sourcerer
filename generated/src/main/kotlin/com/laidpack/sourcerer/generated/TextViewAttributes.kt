package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
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
    val textScaleX: Float? = null,
    @field:MultiFormatQualifier(formats = [Format.Color, Format.Reference]) val textColorLink: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference)),
    val maxLines: Int? = null,
    val maxHeight: Int? = null,
    val lines: Int? = null,
    @field:DimensionQualifier val height: Int? = null,
    val minLines: Int? = null,
    val maxEms: Int? = null,
    val maxWidth: Int? = null,
    val ems: Int? = null,
    @field:DimensionQualifier val width: Int? = null,
    val minEms: Int? = null,
    @field:FlagsQualifier(flagsType = GravityFlagsEnum::class) val gravity: FlagsAccumulator? = null,
    val enabled: Boolean? = null,
    val includeFontPadding: Boolean? = null,
    val shadowColor: Int? = null,
    val shadowDx: Float? = null,
    val shadowDy: Float? = null,
    val shadowRadius: Float? = null,
    @field:FlagsQualifier(flagsType = AutoLinkFlagsEnum::class) val autoLink: FlagsAccumulator? = null,
    val linksClickable: Boolean? = null,
    val freezesText: Boolean? = null,
    val ellipsize: EllipsizeEnum? = null,
    @field:MultiFormatQualifier(formats = [Format.Color, Format.Reference]) val drawableTop: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference)),
    @field:MultiFormatQualifier(formats = [Format.Color, Format.Reference]) val drawableLeft: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference)),
    @field:MultiFormatQualifier(formats = [Format.Color, Format.Reference]) val drawableRight: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference)),
    @field:MultiFormatQualifier(formats = [Format.Color, Format.Reference]) val drawableBottom: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference)),
    @field:DimensionQualifier val drawablePadding: Int? = null,
    @field:ColorQualifier val drawableTint: Int? = null,
    val drawableTintMode: DrawableTintModeEnum? = null,
    @field:DimensionQualifier val lineHeight: Int? = null,
    @field:DimensionQualifier val firstBaselineToTopHeight: Int? = null,
    @field:DimensionQualifier val lastBaselineToBottomHeight: Int? = null,
    @field:MultiFormatQualifier(formats = [Format.Integer, Format.Enum], enumType = MarqueeRepeatLimitEnum::class) val marqueeRepeatLimit: MultiFormat = MultiFormat(setOf(Format.Integer, Format.Enum)),
    val privateImeOptions: String? = null,
    @field:ReferenceQualifier val editorExtras: Int? = null,
    val textIsSelectable: Boolean? = null,
    val fallbackLineSpacing: Boolean? = null,
    val breakStrategy: BreakStrategyEnum? = null,
    val hyphenationFrequency: HyphenationFrequencyEnum? = null,
    val autoSizeTextType: AutoSizeTextTypeEnum? = null,
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
) : ViewAttributes(id = id, scrollX = scrollX, scrollY = scrollY, background = background, focusable = focusable, autofillHints = autofillHints, importantForAutofill = importantForAutofill, visibility = visibility, fitsSystemWindows = fitsSystemWindows, scrollbarStyle = scrollbarStyle, scrollbarFadeDuration = scrollbarFadeDuration, scrollbarDefaultDelayBeforeFade = scrollbarDefaultDelayBeforeFade, scrollbarSize = scrollbarSize, nextFocusLeft = nextFocusLeft, nextFocusRight = nextFocusRight, nextFocusUp = nextFocusUp, nextFocusDown = nextFocusDown, nextFocusForward = nextFocusForward, filterTouchesWhenObscured = filterTouchesWhenObscured, drawingCacheQuality = drawingCacheQuality, keepScreenOn = keepScreenOn, minHeight = minHeight, minWidth = minWidth, contentDescription = contentDescription, accessibilityTraversalBefore = accessibilityTraversalBefore, accessibilityTraversalAfter = accessibilityTraversalAfter, overScrollMode = overScrollMode, alpha = alpha, elevation = elevation, translationX = translationX, translationY = translationY, translationZ = translationZ, transformPivotX = transformPivotX, transformPivotY = transformPivotY, rotation = rotation, rotationX = rotationX, rotationY = rotationY, scaleX = scaleX, scaleY = scaleY, layerType = layerType, layoutDirection = layoutDirection, textDirection = textDirection, textAlignment = textAlignment, importantForAccessibility = importantForAccessibility, accessibilityLiveRegion = accessibilityLiveRegion, labelFor = labelFor, transitionName = transitionName, nestedScrollingEnabled = nestedScrollingEnabled, backgroundTint = backgroundTint, backgroundTintMode = backgroundTintMode, foreground = foreground, foregroundGravity = foregroundGravity, foregroundTint = foregroundTint, foregroundTintMode = foregroundTintMode, forceHasOverlappingRendering = forceHasOverlappingRendering, tooltipText = tooltipText, keyboardNavigationCluster = keyboardNavigationCluster, nextClusterForward = nextClusterForward, focusedByDefault = focusedByDefault, defaultFocusHighlightEnabled = defaultFocusHighlightEnabled, screenReaderFocusable = screenReaderFocusable, accessibilityPaneTitle = accessibilityPaneTitle, accessibilityHeading = accessibilityHeading, outlineSpotShadowColor = outlineSpotShadowColor, outlineAmbientShadowColor = outlineAmbientShadowColor),
        IAttributes

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

enum class MarqueeRepeatLimitEnum(override val key: String, override val value: Int) : AttributeEnum {
    @Json(name = "marquee_forever")
    MarqueeForever("marquee_forever", -1);
}

enum class BreakStrategyEnum(override val key: String, override val value: Int) : AttributeEnum {
    @Json(name = "simple")
    Simple("simple", 0),

    @Json(name = "high_quality")
    HighQuality("high_quality", 1),

    @Json(name = "balanced")
    Balanced("balanced", 2);
}

enum class HyphenationFrequencyEnum(override val key: String, override val value: Int) : AttributeEnum {
    @Json(name = "none")
    None("none", 0),

    @Json(name = "normal")
    Normal("normal", 1),

    @Json(name = "full")
    Full("full", 2);
}

enum class AutoSizeTextTypeEnum(override val key: String, override val value: Int) : AttributeEnum {
    @Json(name = "none")
    None("none", 0),

    @Json(name = "uniform")
    Uniform("uniform", 1),

    @Json(name = "auto_size_text_type_none")
    AutoSizeTextTypeNone("auto_size_text_type_none", 0),

    @Json(name = "auto_size_text_type_uniform")
    AutoSizeTextTypeUniform("auto_size_text_type_uniform", 1);
}
