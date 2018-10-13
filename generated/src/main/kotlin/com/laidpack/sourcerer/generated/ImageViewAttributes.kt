package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.services.api.ColorQualifier
import com.laidpack.sourcerer.services.api.DimensionQualifier
import com.laidpack.sourcerer.services.api.Format
import com.laidpack.sourcerer.services.api.IAttributes
import com.laidpack.sourcerer.services.api.MultiFormat
import com.laidpack.sourcerer.services.api.MultiFormatQualifier
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class ImageViewAttributes : ViewAttributes(), IAttributes {
    @field:MultiFormatQualifier(formats = [Format.Color, Format.Reference])
    var src: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference))

    var scaleType: ScaleTypeEnum? = null

    var adjustViewBounds: Boolean? = null

    @field:DimensionQualifier
    var maxWidth: Int? = null

    @field:DimensionQualifier
    var maxHeight: Int? = null

    @field:ColorQualifier
    var tint: Int? = null

    var baselineAlignBottom: Boolean? = null

    var cropToPadding: Boolean? = null

    @field:DimensionQualifier
    var baseline: Int? = null

    var drawableAlpha: Int? = null
}

enum class ScaleTypeEnum(val attributeName: String, val value: Int) {
    @Json(name = "matrix")
    Matrix("matrix", 0),

    @Json(name = "fitXY")
    FitXY("fitXY", 1),

    @Json(name = "fitStart")
    FitStart("fitStart", 2),

    @Json(name = "fitCenter")
    FitCenter("fitCenter", 3),

    @Json(name = "fitEnd")
    FitEnd("fitEnd", 4),

    @Json(name = "center")
    Center("center", 5),

    @Json(name = "centerCrop")
    CenterCrop("centerCrop", 6),

    @Json(name = "centerInside")
    CenterInside("centerInside", 7);
}
