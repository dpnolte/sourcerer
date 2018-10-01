package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.squareup.moshi.JsonClass
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class ViewStubAttributes : ViewAttributes() {
    @field:ReferenceQualifier
    var layout: Int? = null

    @field:ReferenceQualifier
    var inflatedId: Int? = null
}
