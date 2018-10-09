package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SerializerComponent
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi

@JsonClass(generateAdapter = true)
@TypeScript
open class CircularRevealLinearLayoutAttributes : LinearLayoutAttributes(), IAttributes {
    companion object {
        init {
            SerializerComponent.registerAutoGeneratedAdapter(CircularRevealLinearLayoutAttributes::class, {moshi -> CircularRevealLinearLayoutAttributesJsonAdapter(moshi as Moshi)}, "circularRevealLinearLayout")
            SerializerComponent.associateThisViewGroupWithLayoutParams("circularRevealLinearLayout", "android.view.ViewGroup.LayoutParams")
        }
    }
}