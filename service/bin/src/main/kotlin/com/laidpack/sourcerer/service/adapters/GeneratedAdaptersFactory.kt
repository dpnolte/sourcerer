package com.laidpack.sourcerer.service.adapters

import android.util.Log
import com.laidpack.sourcerer.service.BuildConfig
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type
import kotlin.reflect.KClass

internal class GeneratedAdaptersFactory(private val subjectClass: KClass<*>, private val adapterType: KClass<*>) : JsonAdapter.Factory {
    override fun create(type: Type?, annotations: MutableSet<out Annotation>?, moshi: Moshi?): JsonAdapter<*>? {
        val rawType = Types.getRawType(type)
        if (rawType != subjectClass.javaObjectType) {
            return null
        }

        return try {
            val adapter = adapterType.java.getDeclaredConstructor(Moshi::class.java).newInstance(moshi)
            adapter as? JsonAdapter<*>
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) throw e
            else Log.d(TAG, "${e.message}:\n${e.stackTrace}")

            null
        }
    }
    companion object {
        const val TAG = "GenAdaptersFactory"
    }
}