package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@TypeScript
open class SwipeDismissFrameLayoutAttributes : FrameLayoutAttributes(), IAttributes {
    companion object {
        init {
            SourcererService.registerAdapter(SwipeDismissFrameLayoutAttributes::class, SwipeDismissFrameLayoutAttributesJsonAdapter::class, "swipeDismissFrameLayout")
        }
    }
}
