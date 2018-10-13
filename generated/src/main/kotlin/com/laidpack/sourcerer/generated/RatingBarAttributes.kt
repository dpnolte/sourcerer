package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Float
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class RatingBarAttributes : AbsSeekBarAttributes(), IAttributes {
    var numStars: Int? = null

    var rating: Float? = null

    var stepSize: Float? = null

    var isIndicator: Boolean? = null
}
