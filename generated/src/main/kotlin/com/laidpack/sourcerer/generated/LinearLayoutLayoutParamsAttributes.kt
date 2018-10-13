package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.JsonClass
import kotlin.Float
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class LinearLayoutLayoutParamsAttributes : ViewGroupLayoutParamsAttributes(), IAttributes {
    var layout_weight: Float? = null

    var layout_gravity: Int? = null
}
