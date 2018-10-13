package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.services.api.DimensionQualifier
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class LinearLayoutAttributes : ViewGroupAttributes(), IAttributes {
    var orientation: OrientationEnum? = null

    var gravity: Int? = null

    var baselineAligned: Boolean? = null

    var baselineAlignedChildIndex: Int? = null

    var measureWithLargestChild: Boolean? = null

    var divider: Int? = null

    var showDividers: ShowDividersEnum? = null

    @field:DimensionQualifier
    var dividerPadding: Int? = null
}

enum class OrientationEnum(val attributeName: String, val value: Int) {
    @Json(name = "horizontal")
    Horizontal("horizontal", 0),

    @Json(name = "vertical")
    Vertical("vertical", 1);
}

enum class ShowDividersEnum(val attributeName: String, val value: Int) {
    @Json(name = "show_divider_none")
    ShowDividerNone("show_divider_none", 0),

    @Json(name = "show_divider_beginning")
    ShowDividerBeginning("show_divider_beginning", 1),

    @Json(name = "show_divider_middle")
    ShowDividerMiddle("show_divider_middle", 2),

    @Json(name = "show_divider_end")
    ShowDividerEnd("show_divider_end", 4);
}
