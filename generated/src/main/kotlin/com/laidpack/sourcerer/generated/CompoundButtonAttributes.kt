package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.services.api.ColorQualifier
import com.laidpack.sourcerer.services.api.IAttributes
import com.laidpack.sourcerer.services.api.ReferenceQualifier
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class CompoundButtonAttributes : ButtonAttributes(), IAttributes {
    var checked: Boolean? = null

    @field:ReferenceQualifier
    var button: Int? = null

    @field:ColorQualifier
    var buttonTint: Int? = null
}
