package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class GridLayoutAttributes : ViewGroupAttributes(), IAttributes {
    var orientation: OrientationEnum? = null

    var rowCount: Int? = null

    var columnCount: Int? = null

    var useDefaultMargins: Boolean? = null

    var alignmentMode: AlignmentModeEnum? = null

    var rowOrderPreserved: Boolean? = null

    var columnOrderPreserved: Boolean? = null
}

enum class OrientationEnum_(val attributeName: String, val value: Int) {
    @Json(name = "horizontal")
    Horizontal("horizontal", 0),

    @Json(name = "vertical")
    Vertical("vertical", 1);
}

enum class AlignmentModeEnum(val attributeName: String, val value: Int) {
    @Json(name = "align_bounds")
    AlignBounds("align_bounds", 0),

    @Json(name = "align_margins")
    AlignMargins("align_margins", 1);
}
