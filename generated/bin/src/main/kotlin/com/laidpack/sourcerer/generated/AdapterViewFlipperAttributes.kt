package com.laidpack.sourcerer.generated

import com.laidpack.annotation.TypeScript
import com.laidpack.sourcerer.service.SerializerComponent
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import kotlin.Int

@JsonClass(generateAdapter = true)
@TypeScript
open class AdapterViewFlipperAttributes : AdapterViewAttributes(), IAttributes {
    var flipInterval: Int? = null

    companion object {
        init {
            SerializerComponent.registerAutoGeneratedAdapter(AdapterViewFlipperAttributes::class, {moshi -> AdapterViewFlipperAttributesJsonAdapter(moshi as Moshi)}, "adapterViewFlipper")
            SerializerComponent.associateThisViewGroupWithLayoutParams("adapterViewFlipper", "android.view.ViewGroup.LayoutParams")
        }
    }
}