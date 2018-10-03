package com.laidpack.sourcerer.service.adapters

import android.util.Log
import android.view.View
import com.laidpack.sourcerer.service.BuildConfig
import com.laidpack.sourcerer.service.LayoutElement
import com.laidpack.sourcerer.service.LayoutMap
import com.squareup.moshi.*
import java.lang.IllegalStateException

// TODO: maybe add support for denormalized json?
internal class LayoutMapAdapter {
    @ToJson
    fun toJson(map: LayoutMap): String {
        TODO("not yet implemented, do I need this?")
    }

    @FromJson
    fun fromJson(reader: JsonReader, elementDelegate: JsonAdapter<LayoutElement>): LayoutMap {
        val elements = mutableMapOf<String, LayoutElement>()

        if (reader.peek() != JsonReader.Token.BEGIN_ARRAY) {
            if (BuildConfig.DEBUG) throw JsonDataException("Expected json array for layout map")
            else Log.e("LayoutMapAdapter", "Expected json array for layout map")
        }

        reader.beginArray()
        while(reader.hasNext()) {
            val element = elementDelegate.fromJson(reader)
            if (element != null) {
                elements[element.id] = element
            }
        }
        reader.endArray()
        return LayoutMap(elements)
    }
}