package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.services.api.DimensionQualifier
import com.laidpack.sourcerer.services.api.IAttributes
import com.laidpack.sourcerer.services.api.ReferenceQualifier
import com.squareup.moshi.JsonClass
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class ToolbarAttributes : ViewGroupAttributes(), IAttributes {
    var title: String? = null

    var subtitle: String? = null

    @field:DimensionQualifier
    var titleMargin: Int? = null

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

    @field:ReferenceQualifier
    var popupTheme: Int? = null

    @field:ReferenceQualifier
    var navigationIcon: Int? = null

    var navigationContentDescription: String? = null

    var logo: Int? = null

    var logoDescription: String? = null
}
