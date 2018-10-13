package com.laidpack.sourcerer.generated.coordinatorlayout

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import com.laidpack.sourcerer.services.api.IAttributes
import com.laidpack.sourcerer.services.api.ReferenceQualifier
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class CoordinatorLayoutLayoutParamsAttributes : ViewGroupLayoutParamsAttributes(),
        IAttributes {
    @field:ReferenceQualifier
    var layout_anchor: Int? = null

    var layout_keyline: Int? = null

    var layout_anchorGravity: LayoutAnchorGravityFlagsEnum? = null

    var layout_insetEdge: LayoutInsetEdgeEnum? = null

    var layout_dodgeInsetEdges: LayoutDodgeInsetEdgesFlagsEnum? = null
}

enum class LayoutAnchorGravityFlagsEnum(val attributeName: String, val value: Int) {
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

enum class LayoutInsetEdgeEnum(val attributeName: String, val value: Int) {
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

enum class LayoutDodgeInsetEdgesFlagsEnum(val attributeName: String, val value: Int) {
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
