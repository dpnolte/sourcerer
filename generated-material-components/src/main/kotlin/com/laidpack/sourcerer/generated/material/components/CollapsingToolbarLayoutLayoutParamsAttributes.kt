package com.laidpack.sourcerer.generated.material.components

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.generated.FrameLayoutLayoutParamsAttributes
import com.laidpack.sourcerer.services.api.AttributeEnum
import com.laidpack.sourcerer.services.api.FlagsAccumulator
import com.laidpack.sourcerer.services.api.Format
import com.laidpack.sourcerer.services.api.IAttributes
import com.laidpack.sourcerer.services.api.MultiFormat
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Float
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class CollapsingToolbarLayoutLayoutParamsAttributes(
    val layout_collapseMode: LayoutCollapseModeEnum? = null,
    val layout_collapseParallaxMultiplier: Float? = null,
    layout_gravity: FlagsAccumulator? = null,
    layout_width: MultiFormat = MultiFormat(setOf(Format.Dimension, Format.Enum)),
    layout_height: MultiFormat = MultiFormat(setOf(Format.Dimension, Format.Enum)),
    layout_marginLeft: Int? = null,
    layout_marginBottom: Int? = null,
    layout_marginRight: Int? = null,
    layout_marginTop: Int? = null,
    layout_marginStart: Int? = null,
    layout_marginEnd: Int? = null
) : FrameLayoutLayoutParamsAttributes(layout_gravity = layout_gravity, layout_width = layout_width, layout_height = layout_height, layout_marginLeft = layout_marginLeft, layout_marginBottom = layout_marginBottom, layout_marginRight = layout_marginRight, layout_marginTop = layout_marginTop, layout_marginStart = layout_marginStart, layout_marginEnd = layout_marginEnd),
        IAttributes

enum class LayoutCollapseModeEnum(override val key: String, override val value: Int) : AttributeEnum {
    @Json(name = "none")
    None("none", 0),

    @Json(name = "pin")
    Pin("pin", 1),

    @Json(name = "parallax")
    Parallax("parallax", 2);
}
