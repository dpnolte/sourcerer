package com.laidpack.sourcerer.service.adapters

import android.content.Context
import android.content.res.Resources
import com.laidpack.sourcerer.service.api.ReferenceQualifier
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.ToJson


// note, we are assuming that we want a theme or something so use package
class ReferenceAdapter(
        val context: Context,
        val getResourceId: (name: String, c: Context, type: String) -> Int = {name, c, t -> Resources.getSystem().getIdentifier(name, t, c.packageName)},
        val getResourceName: (id: Int) -> String = {id -> Resources.getSystem().getResourceName(id)}
) {
    @ToJson
    fun toJson(@ReferenceQualifier styleId: Int): String {
        return getResourceName(styleId)
    }

    @FromJson
    @ReferenceQualifier
    fun fromJson(name: String): Int {
        // TODO: add support for more types (drawable, color, dimen, animator, anim): See https://gist.github.com/brucetoo/48be42fa1387717547e2 as reference
        val result = getResourceId(name, context, "styles")
        if (result == 0) {
            throw JsonDataException("Resource name '$name' is not found in @styles in context package")
        }
        return result
    }
}
