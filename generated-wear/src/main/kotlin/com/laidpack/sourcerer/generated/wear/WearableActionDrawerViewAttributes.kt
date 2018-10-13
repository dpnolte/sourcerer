package com.laidpack.sourcerer.generated.wear

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.generated.FrameLayoutAttributes
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.JsonClass
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class WearableActionDrawerViewAttributes : FrameLayoutAttributes(), IAttributes {
    var drawerTitle: String? = null
}
