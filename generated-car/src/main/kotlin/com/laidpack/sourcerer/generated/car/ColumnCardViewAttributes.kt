package com.laidpack.sourcerer.generated.car

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.generated.FrameLayoutAttributes
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.JsonClass
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class ColumnCardViewAttributes : FrameLayoutAttributes(), IAttributes {
    var columnSpan: Int? = null
}
