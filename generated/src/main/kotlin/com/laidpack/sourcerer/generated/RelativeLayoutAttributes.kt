package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.JsonClass
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class RelativeLayoutAttributes : ViewGroupAttributes(), IAttributes {
    var gravity: Int? = null

    companion object {
        init {
            SourcererService.registerAdapter(RelativeLayoutAttributes::class, RelativeLayoutAttributesJsonAdapter::class, "relativeLayout")
        }
    }
}
