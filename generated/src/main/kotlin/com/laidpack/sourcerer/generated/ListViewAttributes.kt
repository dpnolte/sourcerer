package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.services.api.DimensionQualifier
import com.laidpack.sourcerer.services.api.Format
import com.laidpack.sourcerer.services.api.IAttributes
import com.laidpack.sourcerer.services.api.MultiFormat
import com.laidpack.sourcerer.services.api.MultiFormatQualifier
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class ListViewAttributes : AdapterViewAttributes(), IAttributes {
    @field:MultiFormatQualifier(formats = [Format.Color, Format.Reference])
    var divider: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference))

    @field:DimensionQualifier
    var dividerHeight: Int? = null

    var headerDividersEnabled: Boolean? = null

    var footerDividersEnabled: Boolean? = null

    @field:MultiFormatQualifier(formats = [Format.Color, Format.Reference])
    var overScrollHeader: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference))

    @field:MultiFormatQualifier(formats = [Format.Color, Format.Reference])
    var overScrollFooter: MultiFormat = MultiFormat(setOf(Format.Color, Format.Reference))
}
