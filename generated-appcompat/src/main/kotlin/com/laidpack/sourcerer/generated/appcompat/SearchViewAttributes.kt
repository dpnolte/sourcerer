package com.laidpack.sourcerer.generated.appcompat

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.generated.ViewGroupAttributes
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class SearchViewAttributes : ViewGroupAttributes(), IAttributes {
    var iconifiedByDefault: Boolean? = null

    var queryHint: String? = null
}
