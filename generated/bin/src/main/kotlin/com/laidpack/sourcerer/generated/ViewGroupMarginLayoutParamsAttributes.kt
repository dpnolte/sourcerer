package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.squareup.moshi.JsonClass
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class ViewGroupMarginLayoutParamsAttributes : ViewGroupLayoutParamsAttributes() {
    @field:DimensionQualifier
    var layout_marginLeft: Int? = null

    @field:DimensionQualifier
    var layout_marginTop: Int? = null

    @field:DimensionQualifier
    var layout_marginRight: Int? = null

    @field:DimensionQualifier
    var layout_marginBottom: Int? = null

    @field:DimensionQualifier
    var layout_marginStart: Int? = null

    @field:DimensionQualifier
    var layout_marginEnd: Int? = null
}
