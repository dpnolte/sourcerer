package com.laidpack.sourcerer.generated.material.components

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.generated.FrameLayoutAttributes
import com.laidpack.sourcerer.services.api.ColorQualifier
import com.laidpack.sourcerer.services.api.DimensionQualifier
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.JsonClass
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class NavigationViewAttributes : FrameLayoutAttributes(), IAttributes {
    @field:ColorQualifier
    var itemIconTint: Int? = null

    @field:ColorQualifier
    var itemTextColor: Int? = null

    @field:DimensionQualifier
    var itemHorizontalPadding: Int? = null

    @field:DimensionQualifier
    var itemIconPadding: Int? = null
}
