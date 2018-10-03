package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.IAttributes
import com.laidpack.sourcerer.service.api.ReferenceQualifier
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class AppBarLayoutLayoutParamsAttributes : LinearLayoutLayoutParamsAttributes(), IAttributes {
    var layout_scrollFlags: LayoutScrollFlagsFlagsEnum? = null

    @field:ReferenceQualifier
    var layout_scrollInterpolator: Int? = null

    companion object {
        init {
            SourcererService.registerAdapter(AppBarLayoutLayoutParamsAttributes::class, AppBarLayoutLayoutParamsAttributesJsonAdapter::class, "layoutParams")
        }
    }
}

enum class LayoutScrollFlagsFlagsEnum(val attributeName: String, val value: Int) {
    @Json(name = "scroll")
    Scroll("scroll", 1),

    @Json(name = "exitUntilCollapsed")
    ExitUntilCollapsed("exitUntilCollapsed", 2),

    @Json(name = "enterAlways")
    EnterAlways("enterAlways", 4),

    @Json(name = "enterAlwaysCollapsed")
    EnterAlwaysCollapsed("enterAlwaysCollapsed", 8),

    @Json(name = "snap")
    Snap("snap", 16),

    @Json(name = "snapMargins")
    SnapMargins("snapMargins", 32);
}
