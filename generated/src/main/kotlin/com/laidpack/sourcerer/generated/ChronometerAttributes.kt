package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class ChronometerAttributes : TextViewAttributes(), IAttributes {
    var format: String? = null

    var countDown: Boolean? = null
}
