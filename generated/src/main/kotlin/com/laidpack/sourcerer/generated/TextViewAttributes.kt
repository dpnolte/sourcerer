package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.services.api.ColorQualifier
import com.laidpack.sourcerer.services.api.DimensionQualifier
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class TextViewAttributes : ViewAttributes(), IAttributes {
    var text: String? = null

    var hint: String? = null

    @field:ColorQualifier
    var textColor: Int? = null

    @field:ColorQualifier
    var textColorHighlight: Int? = null

    @field:ColorQualifier
    var textColorHint: Int? = null

    var textSize: Float? = null

    var textScaleX: Float? = null

    @field:ColorQualifier
    var textColorLink: Int? = null

    var cursorVisible: Boolean? = null

    var maxLines: Int? = null

    var maxHeight: Int? = null

    var lines: Int? = null

    @field:DimensionQualifier
    var height: Int? = null

    var minLines: Int? = null

    var maxEms: Int? = null

    var maxWidth: Int? = null

    var ems: Int? = null

    @field:DimensionQualifier
    var width: Int? = null

    var minEms: Int? = null

    var gravity: Int? = null

    var enabled: Boolean? = null

    var includeFontPadding: Boolean? = null

    var shadowColor: Int? = null

    var shadowDx: Float? = null

    var shadowDy: Float? = null

    var shadowRadius: Float? = null

    var autoLink: Int? = null

    var linksClickable: Boolean? = null

    var freezesText: Boolean? = null

    var ellipsize: Int? = null

    @field:DimensionQualifier
    var drawablePadding: Int? = null

    @field:ColorQualifier
    var drawableTint: Int? = null

    var drawableTintMode: DrawableTintModeEnum? = null

    @field:DimensionQualifier
    var lineHeight: Int? = null

    @field:DimensionQualifier
    var firstBaselineToTopHeight: Int? = null

    @field:DimensionQualifier
    var lastBaselineToBottomHeight: Int? = null

    var marqueeRepeatLimit: MarqueeRepeatLimitEnum? = null

    var inputType: Int? = null

    var imeOptions: Int? = null

    var privateImeOptions: String? = null

    var textAllCaps: Boolean? = null

    var elegantTextHeight: Boolean? = null

    var fallbackLineSpacing: Boolean? = null

    var letterSpacing: Float? = null

    var fontFeatureSettings: String? = null

    var breakStrategy: BreakStrategyEnum? = null

    var hyphenationFrequency: HyphenationFrequencyEnum? = null

    var autoSizeTextType: AutoSizeTextTypeEnum? = null
}

enum class DrawableTintModeEnum(val attributeName: String, val value: Int) {
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

enum class MarqueeRepeatLimitEnum(val attributeName: String, val value: Int) {
    @Json(name = "marquee_forever")
    MarqueeForever("marquee_forever", -1);
}

enum class BreakStrategyEnum(val attributeName: String, val value: Int) {
    @Json(name = "simple")
    Simple("simple", 0),

    @Json(name = "high_quality")
    HighQuality("high_quality", 1),

    @Json(name = "balanced")
    Balanced("balanced", 2);
}

enum class HyphenationFrequencyEnum(val attributeName: String, val value: Int) {
    @Json(name = "none")
    None("none", 0),

    @Json(name = "normal")
    Normal("normal", 1),

    @Json(name = "full")
    Full("full", 2);
}

enum class AutoSizeTextTypeEnum(val attributeName: String, val value: Int) {
    @Json(name = "none")
    None("none", 0),

    @Json(name = "uniform")
    Uniform("uniform", 1),

    @Json(name = "auto_size_text_type_none")
    AutoSizeTextTypeNone("auto_size_text_type_none", 0),

    @Json(name = "auto_size_text_type_uniform")
    AutoSizeTextTypeUniform("auto_size_text_type_uniform", 1);
}
