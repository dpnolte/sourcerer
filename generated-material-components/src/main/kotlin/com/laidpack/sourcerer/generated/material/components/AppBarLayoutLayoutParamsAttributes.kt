package com.laidpack.sourcerer.generated.material.components

import com.laidpack.sourcerer.generated.LinearLayoutLayoutParamsAttributes
import com.laidpack.sourcerer.services.api.AttributeEnum
import com.laidpack.sourcerer.services.api.FlagsAccumulator
import com.laidpack.sourcerer.services.api.FlagsQualifier
import com.laidpack.sourcerer.services.api.Format
import com.laidpack.sourcerer.services.api.IAttributes
import com.laidpack.sourcerer.services.api.MultiFormat
import com.laidpack.sourcerer.services.api.ReferenceQualifier
import com.laidpack.typescript.annotation.TypeScript
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Float
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class AppBarLayoutLayoutParamsAttributes(
    @field:FlagsQualifier(flagsType = LayoutScrollFlagsFlagsEnum::class) val layout_scrollFlags: FlagsAccumulator? = null,
    @field:ReferenceQualifier val layout_scrollInterpolator: Int? = null,
    layout_weight: Float? = null,
    layout_gravity: MultiFormat = MultiFormat(setOf(Format.Integer, Format.Flags)),
    layout_width: MultiFormat = MultiFormat(setOf(Format.Dimension, Format.Enum)),
    layout_height: MultiFormat = MultiFormat(setOf(Format.Dimension, Format.Enum)),
    layout_marginLeft: Int? = null,
    layout_marginBottom: Int? = null,
    layout_marginRight: Int? = null,
    layout_marginTop: Int? = null,
    layout_marginStart: Int? = null,
    layout_marginEnd: Int? = null
) : LinearLayoutLayoutParamsAttributes(layout_weight = layout_weight, layout_gravity = layout_gravity, layout_width = layout_width, layout_height = layout_height, layout_marginLeft = layout_marginLeft, layout_marginBottom = layout_marginBottom, layout_marginRight = layout_marginRight, layout_marginTop = layout_marginTop, layout_marginStart = layout_marginStart, layout_marginEnd = layout_marginEnd),
        IAttributes

@TypeScript
enum class LayoutScrollFlagsFlagsEnum(override val key: String, override val value: Int) : AttributeEnum {
    @Json(name = "scroll")
    Scroll("scroll", 1),

    @Json(name = "exitUntilCollapsed")
    ExitUntilCollapsed("exitUntilCollapsed", 2),

    @Json(name = "enterAlways")
    EnterAlways("enterAlways", 4),

    @Json(name = "enterAlwaysCollapsed")
    EnterAlwaysCollapsed("enterAlwaysCollapsed", 8),

    @Json(name = "snap")
    Snap("snap", 16),

    @Json(name = "snapMargins")
    SnapMargins("snapMargins", 32);
}
