package com.laidpack.sourcerer.generated.material.components

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.generated.ImageViewAttributes
import com.laidpack.sourcerer.services.api.ColorQualifier
import com.laidpack.sourcerer.services.api.DimensionQualifier
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class FloatingActionButtonAttributes : ImageViewAttributes(), IAttributes {
    @field:ColorQualifier
    var rippleColor: Int? = null

    var fabSize: FabSizeEnum? = null

    @field:DimensionQualifier
    var fabCustomSize: Int? = null

    @field:DimensionQualifier
    var hoveredFocusedTranslationZ: Int? = null

    @field:DimensionQualifier
    var pressedTranslationZ: Int? = null

    var useCompatPadding: Boolean? = null

    var showMotionSpec: Int? = null

    var hideMotionSpec: Int? = null
}

enum class FabSizeEnum(val attributeName: String, val value: Int) {
    @Json(name = "auto")
    Auto("auto", -1),

    @Json(name = "normal")
    Normal("normal", 0),

    @Json(name = "mini")
    Mini("mini", 1),

    @Json(name = "size_mini")
    SizeMini("size_mini", 1),

    @Json(name = "size_normal")
    SizeNormal("size_normal", 0),

    @Json(name = "size_auto")
    SizeAuto("size_auto", 1);
}
