package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.JsonClass
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class TextClockAttributes : TextViewAttributes(), IAttributes {
    var format12Hour: String? = null

    var format24Hour: String? = null

    var timeZone: String? = null

    companion object {
        init {
            SourcererService.registerAdapter(TextClockAttributes::class, TextClockAttributesJsonAdapter::class, "textClock")
        }
    }
}
