package com.laidpack.sourcerer.generated.appcompat

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.generated.ViewGroupAttributes
import com.laidpack.sourcerer.services.api.DimensionQualifier
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.JsonClass
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class ToolbarAttributes : ViewGroupAttributes(), IAttributes {
    @field:DimensionQualifier
    var titleMarginStart: Int? = null

    @field:DimensionQualifier
    var titleMarginEnd: Int? = null

    @field:DimensionQualifier
    var titleMarginTop: Int? = null

    @field:DimensionQualifier
    var titleMarginBottom: Int? = null

    var contentInsetStart: Int? = null

    var contentInsetEnd: Int? = null

    var contentInsetLeft: Int? = null

    var contentInsetRight: Int? = null

    var contentInsetStartWithNavigation: Int? = null

    var contentInsetEndWithActions: Int? = null

    var navigationContentDescription: String? = null
}
