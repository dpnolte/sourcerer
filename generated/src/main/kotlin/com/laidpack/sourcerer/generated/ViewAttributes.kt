package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SerializerComponent
import com.laidpack.sourcerer.service.api.ColorQualifier
import com.laidpack.sourcerer.service.api.DimensionQualifier
import com.laidpack.sourcerer.service.api.Format
import com.laidpack.sourcerer.service.api.IAttributes
import com.laidpack.sourcerer.service.api.MultiFormat
import com.laidpack.sourcerer.service.api.MultiFormatQualifier
import com.laidpack.sourcerer.service.api.ReferenceQualifier
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class ViewAttributes : IAttributes {
    @field:ReferenceQualifier
    var id: Int? = null

    @field:DimensionQualifier
    var scrollX: Int? = null

    @field:DimensionQualifier
    var scrollY: Int? = null

    @field:MultiFormatQualifier(formats = [Format.Color, Format.Reference])
    var background: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference))

    @field:DimensionQualifier
    var paddingLeft: Int? = null

    @field:DimensionQualifier
    var paddingTop: Int? = null

    @field:DimensionQualifier
    var paddingRight: Int? = null

    @field:DimensionQualifier
    var paddingBottom: Int? = null

    @field:DimensionQualifier
    var paddingStart: Int? = null

    @field:DimensionQualifier
    var paddingEnd: Int? = null

    @field:MultiFormatQualifier(formats = [Format.Boolean, Format.Enum])
    var focusable: MultiFormat = MultiFormat(setOf(Format.Boolean, Format.Enum))

    var autofillHints: MultiFormat = MultiFormat(setOf(Format.Reference, Format.String))

    var importantForAutofill: ImportantForAutofillFlagsEnum? = null

    var focusableInTouchMode: Boolean? = null

    var visibility: VisibilityEnum? = null

    var fitsSystemWindows: Boolean? = null

    var scrollbarStyle: ScrollbarStyleEnum? = null

    var isScrollContainer: Boolean? = null

    var fadeScrollbars: Boolean? = null

    var scrollbarFadeDuration: Int? = null

    var scrollbarDefaultDelayBeforeFade: Int? = null

    @field:DimensionQualifier
    var scrollbarSize: Int? = null

    var requiresFadingEdge: RequiresFadingEdgeFlagsEnum? = null

    @field:ReferenceQualifier
    var nextFocusLeft: Int? = null

    @field:ReferenceQualifier
    var nextFocusRight: Int? = null

    @field:ReferenceQualifier
    var nextFocusUp: Int? = null

    @field:ReferenceQualifier
    var nextFocusDown: Int? = null

    @field:ReferenceQualifier
    var nextFocusForward: Int? = null

    var clickable: Boolean? = null

    var longClickable: Boolean? = null

    var contextClickable: Boolean? = null

    var saveEnabled: Boolean? = null

    var filterTouchesWhenObscured: Boolean? = null

    var drawingCacheQuality: DrawingCacheQualityEnum? = null

    var keepScreenOn: Boolean? = null

    var minHeight: Int? = null

    var minWidth: Int? = null

    var soundEffectsEnabled: Boolean? = null

    var hapticFeedbackEnabled: Boolean? = null

    var contentDescription: String? = null

    var accessibilityTraversalBefore: Int? = null

    var accessibilityTraversalAfter: Int? = null

    var overScrollMode: OverScrollModeEnum? = null

    var alpha: Float? = null

    @field:DimensionQualifier
    var elevation: Int? = null

    @field:DimensionQualifier
    var translationX: Int? = null

    @field:DimensionQualifier
    var translationY: Int? = null

    @field:DimensionQualifier
    var translationZ: Int? = null

    @field:DimensionQualifier
    var transformPivotX: Int? = null

    @field:DimensionQualifier
    var transformPivotY: Int? = null

    var rotation: Float? = null

    var rotationX: Float? = null

    var rotationY: Float? = null

    var scaleX: Float? = null

    var scaleY: Float? = null

    var layerType: LayerTypeEnum? = null

    var layoutDirection: LayoutDirectionEnum? = null

    var textDirection: TextDirectionEnum? = null

    var textAlignment: TextAlignmentEnum? = null

    var importantForAccessibility: ImportantForAccessibilityEnum? = null

    var accessibilityLiveRegion: AccessibilityLiveRegionEnum? = null

    @field:ReferenceQualifier
    var labelFor: Int? = null

    var transitionName: String? = null

    var nestedScrollingEnabled: Boolean? = null

    @field:ColorQualifier
    var backgroundTint: Int? = null

    var backgroundTintMode: BackgroundTintModeEnum? = null

    @field:MultiFormatQualifier(formats = [Format.Color, Format.Reference])
    var foreground: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference))

    var foregroundGravity: ForegroundGravityFlagsEnum? = null

    @field:ColorQualifier
    var foregroundTint: Int? = null

    var foregroundTintMode: ForegroundTintModeEnum? = null

    var scrollIndicators: ScrollIndicatorsFlagsEnum? = null

    var forceHasOverlappingRendering: Boolean? = null

    var tooltipText: String? = null

    var keyboardNavigationCluster: Boolean? = null

    @field:ReferenceQualifier
    var nextClusterForward: Int? = null

    var focusedByDefault: Boolean? = null

    var defaultFocusHighlightEnabled: Boolean? = null

    var screenReaderFocusable: Boolean? = null

    var accessibilityPaneTitle: String? = null

    var accessibilityHeading: Boolean? = null

    @field:ColorQualifier
    var outlineSpotShadowColor: Int? = null

    @field:ColorQualifier
    var outlineAmbientShadowColor: Int? = null

    companion object {
        init {
            SerializerComponent.registerAutoGeneratedAdapter(ViewAttributes::class, {moshi -> ViewAttributesJsonAdapter(moshi as Moshi)}, "view")
        }
    }
}

enum class FocusableEnum(val attributeName: String, val value: Int) {
    @Json(name = "auto")
    Auto("auto", 16);
}

enum class ImportantForAutofillFlagsEnum(val attributeName: String, val value: Int) {
    @Json(name = "auto")
    Auto("auto", 0),

    @Json(name = "yes")
    Yes("yes", 1),

    @Json(name = "no")
    No("no", 2),

    @Json(name = "yesExcludeDescendants")
    YesExcludeDescendants("yesExcludeDescendants", 4),

    @Json(name = "noExcludeDescendants")
    NoExcludeDescendants("noExcludeDescendants", 8);
}

enum class VisibilityEnum(val attributeName: String, val value: Int) {
    @Json(name = "visible")
    Visible("visible", 0),

    @Json(name = "invisible")
    Invisible("invisible", 1),

    @Json(name = "gone")
    Gone("gone", 2);
}

enum class ScrollbarStyleEnum(val attributeName: String, val value: Int) {
    @Json(name = "insideOverlay")
    InsideOverlay("insideOverlay", 0),

    @Json(name = "insideInset")
    InsideInset("insideInset", 16777216),

    @Json(name = "outsideOverlay")
    OutsideOverlay("outsideOverlay", 33554432),

    @Json(name = "outsideInset")
    OutsideInset("outsideInset", 50331648);
}

enum class RequiresFadingEdgeFlagsEnum(val attributeName: String, val value: Int) {
    @Json(name = "none")
    None("none", 0),

    @Json(name = "horizontal")
    Horizontal("horizontal", 4096),

    @Json(name = "vertical")
    Vertical("vertical", 8192);
}

enum class DrawingCacheQualityEnum(val attributeName: String, val value: Int) {
    @Json(name = "auto")
    Auto("auto", 0),

    @Json(name = "low")
    Low("low", 1),

    @Json(name = "high")
    High("high", 2);
}

enum class OverScrollModeEnum(val attributeName: String, val value: Int) {
    @Json(name = "always")
    Always("always", 0),

    @Json(name = "ifContentScrolls")
    IfContentScrolls("ifContentScrolls", 1),

    @Json(name = "never")
    Never("never", 2);
}

enum class LayerTypeEnum(val attributeName: String, val value: Int) {
    @Json(name = "none")
    None("none", 0),

    @Json(name = "software")
    Software("software", 1),

    @Json(name = "hardware")
    Hardware("hardware", 2);
}

enum class LayoutDirectionEnum(val attributeName: String, val value: Int) {
    @Json(name = "ltr")
    Ltr("ltr", 0),

    @Json(name = "rtl")
    Rtl("rtl", 1),

    @Json(name = "inherit")
    Inherit("inherit", 2),

    @Json(name = "locale")
    Locale("locale", 3);
}

enum class TextDirectionEnum(val attributeName: String, val value: Int) {
    @Json(name = "inherit")
    Inherit("inherit", 0),

    @Json(name = "firstStrong")
    FirstStrong("firstStrong", 1),

    @Json(name = "anyRtl")
    AnyRtl("anyRtl", 2),

    @Json(name = "ltr")
    Ltr("ltr", 3),

    @Json(name = "rtl")
    Rtl("rtl", 4),

    @Json(name = "locale")
    Locale("locale", 5),

    @Json(name = "firstStrongLtr")
    FirstStrongLtr("firstStrongLtr", 6),

    @Json(name = "firstStrongRtl")
    FirstStrongRtl("firstStrongRtl", 7);
}

enum class TextAlignmentEnum(val attributeName: String, val value: Int) {
    @Json(name = "inherit")
    Inherit("inherit", 0),

    @Json(name = "gravity")
    Gravity("gravity", 1),

    @Json(name = "textStart")
    TextStart("textStart", 2),

    @Json(name = "textEnd")
    TextEnd("textEnd", 3),

    @Json(name = "center")
    Center("center", 4),

    @Json(name = "viewStart")
    ViewStart("viewStart", 5),

    @Json(name = "viewEnd")
    ViewEnd("viewEnd", 6);
}

enum class ImportantForAccessibilityEnum(val attributeName: String, val value: Int) {
    @Json(name = "auto")
    Auto("auto", 0),

    @Json(name = "yes")
    Yes("yes", 1),

    @Json(name = "no")
    No("no", 2),

    @Json(name = "noHideDescendants")
    NoHideDescendants("noHideDescendants", 4);
}

enum class AccessibilityLiveRegionEnum(val attributeName: String, val value: Int) {
    @Json(name = "none")
    None("none", 0),

    @Json(name = "polite")
    Polite("polite", 1),

    @Json(name = "assertive")
    Assertive("assertive", 2);
}

enum class BackgroundTintModeEnum(val attributeName: String, val value: Int) {
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

enum class ForegroundGravityFlagsEnum(val attributeName: String, val value: Int) {
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
    ClipHorizontal("clip_horizontal", 8);
}

enum class ForegroundTintModeEnum(val attributeName: String, val value: Int) {
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

enum class ScrollIndicatorsFlagsEnum(val attributeName: String, val value: Int) {
    @Json(name = "none")
    None("none", 0),

    @Json(name = "top")
    Top("top", 1),

    @Json(name = "bottom")
    Bottom("bottom", 2),

    @Json(name = "left")
    Left("left", 4),

    @Json(name = "right")
    Right("right", 8),

    @Json(name = "start")
    Start("start", 16),

    @Json(name = "end")
    End("end", 32);
}
