package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SerializerComponent
import com.laidpack.sourcerer.service.api.ColorQualifier
import com.laidpack.sourcerer.service.api.DimensionQualifier
import com.laidpack.sourcerer.service.api.IAttributes
import com.laidpack.sourcerer.service.api.ReferenceQualifier
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class PagedListViewAttributes : FrameLayoutAttributes(), IAttributes {
    var gutter: GutterEnum? = null

    @field:ColorQualifier
    var scrollBarColor: Int? = null

    @field:DimensionQualifier
    var scrollBarTopMargin: Int? = null

    @field:DimensionQualifier
    var scrollBarContainerWidth: Int? = null

    @field:ReferenceQualifier
    var upButtonIcon: Int? = null

    @field:ReferenceQualifier
    var downButtonIcon: Int? = null

    var dayNightStyle: DayNightStyleEnum? = null

    companion object {
        init {
            SerializerComponent.registerAutoGeneratedAdapter(PagedListViewAttributes::class, {moshi -> PagedListViewAttributesJsonAdapter(moshi as Moshi)}, "pagedListView")
            SerializerComponent.associateThisViewGroupWithLayoutParams("pagedListView", "android.view.ViewGroup.LayoutParams")
        }
    }
}

enum class GutterEnum(val attributeName: String, val value: Int) {
    @Json(name = "none")
    None("none", 0),

    @Json(name = "start")
    Start("start", 1),

    @Json(name = "end")
    End("end", 2),

    @Json(name = "both")
    Both("both", 3);
}

enum class DayNightStyleEnum(val attributeName: String, val value: Int) {
    @Json(name = "auto")
    Auto("auto", 0),

    @Json(name = "auto_inverse")
    AutoInverse("auto_inverse", 1),

    @Json(name = "always_light")
    AlwaysLight("always_light", 2),

    @Json(name = "always_dark")
    AlwaysDark("always_dark", 3);
}