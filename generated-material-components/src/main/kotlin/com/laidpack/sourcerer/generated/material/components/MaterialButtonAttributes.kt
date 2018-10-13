package com.laidpack.sourcerer.generated.material.components

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.generated.ButtonAttributes
import com.laidpack.sourcerer.services.api.ColorQualifier
import com.laidpack.sourcerer.services.api.DimensionQualifier
import com.laidpack.sourcerer.services.api.IAttributes
import com.laidpack.sourcerer.services.api.ReferenceQualifier
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class MaterialButtonAttributes : ButtonAttributes(), IAttributes {
    @field:ReferenceQualifier
    var icon: Int? = null

    @field:DimensionQualifier
    var iconSize: Int? = null

    @field:DimensionQualifier
    var iconPadding: Int? = null

    var iconGravity: IconGravityEnum? = null

    @field:ColorQualifier
    var iconTint: Int? = null

    var iconTintMode: Int? = null

    var strokeColor: Int? = null

    var strokeWidth: Int? = null

    @field:DimensionQualifier
    var cornerRadius: Int? = null

    var rippleColor: Int? = null
}

enum class IconGravityEnum(val attributeName: String, val value: Int) {
    @Json(name = "icon_gravity_start")
    IconGravityStart("icon_gravity_start", 1),

    @Json(name = "icon_gravity_text_start")
    IconGravityTextStart("icon_gravity_text_start", 2);
}
