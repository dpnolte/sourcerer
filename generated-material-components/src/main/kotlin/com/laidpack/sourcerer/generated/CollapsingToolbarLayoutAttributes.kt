package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class CollapsingToolbarLayoutAttributes : FrameLayoutAttributes() {
    @field:DimensionQualifier
    var expandedTitleMarginStart: Int? = null

    @field:DimensionQualifier
    var expandedTitleMarginTop: Int? = null

    @field:DimensionQualifier
    var expandedTitleMarginEnd: Int? = null

    @field:DimensionQualifier
    var expandedTitleMarginBottom: Int? = null

    @field:ColorQualifier
    var contentScrim: Int? = null

    @field:ColorQualifier
    var statusBarScrim: Int? = null

    @field:DimensionQualifier
    var scrimVisibleHeightTrigger: Int? = null

    var scrimAnimationDuration: Int? = null

    var collapsedTitleGravity: CollapsedTitleGravityFlagsEnum? = null

    var expandedTitleGravity: ExpandedTitleGravityFlagsEnum? = null

    var titleEnabled: Boolean? = null

    var title: String? = null
}

enum class CollapsedTitleGravityFlagsEnum(val attributeName: String, val value: Int) {
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

    @Json(name = "center")
    Center("center", 17),

    @Json(name = "start")
    Start("start", 8388611),

    @Json(name = "end")
    End("end", 8388613);
}

enum class ExpandedTitleGravityFlagsEnum(val attributeName: String, val value: Int) {
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

    @Json(name = "center")
    Center("center", 17),

    @Json(name = "start")
    Start("start", 8388611),

    @Json(name = "end")
    End("end", 8388613);
}
