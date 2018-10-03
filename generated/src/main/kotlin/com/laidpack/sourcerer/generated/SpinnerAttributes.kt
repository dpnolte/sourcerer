package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.JsonClass
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class SpinnerAttributes : AdapterViewAttributes(), IAttributes {
    var popupBackground: Int? = null

    var dropDownWidth: Int? = null

    var gravity: Int? = null

    companion object {
        init {
            SourcererService.registerAdapter(SpinnerAttributes::class, SpinnerAttributesJsonAdapter::class, "spinner")
        }
    }
}
