package com.laidpack.sourcerer.generated.material.components

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.generated.LinearLayoutLayoutParamsAttributes
import com.laidpack.sourcerer.services.api.IAttributes
import com.laidpack.sourcerer.services.api.ReferenceQualifier
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class AppBarLayoutLayoutParamsAttributes : LinearLayoutLayoutParamsAttributes(), IAttributes {
    var layout_scrollFlags: LayoutScrollFlagsEnum? = null

    @field:ReferenceQualifier
    var layout_scrollInterpolator: Int? = null
}

enum class LayoutScrollFlagsEnum(val attributeName: String, val value: Int) {
    @Json(name = "scroll_flag_scroll")
    ScrollFlagScroll("scroll_flag_scroll", 1),

    @Json(name = "scroll_flag_exit_until_collapsed")
    ScrollFlagExitUntilCollapsed("scroll_flag_exit_until_collapsed", 2),

    @Json(name = "scroll_flag_enter_always")
    ScrollFlagEnterAlways("scroll_flag_enter_always", 4),

    @Json(name = "scroll_flag_enter_always_collapsed")
    ScrollFlagEnterAlwaysCollapsed("scroll_flag_enter_always_collapsed", 8),

    @Json(name = "scroll_flag_snap")
    ScrollFlagSnap("scroll_flag_snap", 16),

    @Json(name = "scroll_flag_snap_margins")
    ScrollFlagSnapMargins("scroll_flag_snap_margins", 32);
}
