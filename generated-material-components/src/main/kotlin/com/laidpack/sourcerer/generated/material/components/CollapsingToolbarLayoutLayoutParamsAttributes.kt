package com.laidpack.sourcerer.generated.material.components

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.generated.FrameLayoutLayoutParamsAttributes
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.JsonClass
import kotlin.Float

@JsonClass(generateAdapter = true)
@TypeScript
open class CollapsingToolbarLayoutLayoutParamsAttributes : FrameLayoutLayoutParamsAttributes(),
        IAttributes {
    var layout_collapseParallaxMultiplier: Float? = null
}
