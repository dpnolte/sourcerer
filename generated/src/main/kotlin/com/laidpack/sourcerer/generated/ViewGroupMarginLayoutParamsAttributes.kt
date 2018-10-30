package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.services.api.DimensionQualifier
import com.laidpack.sourcerer.services.api.Format
import com.laidpack.sourcerer.services.api.IAttributes
import com.laidpack.sourcerer.services.api.MultiFormat
import com.squareup.moshi.JsonClass
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class ViewGroupMarginLayoutParamsAttributes(
    @field:DimensionQualifier val layout_marginLeft: Int? = null,
    @field:DimensionQualifier val layout_marginBottom: Int? = null,
    @field:DimensionQualifier val layout_marginRight: Int? = null,
    @field:DimensionQualifier val layout_marginTop: Int? = null,
    @field:DimensionQualifier val layout_marginStart: Int? = null,
    @field:DimensionQualifier val layout_marginEnd: Int? = null,
    layout_width: MultiFormat = MultiFormat(setOf(Format.Dimension, Format.Enum)),
    layout_height: MultiFormat = MultiFormat(setOf(Format.Dimension, Format.Enum))
) : ViewGroupLayoutParamsAttributes(layout_width = layout_width, layout_height = layout_height),
        IAttributes
