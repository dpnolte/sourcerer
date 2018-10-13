package com.laidpack.sourcerer.services.adapters

import com.laidpack.sourcerer.services.BuildConfig
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.lang.reflect.Type

internal class StrictAdapterFactory : JsonAdapter.Factory {
    override fun create(type: Type?, annotations: MutableSet<out Annotation>?, moshi: Moshi?): JsonAdapter<Any>? {
        //val annotation = Types.getRawType(type).getAnnotation(JsonClass::class.java)
        return if (BuildConfig.DEBUG && type != null && annotations != null && moshi != null) {
            moshi.nextAdapter<Any>(this, type, annotations).failOnUnknown()
        } else {
            null
        }
    }
}