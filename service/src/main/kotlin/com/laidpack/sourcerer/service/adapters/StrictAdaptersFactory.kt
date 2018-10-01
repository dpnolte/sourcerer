package com.laidpack.sourcerer.service.adapters

import com.laidpack.sourcerer.service.BuildConfig
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

internal class StrictAdaptersFactory : JsonAdapter.Factory {
    override fun create(type: Type?, annotations: MutableSet<out Annotation>?, moshi: Moshi?): JsonAdapter<Any>? {
        val annotation = Types.getRawType(type).getAnnotation(JsonClass::class.java)
        return if (BuildConfig.DEBUG && type != null && annotations != null && moshi != null) {
            moshi.nextAdapter<Any>(this, type, annotations).failOnUnknown()
        } else {
            null
        }
    }
}