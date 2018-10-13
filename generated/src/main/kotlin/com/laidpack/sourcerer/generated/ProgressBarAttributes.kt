package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.services.api.ColorQualifier
import com.laidpack.sourcerer.services.api.IAttributes
import com.laidpack.sourcerer.services.api.ReferenceQualifier
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class ProgressBarAttributes : ViewAttributes(), IAttributes {
    var min: Int? = null

    var max: Int? = null

    var secondaryProgress: Int? = null

    var indeterminate: Boolean? = null

    @field:ReferenceQualifier
    var indeterminateDrawable: Int? = null

    @field:ReferenceQualifier
    var progressDrawable: Int? = null

    @field:ColorQualifier
    var progressTint: Int? = null

    var progressTintMode: ProgressTintModeEnum? = null

    @field:ColorQualifier
    var progressBackgroundTint: Int? = null

    var progressBackgroundTintMode: ProgressBackgroundTintModeEnum? = null

    @field:ColorQualifier
    var secondaryProgressTint: Int? = null

    var secondaryProgressTintMode: SecondaryProgressTintModeEnum? = null

    @field:ColorQualifier
    var indeterminateTint: Int? = null

    var indeterminateTintMode: IndeterminateTintModeEnum? = null
}

enum class ProgressTintModeEnum(val attributeName: String, val value: Int) {
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

enum class ProgressBackgroundTintModeEnum(val attributeName: String, val value: Int) {
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

enum class SecondaryProgressTintModeEnum(val attributeName: String, val value: Int) {
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

enum class IndeterminateTintModeEnum(val attributeName: String, val value: Int) {
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
