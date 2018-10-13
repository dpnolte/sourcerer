package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.JsonClass
import kotlin.Boolean

@JsonClass(generateAdapter = true)
@TypeScript
open class ScrollViewAttributes : FrameLayoutAttributes(), IAttributes {
    var fillViewport: Boolean? = null
}
