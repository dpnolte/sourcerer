package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SerializerComponent
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi

@JsonClass(generateAdapter = true)
@TypeScript
open class CoordinatorLayoutAttributes : ViewGroupAttributes(), IAttributes {
    companion object {
        init {
            SerializerComponent.registerAutoGeneratedAdapter(CoordinatorLayoutAttributes::class, {moshi -> CoordinatorLayoutAttributesJsonAdapter(moshi as Moshi)}, "coordinatorLayout")
            SerializerComponent.associateThisViewGroupWithLayoutParams("coordinatorLayout", "androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams")
        }
    }
}