package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class SearchViewAttributes : ViewGroupAttributes(), IAttributes {
    var iconifiedByDefault: Boolean? = null

    var queryHint: String? = null

    companion object {
        init {
            SourcererService.registerAdapter(SearchViewAttributes::class, SearchViewAttributesJsonAdapter::class, "searchView")
        }
    }
}
