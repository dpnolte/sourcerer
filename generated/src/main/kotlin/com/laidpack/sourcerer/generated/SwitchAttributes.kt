package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.ColorQualifier
import com.laidpack.sourcerer.service.api.DimensionQualifier
import com.laidpack.sourcerer.service.api.IAttributes
import com.laidpack.sourcerer.service.api.ReferenceQualifier
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class SwitchAttributes : ButtonAttributes(), IAttributes {
    var thumb: Int? = null

    var thumbTint: Int? = null

    var thumbTintMode: Int? = null

    @field:ReferenceQualifier
    var track: Int? = null

    @field:ColorQualifier
    var trackTint: Int? = null

    var trackTintMode: TrackTintModeEnum? = null

    var textOn: String? = null

    var textOff: String? = null

    @field:DimensionQualifier
    var thumbTextPadding: Int? = null

    @field:ReferenceQualifier
    var switchTextAppearance: Int? = null

    @field:DimensionQualifier
    var switchMinWidth: Int? = null

    @field:DimensionQualifier
    var switchPadding: Int? = null

    var splitTrack: Boolean? = null

    var showText: Boolean? = null

    companion object {
        init {
            SourcererService.registerAdapter(SwitchAttributes::class, SwitchAttributesJsonAdapter::class, "switch")
        }
    }
}

enum class TrackTintModeEnum(val attributeName: String, val value: Int) {
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
