package com.laidpack.sourcerer.generated.car

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.generated.FrameLayoutAttributes
import com.laidpack.sourcerer.services.api.DimensionQualifier
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.JsonClass
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class PagedListViewAttributes : FrameLayoutAttributes(), IAttributes {
    @field:DimensionQualifier
    var scrollBarContainerWidth: Int? = null
}
