package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.services.api.AttributeEnum
import com.laidpack.sourcerer.services.api.Format
import com.laidpack.sourcerer.services.api.IAttributes
import com.laidpack.sourcerer.services.api.MultiFormat
import com.laidpack.sourcerer.services.api.MultiFormatQualifier
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class ViewGroupLayoutParamsAttributes(@field:MultiFormatQualifier(formats = [Format.Dimension, Format.Enum], enumType = LayoutWidthEnum::class) val layout_width: MultiFormat = MultiFormat(setOf(Format.Dimension, Format.Enum)), @field:MultiFormatQualifier(formats = [Format.Dimension, Format.Enum], enumType = LayoutHeightEnum::class) val layout_height: MultiFormat = MultiFormat(setOf(Format.Dimension, Format.Enum))) : IAttributes

enum class LayoutWidthEnum(override val key: String, override val value: Int) : AttributeEnum {
    @Json(name = "fill_parent")
    FillParent("fill_parent", -1),

    @Json(name = "match_parent")
    MatchParent("match_parent", -1),

    @Json(name = "wrap_content")
    WrapContent("wrap_content", -2);
}

enum class LayoutHeightEnum(override val key: String, override val value: Int) : AttributeEnum {
    @Json(name = "fill_parent")
    FillParent("fill_parent", -1),

    @Json(name = "match_parent")
    MatchParent("match_parent", -1),

    @Json(name = "wrap_content")
    WrapContent("wrap_content", -2);
}
