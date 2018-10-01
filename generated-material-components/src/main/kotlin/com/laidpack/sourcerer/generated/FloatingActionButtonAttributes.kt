package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class FloatingActionButtonAttributes : ImageViewAttributes() {
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
    Mini("mini", 1);
}
