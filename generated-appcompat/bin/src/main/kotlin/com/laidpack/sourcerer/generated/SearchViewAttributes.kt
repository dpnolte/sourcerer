package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class SearchViewAttributes : ViewGroupAttributes() {
    var iconifiedByDefault: Boolean? = null

    var queryHint: String? = null
}
