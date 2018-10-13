package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.JsonClass
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class ToggleButtonAttributes : ButtonAttributes(), IAttributes {
    var textOn: String? = null

    var textOff: String? = null
}
