package com.laidpack.sourcerer.generated

import com.laidpack.sourcerer.services.api.Format
import com.laidpack.sourcerer.services.api.IAttributes
import com.laidpack.sourcerer.services.api.MultiFormat
import com.laidpack.typescript.annotation.TypeScript
import com.squareup.moshi.JsonClass
import kotlin.Float
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class RadioGroupLayoutParamsAttributes(
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
