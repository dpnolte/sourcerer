package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class AdapterViewAnimatorAttributes : AdapterViewAttributes() {
    var inAnimation: Int? = null

    var outAnimation: Int? = null

    var animateFirstView: Boolean? = null
}
