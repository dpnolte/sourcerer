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
    layout_height: MultiFormat = MultiFormat(setOf(Format.Dimension, Format.Enum))
) : FrameLayoutLayoutParamsAttributes(layout_gravity = layout_gravity, layout_width = layout_width, layout_height = layout_height),
        IAttributes

enum class LayoutCollapseModeEnum(override val key: String, override val value: Int) : AttributeEnum {
    @Json(name = "none")
    None("none", 0),

    @Json(name = "pin")
    Pin("pin", 1),

    @Json(name = "parallax")
    Parallax("parallax", 2),

    @Json(name = "collapse_mode_off")
    CollapseModeOff("collapse_mode_off", 0),

    @Json(name = "collapse_mode_pin")
    CollapseModePin("collapse_mode_pin", 1),

    @Json(name = "collapse_mode_parallax")
    CollapseModeParallax("collapse_mode_parallax", 2);
}
