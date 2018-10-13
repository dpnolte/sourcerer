package com.laidpack.sourcerer.generated.wear

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.generated.ViewGroupAttributes
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Float

@JsonClass(generateAdapter = true)
@TypeScript
open class WearableRecyclerViewAttributes : ViewGroupAttributes(), IAttributes {
    var bezelWidth: Float? = null

    var circularScrollingGestureEnabled: Boolean? = null

    var scrollDegreesPerScreen: Float? = null
}
