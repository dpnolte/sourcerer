package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class LinearLayoutAttributes : ViewGroupAttributes() {
    var orientation: Int? = null

    var gravity: Int? = null

    var baselineAligned: Boolean? = null

    var baselineAlignedChildIndex: Int? = null

    var measureWithLargestChild: Boolean? = null

    var divider: Int? = null
}
