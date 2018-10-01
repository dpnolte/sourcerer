package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class AutoCompleteTextViewAttributes : EditTextAttributes() {
    var completionHint: String? = null

    var completionThreshold: Int? = null

    @field:ReferenceQualifier
    var dropDownAnchor: Int? = null

    @field:DimensionQualifier
    var dropDownWidth: DropDownWidthEnum? = null

    @field:DimensionQualifier
    var dropDownHeight: DropDownHeightEnum? = null
}

enum class DropDownWidthEnum(val attributeName: String, val value: Int) {
    @Json(name = "fill_parent")
    FillParent("fill_parent", -1),

    @Json(name = "match_parent")
    MatchParent("match_parent", -1),

    @Json(name = "wrap_content")
    WrapContent("wrap_content", -2);
}

enum class DropDownHeightEnum(val attributeName: String, val value: Int) {
    @Json(name = "fill_parent")
    FillParent("fill_parent", -1),

    @Json(name = "match_parent")
    MatchParent("match_parent", -1),

    @Json(name = "wrap_content")
    WrapContent("wrap_content", -2);
}
