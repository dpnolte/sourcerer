package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.squareup.moshi.JsonClass
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class TextClockAttributes : TextViewAttributes() {
    var format12Hour: String? = null

    var format24Hour: String? = null

    var timeZone: String? = null
}
