package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.services.api.IAttributes
import com.laidpack.sourcerer.services.api.ReferenceQualifier
import com.squareup.moshi.JsonClass
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class ViewStubAttributes : ViewAttributes(), IAttributes {
    @field:ReferenceQualifier
    var layout: Int? = null

    @field:ReferenceQualifier
    var inflatedId: Int? = null
}
