package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@TypeScript
open class ListViewAttributes : AdapterViewAttributes() {
    @field:MultiFormatQualifier(formats = [ColorQualifier::class, ReferenceQualifier::class])
    var divider: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference))
}
