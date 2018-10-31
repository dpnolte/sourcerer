package com.laidpack.sourcerer.generated.coordinatorlayout

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.generated.ViewGroupMarginLayoutParamsAttributes
import com.laidpack.sourcerer.services.api.AttributeEnum
import com.laidpack.sourcerer.services.api.FlagsAccumulator
import com.laidpack.sourcerer.services.api.FlagsQualifier
import com.laidpack.sourcerer.services.api.Format
import com.laidpack.sourcerer.services.api.IAttributes
import com.laidpack.sourcerer.services.api.MultiFormat
import com.laidpack.sourcerer.services.api.MultiFormatQualifier
import com.laidpack.sourcerer.services.api.ReferenceQualifier
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class CoordinatorLayoutLayoutParamsAttributes(
    val layout_behavior: String? = null,
    @field:ReferenceQualifier val layout_anchor: Int? = null,
    val layout_keyline: Int? = null,
    @field:MultiFormatQualifier(formats = [Format.Integer, Format.Flags], flagsType = LayoutAnchorGravityFlagsEnum::class) val layout_anchorGravity: MultiFormat = MultiFormat(setOf(Format.Integer, Format.Flags)),
    val layout_insetEdge: LayoutInsetEdgeEnum? = null,
    @field:FlagsQualifier(flagsType = LayoutDodgeInsetEdgesFlagsEnum::class) val layout_dodgeInsetEdges: FlagsAccumulator? = null,
    val android_layout_gravity: Int? = null,
    layout_width: MultiFormat = MultiFormat(setOf(Format.Dimension, Format.Enum)),
    layout_height: MultiFormat = MultiFormat(setOf(Format.Dimension, Format.Enum)),
    layout_marginLeft: Int? = null,
    layout_marginBottom: Int? = null,
    layout_marginRight: Int? = null,
    layout_marginTop: Int? = null,
    layout_marginStart: Int? = null,
    layout_marginEnd: Int? = null
) : ViewGroupMarginLayoutParamsAttributes(layout_width = layout_width, layout_height = layout_height, layout_marginLeft = layout_marginLeft, layout_marginBottom = layout_marginBottom, layout_marginRight = layout_marginRight, layout_marginTop = layout_marginTop, layout_marginStart = layout_marginStart, layout_marginEnd = layout_marginEnd),
        IAttributes

enum class LayoutAnchorGravityFlagsEnum(override val key: String, override val value: Int) : AttributeEnum {
    @Json(name = "top")
    Top("top", 48),

    @Json(name = "bottom")
    Bottom("bottom", 80),

    @Json(name = "left")
    Left("left", 3),

    @Json(name = "right")
    Right("right", 5),

    @Json(name = "center_vertical")
    CenterVertical("center_vertical", 16),

    @Json(name = "fill_vertical")
    FillVertical("fill_vertical", 112),

    @Json(name = "center_horizontal")
    CenterHorizontal("center_horizontal", 1),

    @Json(name = "fill_horizontal")
    FillHorizontal("fill_horizontal", 7),

    @Json(name = "center")
    Center("center", 17),

    @Json(name = "fill")
    Fill("fill", 119),

    @Json(name = "clip_vertical")
    ClipVertical("clip_vertical", 128),

    @Json(name = "clip_horizontal")
    ClipHorizontal("clip_horizontal", 8),

    @Json(name = "start")
    Start("start", 8388611),

    @Json(name = "end")
    End("end", 8388613);
}

enum class LayoutInsetEdgeEnum(override val key: String, override val value: Int) : AttributeEnum {
    @Json(name = "none")
    None("none", 0),

    @Json(name = "top")
    Top("top", 48),

    @Json(name = "bottom")
    Bottom("bottom", 80),

    @Json(name = "left")
    Left("left", 3),

    @Json(name = "right")
    Right("right", 5),

    @Json(name = "start")
    Start("start", 8388611),

    @Json(name = "end")
    End("end", 8388613);
}

enum class LayoutDodgeInsetEdgesFlagsEnum(override val key: String, override val value: Int) : AttributeEnum {
    @Json(name = "none")
    None("none", 0),

    @Json(name = "top")
    Top("top", 48),

    @Json(name = "bottom")
    Bottom("bottom", 80),

    @Json(name = "left")
    Left("left", 3),

    @Json(name = "right")
    Right("right", 5),

    @Json(name = "start")
    Start("start", 8388611),

    @Json(name = "end")
    End("end", 8388613),

    @Json(name = "all")
    All("all", 119);
}
