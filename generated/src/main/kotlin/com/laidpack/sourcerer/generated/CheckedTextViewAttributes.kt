package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.ColorQualifier
import com.laidpack.sourcerer.service.api.IAttributes
import com.laidpack.sourcerer.service.api.ReferenceQualifier
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class CheckedTextViewAttributes : TextViewAttributes(), IAttributes {
    @field:ReferenceQualifier
    var checkMark: Int? = null

    @field:ColorQualifier
    var checkMarkTint: Int? = null

    var checkMarkTintMode: CheckMarkTintModeEnum? = null

    companion object {
        init {
            SourcererService.registerAdapter(CheckedTextViewAttributes::class, CheckedTextViewAttributesJsonAdapter::class, "checkedTextView")
        }
    }
}

enum class CheckMarkTintModeEnum(val attributeName: String, val value: Int) {
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
