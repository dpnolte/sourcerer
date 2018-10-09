package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SerializerComponent
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi

@JsonClass(generateAdapter = true)
@TypeScript
open class BoxInsetLayoutAttributes : ViewGroupAttributes(), IAttributes {
    companion object {
        init {
            SerializerComponent.registerAutoGeneratedAdapter(BoxInsetLayoutAttributes::class, {moshi -> BoxInsetLayoutAttributesJsonAdapter(moshi as Moshi)}, "boxInsetLayout")
            SerializerComponent.associateThisViewGroupWithLayoutParams("boxInsetLayout", "androidx.wear.widget.BoxInsetLayout.LayoutParams")
        }
    }
}