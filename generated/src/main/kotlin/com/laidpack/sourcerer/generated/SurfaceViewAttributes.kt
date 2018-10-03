package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@TypeScript
open class SurfaceViewAttributes : ViewAttributes(), IAttributes {
    companion object {
        init {
            SourcererService.registerAdapter(SurfaceViewAttributes::class, SurfaceViewAttributesJsonAdapter::class, "surfaceView")
        }
    }
}
