package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.ColorQualifier
import com.laidpack.sourcerer.service.api.IAttributes
import com.laidpack.sourcerer.service.api.ReferenceQualifier
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class CompoundButtonAttributes : ButtonAttributes(), IAttributes {
    var checked: Boolean? = null

    @field:ReferenceQualifier
    var button: Int? = null

    @field:ColorQualifier
    var buttonTint: Int? = null

    var buttonTintMode: ButtonTintModeEnum? = null

    companion object {
        init {
            SourcererService.registerAdapter(CompoundButtonAttributes::class, CompoundButtonAttributesJsonAdapter::class, "compoundButton")
        }
    }
}

enum class ButtonTintModeEnum(val attributeName: String, val value: Int) {
    @Json(name = "src_over")
    SrcOver("src_over", 3),

    @Json(name = "src_in")
    SrcIn("src_in", 5),

    @Json(name = "src_atop")
    SrcAtop("src_atop", 9),

    @Json(name = "multiply")
    Multiply("multiply", 14),

    @Json(name = "screen")
    Screen("screen", 15),

    @Json(name = "add")
    Add("add", 16);
}
