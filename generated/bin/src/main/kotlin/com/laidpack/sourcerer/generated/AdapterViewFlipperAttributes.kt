package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.squareup.moshi.JsonClass
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class AdapterViewFlipperAttributes : AdapterViewAttributes() {
    var flipInterval: Int? = null
}
