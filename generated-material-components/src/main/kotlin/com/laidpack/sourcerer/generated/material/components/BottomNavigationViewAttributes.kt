package com.laidpack.sourcerer.generated.material.components

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.generated.FrameLayoutAttributes
import com.laidpack.sourcerer.services.api.DimensionQualifier
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class BottomNavigationViewAttributes : FrameLayoutAttributes(), IAttributes {
    var labelVisibilityMode: LabelVisibilityModeEnum? = null

    @field:DimensionQualifier
    var itemIconSize: Int? = null

    var itemIconTint: Int? = null

    var itemTextColor: Int? = null
}

enum class LabelVisibilityModeEnum(val attributeName: String, val value: Int) {
    @Json(name = "auto")
    Auto("auto", -1),

    @Json(name = "selected")
    Selected("selected", 0),

    @Json(name = "labeled")
    Labeled("labeled", 1),

    @Json(name = "unlabeled")
    Unlabeled("unlabeled", 2);
}
