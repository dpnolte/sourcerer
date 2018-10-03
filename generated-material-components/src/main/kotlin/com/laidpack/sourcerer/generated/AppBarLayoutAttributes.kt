package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@TypeScript
open class AppBarLayoutAttributes : LinearLayoutAttributes(), IAttributes {
    companion object {
        init {
            SourcererService.registerAdapter(AppBarLayoutAttributes::class, AppBarLayoutAttributesJsonAdapter::class, "appBarLayout")
        }
    }
}
