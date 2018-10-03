package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class SearchViewAttributes : LinearLayoutAttributes(), IAttributes {
    var iconifiedByDefault: Boolean? = null

    var maxWidth: Int? = null

    var queryHint: String? = null

    var imeOptions: Int? = null

    var inputType: Int? = null

    companion object {
        init {
            SourcererService.registerAdapter(SearchViewAttributes::class, SearchViewAttributesJsonAdapter::class, "searchView")
        }
    }
}
