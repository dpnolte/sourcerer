package com.laidpack.sourcerer.services.adapters

import android.content.Context
import android.content.res.Resources
import com.laidpack.sourcerer.services.api.ReferenceQualifier
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.ToJson
import java.lang.ref.WeakReference

class ReferenceAdapter(
        private val getResourceId: (name: String, c: Context, type: String, packageName: String?) -> Int = {name, c, t, p  ->
            val packageName = p ?: c.packageName
            c.resources.getIdentifier(name, t, packageName)
        },
        private val getResourceName: (id: Int) -> String = {id -> Resources.getSystem().getResourceName(id)}
): ContextAwareAdapter {
    override lateinit var context: WeakReference<Context>

    @ToJson
    fun toJson(@ReferenceQualifier styleId: Int?): String {
        return if(styleId != null) {
            getResourceName(styleId)
        } else ""
    }

    @FromJson
    @ReferenceQualifier
    fun fromJson(name: String): Int? {
        if (cachedResourceIds.containsKey(name)) {
            return cachedResourceIds[name]
        }

        val type: String
        val resourceName: String
        if (name.startsWith("@") && name.contains("/")) {
            val splitName = name.split("/")
            type = splitName[0].substring(1)
            resourceName = splitName[1]
        } else {
            throw JsonDataException("Resource name has invalid format. Received: '$name'. Expected format: '@type/resource_name' (e.g., '@drawable/ic_launcher_background').")
        }
        if (!allowedTypes.contains(type)) {
            throw JsonDataException("Invalid type provided: '$type', parsed from '$name'.\nAllowed types: '${allowedTypes.joinToString()}'. Example value: @drawable:R.nav_icon")
        }

        val c = context.get() ?: throw IllegalStateException("Context is not set which is necessary to determine reference")
        val packageName = if (resourceName.contains(".R")) resourceName.split(".R").first() else c.packageName
        val result = getResourceId(resourceName, c, type, packageName)
        if (result == 0) {
            throw JsonDataException("Could not convert resource name '$name' into a resource identifier. Package: $packageName, resource name: $resourceName, type: $type ")
        }
        cachedResourceIds[name] = result
        return result
    }

    companion object {
        private val allowedTypes = setOf("style", "drawable", "color", "dimen", "animator", "anim")
        private val cachedResourceIds = mutableMapOf<String, Int>()
    }
}
