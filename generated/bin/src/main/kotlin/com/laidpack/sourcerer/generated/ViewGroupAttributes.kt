package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int
import kotlin.String

@JsonClass(generateAdapter = true)
@TypeScript
open class ViewGroupAttributes : ViewAttributes() {
    var animateLayoutChanges: Boolean? = null

    var clipChildren: Boolean? = null

    var clipToPadding: Boolean? = null

    var animationCache: Boolean? = null

    var persistentDrawingCache: PersistentDrawingCacheFlagsEnum? = null

    var alwaysDrawnWithCache: Boolean? = null

    var addStatesFromChildren: Boolean? = null

    var touchscreenBlocksFocus: Boolean? = null

    var splitMotionEvents: Boolean? = null

    var layoutMode: LayoutModeEnum? = null

    var transitionGroup: Boolean? = null
}

enum class PersistentDrawingCacheFlagsEnum(val attributeName: String, val value: Int) {
    @Json(name = "none")
    None("none", 0),

    @Json(name = "animation")
    Animation("animation", 1),

    @Json(name = "scrolling")
    Scrolling("scrolling", 2),

    @Json(name = "all")
    All("all", 3);
}

enum class LayoutModeEnum(val attributeName: String, val value: Int) {
    @Json(name = "clipBounds")
    ClipBounds("clipBounds", 0),

    @Json(name = "opticalBounds")
    OpticalBounds("opticalBounds", 1);
}