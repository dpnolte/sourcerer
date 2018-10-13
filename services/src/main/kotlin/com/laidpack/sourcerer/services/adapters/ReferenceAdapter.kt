package com.laidpack.sourcerer.services.adapters

import android.content.Context
import android.content.res.Resources
import com.laidpack.sourcerer.services.api.ReferenceQualifier
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.ToJson

class ReferenceAdapter(
        private val getResourceId: (name: String, c: Context, type: String) -> Int = {name, c, t -> Resources.getSystem().getIdentifier(name, t, c.packageName)},
        private val getResourceName: (id: Int) -> String = {id -> Resources.getSystem().getResourceName(id)}
) {
    lateinit var context: Context

    @ToJson
    fun toJson(@ReferenceQualifier styleId: Int?): String {
        return if(styleId != null) {
            getResourceName(styleId)
        } else ""
    }

    @FromJson
    @ReferenceQualifier
    fun fromJson(name: String): Int? {
        // See as reference: https://gist.github.com/brucetoo/48be42fa1387717547e2
        val type: String
        val resourceName: String
        if (name.contains(":")) {
            val splitName = name.split(":")
            type = splitName[0].substring(1)
            resourceName = splitName[1]
        } else {
            type = "style"
            resourceName = name
        }
        if (!resourceName.contains("R.")) {
            throw JsonDataException("Invalid resource name provided: '$resourceName'. It doesn't constain 'R.', it is parsed from '$name'.\nAllowed types: '${allowedTypes.joinToString()}'. Example value: @drawable:R.nav_icon")
        }
        if (!allowedTypes.contains(type)) {
            throw JsonDataException("Invalid type provided: '$type', parsed from '$name'.\nAllowed types: '${allowedTypes.joinToString()}'. Example value: @drawable:R.nav_icon")
        }

        val result = getResourceId(resourceName, context, type)
        if (result == 0) {
            throw JsonDataException("Resource name '$name' is not found in @styles in context package")
        }
        return result
    }

    companion object {
        private val allowedTypes = setOf("style", "drawable", "color", "dimen", "animator", "anim")
    }
}
