package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@TypeScript
open class SliceViewAttributes : ViewGroupAttributes(), IAttributes {
    companion object {
        init {
            SourcererService.registerAdapter(SliceViewAttributes::class, SliceViewAttributesJsonAdapter::class, "sliceView")
        }
    }
}
