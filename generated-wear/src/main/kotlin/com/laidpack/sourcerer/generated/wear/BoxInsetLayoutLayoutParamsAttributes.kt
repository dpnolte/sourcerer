package com.laidpack.sourcerer.generated.wear

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class BoxInsetLayoutLayoutParamsAttributes : ViewGroupLayoutParamsAttributes(), IAttributes {
    var boxedEdges: BoxedEdgesFlagsEnum? = null
}

enum class BoxedEdgesFlagsEnum(val attributeName: String, val value: Int) {
    @Json(name = "none")
    None("none", 0),

    @Json(name = "left")
    Left("left", 1),

    @Json(name = "top")
    Top("top", 2),

    @Json(name = "right")
    Right("right", 4),

    @Json(name = "bottom")
    Bottom("bottom", 8),

    @Json(name = "all")
    All("all", 15);
}
