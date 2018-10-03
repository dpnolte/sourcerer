package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.DimensionQualifier
import com.laidpack.sourcerer.service.api.IAttributes
import com.laidpack.sourcerer.service.api.ReferenceQualifier
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class AutoCompleteTextViewAttributes : EditTextAttributes(), IAttributes {
    var completionHint: String? = null

    var completionThreshold: Int? = null

    @field:ReferenceQualifier
    var dropDownAnchor: Int? = null

    @field:DimensionQualifier
    var dropDownWidth: DropDownWidthEnum? = null

    @field:DimensionQualifier
    var dropDownHeight: DropDownHeightEnum? = null

    companion object {
        init {
            SourcererService.registerAdapter(AutoCompleteTextViewAttributes::class, AutoCompleteTextViewAttributesJsonAdapter::class, "autoCompleteTextView")
        }
    }
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
