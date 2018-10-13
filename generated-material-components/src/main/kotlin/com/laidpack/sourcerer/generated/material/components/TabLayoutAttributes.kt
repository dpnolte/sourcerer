package com.laidpack.sourcerer.generated.material.components

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.generated.HorizontalScrollViewAttributes
import com.laidpack.sourcerer.services.api.ColorQualifier
import com.laidpack.sourcerer.services.api.DimensionQualifier
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class TabLayoutAttributes : HorizontalScrollViewAttributes(), IAttributes {
    @field:DimensionQualifier
    var tabIndicatorHeight: Int? = null

    var tabIndicatorGravity: TabIndicatorGravityEnum? = null

    var tabIndicatorFullWidth: Boolean? = null

    var tabMode: TabModeEnum? = null

    var tabGravity: TabGravityEnum? = null

    var tabInlineLabel: Boolean? = null

    @field:ColorQualifier
    var tabRippleColor: Int? = null
}

enum class TabIndicatorGravityEnum(val attributeName: String, val value: Int) {
    @Json(name = "bottom")
    Bottom("bottom", 0),

    @Json(name = "center")
    Center("center", 1),

    @Json(name = "top")
    Top("top", 2),

    @Json(name = "stretch")
    Stretch("stretch", 3),

    @Json(name = "indicator_gravity_bottom")
    IndicatorGravityBottom("indicator_gravity_bottom", 0),

    @Json(name = "indicator_gravity_center")
    IndicatorGravityCenter("indicator_gravity_center", 1),

    @Json(name = "indicator_gravity_top")
    IndicatorGravityTop("indicator_gravity_top", 2),

    @Json(name = "indicator_gravity_stretch")
    IndicatorGravityStretch("indicator_gravity_stretch", 3);
}

enum class TabModeEnum(val attributeName: String, val value: Int) {
    @Json(name = "scrollable")
    Scrollable("scrollable", 0),

    @Json(name = "fixed")
    Fixed("fixed", 1),

    @Json(name = "mode_scrollable")
    ModeScrollable("mode_scrollable", 0),

    @Json(name = "mode_fixed")
    ModeFixed("mode_fixed", 1);
}

enum class TabGravityEnum(val attributeName: String, val value: Int) {
    @Json(name = "fill")
    Fill("fill", 0),

    @Json(name = "center")
    Center("center", 1),

    @Json(name = "gravity_fill")
    GravityFill("gravity_fill", 0),

    @Json(name = "gravity_center")
    GravityCenter("gravity_center", 1);
}
