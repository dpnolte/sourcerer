package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.ColorQualifier
import com.laidpack.sourcerer.service.api.DimensionQualifier
import com.laidpack.sourcerer.service.api.IAttributes
import com.laidpack.sourcerer.service.api.ReferenceQualifier
import com.squareup.moshi.JsonClass
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class NavigationViewAttributes : FrameLayoutAttributes(), IAttributes {
    @field:ColorQualifier
    var itemIconTint: Int? = null

    @field:ColorQualifier
    var itemTextColor: Int? = null

    @field:ReferenceQualifier
    var itemBackground: Int? = null

    @field:DimensionQualifier
    var itemHorizontalPadding: Int? = null

    @field:DimensionQualifier
    var itemIconPadding: Int? = null

    companion object {
        init {
            SourcererService.registerAdapter(NavigationViewAttributes::class, NavigationViewAttributesJsonAdapter::class, "navigationView")
        }
    }
}
