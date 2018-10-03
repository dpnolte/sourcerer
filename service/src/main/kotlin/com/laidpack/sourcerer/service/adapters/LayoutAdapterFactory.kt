package com.laidpack.sourcerer.service.adapters

import android.util.Log
import com.laidpack.sourcerer.service.BuildConfig
import com.laidpack.sourcerer.service.LayoutElement
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type
import kotlin.reflect.KClass

internal class LayoutAdapterFactory(private val elementNameToTypeProvider: () -> Map<String, KClass<*>>) : JsonAdapter.Factory {
    override fun create(type: Type?, annotations: MutableSet<out Annotation>?, moshi: Moshi?): JsonAdapter<*>? {
        val rawType = Types.getRawType(type)
        if (rawType != targetType) {
            return null
        }

        return LayoutElementAdapter(moshi as Moshi, elementNameToTypeProvider())

    }
    companion object {
        val targetType = LayoutElement::class.java
    }
}
