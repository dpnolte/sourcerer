package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.squareup.moshi.JsonClass
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class NavigationViewAttributes : FrameLayoutAttributes() {
    @field:ColorQualifier
    var itemIconTint: Int? = null

    @field:ColorQualifier
    var itemTextColor: Int? = null

    @field:ReferenceQualifier
    var itemBackground: Int? = null

    @field:DimensionQualifier
    var itemHorizontalPadding: Int? = null

    @field:DimensionQualifier
    var itemIconPadding: Int? = null
}
