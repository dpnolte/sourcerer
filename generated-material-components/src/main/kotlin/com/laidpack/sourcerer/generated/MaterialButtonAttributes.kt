package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.ColorQualifier
import com.laidpack.sourcerer.service.api.DimensionQualifier
import com.laidpack.sourcerer.service.api.IAttributes
import com.laidpack.sourcerer.service.api.ReferenceQualifier
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

    var iconGravity: IconGravityFlagsEnum? = null

    @field:ColorQualifier
    var iconTint: Int? = null

    var iconTintMode: Int? = null

    var strokeColor: Int? = null

    var strokeWidth: Int? = null

    @field:DimensionQualifier
    var cornerRadius: Int? = null

    var rippleColor: Int? = null

    companion object {
        init {
            SourcererService.registerAdapter(MaterialButtonAttributes::class, MaterialButtonAttributesJsonAdapter::class, "materialButton")
        }
    }
}

enum class IconGravityFlagsEnum(val attributeName: String, val value: Int) {
    @Json(name = "start")
    Start("start", 1),

    @Json(name = "textStart")
    TextStart("textStart", 2);
}
