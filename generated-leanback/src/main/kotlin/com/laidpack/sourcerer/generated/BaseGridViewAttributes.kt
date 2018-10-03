package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@TypeScript
open class BaseGridViewAttributes : RecyclerViewAttributes(), IAttributes {
    companion object {
        init {
            SourcererService.registerAdapter(BaseGridViewAttributes::class, BaseGridViewAttributesJsonAdapter::class, "baseGridView")
        }
    }
}
