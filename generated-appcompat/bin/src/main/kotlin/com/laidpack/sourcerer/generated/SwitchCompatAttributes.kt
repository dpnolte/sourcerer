package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class SwitchCompatAttributes : ButtonAttributes() {
    @field:ColorQualifier
    var thumbTint: Int? = null

    var thumbTintMode: ThumbTintModeEnum? = null

    @field:ReferenceQualifier
    var track: Int? = null

    @field:ColorQualifier
    var trackTint: Int? = null

    var trackTintMode: TrackTintModeEnum? = null

    @field:DimensionQualifier
    var thumbTextPadding: Int? = null

    @field:DimensionQualifier
    var switchMinWidth: Int? = null

    @field:DimensionQualifier
    var switchPadding: Int? = null

    var splitTrack: Boolean? = null

    var showText: Boolean? = null
}

enum class ThumbTintModeEnum(val attributeName: String, val value: Int) {
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

enum class TrackTintModeEnum_(val attributeName: String, val value: Int) {
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
