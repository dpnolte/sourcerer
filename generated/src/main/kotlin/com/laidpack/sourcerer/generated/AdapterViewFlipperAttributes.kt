package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class AdapterViewFlipperAttributes : AdapterViewAttributes(), IAttributes {
    var flipInterval: Int? = null

    var autoStart: Boolean? = null
}
