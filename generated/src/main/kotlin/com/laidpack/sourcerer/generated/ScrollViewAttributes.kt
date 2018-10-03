package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.JsonClass
import kotlin.Boolean

@JsonClass(generateAdapter = true)
@TypeScript
open class ScrollViewAttributes : FrameLayoutAttributes(), IAttributes {
    var fillViewport: Boolean? = null

    companion object {
        init {
            SourcererService.registerAdapter(ScrollViewAttributes::class, ScrollViewAttributesJsonAdapter::class, "scrollView")
        }
    }
}
