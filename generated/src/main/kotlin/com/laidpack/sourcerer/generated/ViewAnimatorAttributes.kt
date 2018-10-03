package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SourcererService
import com.laidpack.sourcerer.service.api.IAttributes
import com.laidpack.sourcerer.service.api.ReferenceQualifier
import com.squareup.moshi.JsonClass
import kotlin.Boolean
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class ViewAnimatorAttributes : FrameLayoutAttributes(), IAttributes {
    @field:ReferenceQualifier
    var inAnimation: Int? = null

    @field:ReferenceQualifier
    var outAnimation: Int? = null

    var animateFirstView: Boolean? = null

    companion object {
        init {
            SourcererService.registerAdapter(ViewAnimatorAttributes::class, ViewAnimatorAttributesJsonAdapter::class, "viewAnimator")
        }
    }
}
