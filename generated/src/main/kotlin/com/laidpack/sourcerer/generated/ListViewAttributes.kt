package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.ColorQualifier
import com.laidpack.sourcerer.service.api.IAttributes
import com.laidpack.sourcerer.service.api.MultiFormatQualifier
import com.laidpack.sourcerer.service.api.ReferenceQualifier
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@TypeScript
open class ListViewAttributes : AdapterViewAttributes(), IAttributes {
    @field:MultiFormatQualifier(formats = [ColorQualifier::class, ReferenceQualifier::class])
    var divider: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference))

    companion object {
        init {
            SourcererService.registerAdapter(ListViewAttributes::class, ListViewAttributesJsonAdapter::class, "listView")
        }
    }
}
