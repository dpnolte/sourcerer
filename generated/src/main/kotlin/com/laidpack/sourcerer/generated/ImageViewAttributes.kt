package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.ColorQualifier
import com.laidpack.sourcerer.service.api.DimensionQualifier
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class ImageViewAttributes : ViewAttributes(), IAttributes {
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

    var tintMode: Int? = null

    companion object {
        init {
            SourcererService.registerAdapter(ImageViewAttributes::class, ImageViewAttributesJsonAdapter::class, "imageView")
        }
    }
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
