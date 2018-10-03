package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@TypeScript
open class SearchBarAttributes : RelativeLayoutAttributes(), IAttributes {
    companion object {
        init {
            SourcererService.registerAdapter(SearchBarAttributes::class, SearchBarAttributesJsonAdapter::class, "searchBar")
        }
    }
}
