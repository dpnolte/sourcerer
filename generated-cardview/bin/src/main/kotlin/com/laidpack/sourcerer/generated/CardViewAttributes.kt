package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class CardViewAttributes : FrameLayoutAttributes() {
    @field:ColorQualifier
    var cardBackgroundColor: Int? = null

    @field:DimensionQualifier
    var cardCornerRadius: Int? = null

    @field:DimensionQualifier
    var cardElevation: Int? = null

    @field:DimensionQualifier
    var cardMaxElevation: Int? = null

    var cardUseCompatPadding: Boolean? = null

    var cardPreventCornerOverlap: Boolean? = null

    @field:DimensionQualifier
    var contentPaddingLeft: Int? = null

    @field:DimensionQualifier
    var contentPaddingRight: Int? = null

    @field:DimensionQualifier
    var contentPaddingTop: Int? = null

    @field:DimensionQualifier
    var contentPaddingBottom: Int? = null
}
