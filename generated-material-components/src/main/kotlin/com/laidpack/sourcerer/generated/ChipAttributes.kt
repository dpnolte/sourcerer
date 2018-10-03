package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.ColorQualifier
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.JsonClass
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class ChipAttributes : ButtonAttributes(), IAttributes {
    @field:ColorQualifier
    var chipIconTint: Int? = null

    companion object {
        init {
            SourcererService.registerAdapter(ChipAttributes::class, ChipAttributesJsonAdapter::class, "chip")
        }
    }
}
