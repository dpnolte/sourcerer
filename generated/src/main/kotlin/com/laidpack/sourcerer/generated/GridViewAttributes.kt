package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.services.api.DimensionQualifier
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class GridViewAttributes : AdapterViewAttributes(), IAttributes {
    @field:DimensionQualifier
    var horizontalSpacing: Int? = null

    @field:DimensionQualifier
    var verticalSpacing: Int? = null

    var stretchMode: StretchModeEnum? = null

    @field:DimensionQualifier
    var columnWidth: Int? = null

    var numColumns: NumColumnsEnum? = null

    var gravity: Int? = null
}

enum class StretchModeEnum(val attributeName: String, val value: Int) {
    @Json(name = "none")
    None("none", 0),

    @Json(name = "spacingWidth")
    SpacingWidth("spacingWidth", 1),

    @Json(name = "columnWidth")
    ColumnWidth("columnWidth", 2),

    @Json(name = "spacingWidthUniform")
    SpacingWidthUniform("spacingWidthUniform", 3),

    @Json(name = "no_stretch")
    NoStretch("no_stretch", 0),

    @Json(name = "stretch_spacing")
    StretchSpacing("stretch_spacing", 1),

    @Json(name = "stretch_column_width")
    StretchColumnWidth("stretch_column_width", 2),

    @Json(name = "stretch_spacing_uniform")
    StretchSpacingUniform("stretch_spacing_uniform", 3);
}

enum class NumColumnsEnum(val attributeName: String, val value: Int) {
    @Json(name = "auto_fit")
    AutoFit("auto_fit", -1);
}
