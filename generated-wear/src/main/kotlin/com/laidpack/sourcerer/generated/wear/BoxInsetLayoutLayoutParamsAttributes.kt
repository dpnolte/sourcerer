package com.laidpack.sourcerer.generated.wear

import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import com.laidpack.sourcerer.services.api.AttributeEnum
import com.laidpack.sourcerer.services.api.FlagsAccumulator
import com.laidpack.sourcerer.services.api.FlagsQualifier
import com.laidpack.sourcerer.services.api.Format
import com.laidpack.sourcerer.services.api.IAttributes
import com.laidpack.sourcerer.services.api.MultiFormat
import com.laidpack.typescript.annotation.TypeScript
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class BoxInsetLayoutLayoutParamsAttributes(
    @field:FlagsQualifier(flagsType = BoxedEdgesFlagsEnum::class) val boxedEdges: FlagsAccumulator? = null,
    layout_width: MultiFormat = MultiFormat(setOf(Format.Dimension, Format.Enum)),
    layout_height: MultiFormat = MultiFormat(setOf(Format.Dimension, Format.Enum))
) : ViewGroupLayoutParamsAttributes(layout_width = layout_width, layout_height = layout_height),
        IAttributes

@TypeScript
enum class BoxedEdgesFlagsEnum(override val key: String, override val value: Int) : AttributeEnum {
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
