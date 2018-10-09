package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SerializerComponent
import com.laidpack.sourcerer.service.api.DimensionQualifier
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
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

    companion object {
        init {
            SerializerComponent.registerAutoGeneratedAdapter(GridViewAttributes::class, {moshi -> GridViewAttributesJsonAdapter(moshi as Moshi)}, "gridView")
            SerializerComponent.associateThisViewGroupWithLayoutParams("gridView", "android.view.ViewGroup.LayoutParams")
        }
    }
}

enum class StretchModeEnum(val attributeName: String, val value: Int) {
    @Json(name = "none")
    None("none", 0),

    @Json(name = "spacingWidth")
    SpacingWidth("spacingWidth", 1),

    @Json(name = "columnWidth")
    ColumnWidth("columnWidth", 2),

    @Json(name = "spacingWidthUniform")
    SpacingWidthUniform("spacingWidthUniform", 3);
}

enum class NumColumnsEnum(val attributeName: String, val value: Int) {
    @Json(name = "auto_fit")
    AutoFit("auto_fit", -1);
}