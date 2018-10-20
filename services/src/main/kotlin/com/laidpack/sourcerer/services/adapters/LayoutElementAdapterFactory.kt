package com.laidpack.sourcerer.services.adapters

import com.laidpack.sourcerer.services.LayoutProperties
import com.laidpack.sourcerer.services.api.IAttributes
import com.squareup.moshi.*
import java.lang.reflect.Type
import kotlin.reflect.KClass

internal class LayoutElementAdapterFactory(private val elementNameToTypeProvider: () -> Map<String, KClass<*>>) : JsonAdapter.Factory {
    override fun create(t: Type?, annotations: MutableSet<out Annotation>?, moshi: Moshi?): JsonAdapter<*>? {
        val type = t ?: return null
        val rawType = Types.getRawType(type)
        if (rawType != targetType) {
            return null
        }

        return LayoutElementAdapter(moshi as Moshi, elementNameToTypeProvider())

    }
    companion object {
        val targetType = LayoutProperties::class.java
    }
}


internal class LayoutElementAdapter(private val moshi: Moshi, private val elementNameToType: Map<String, KClass<*>>) : JsonAdapter<LayoutProperties>() {
    private val options: JsonReader.Options =
            JsonReader.Options.of("id", "type", "attributes", "children")

    private val stringAdapter: JsonAdapter<String> =
            moshi.adapter<String>(String::class.java, kotlin.collections.emptySet(), "id")

    private val listOfStringAdapter: JsonAdapter<List<String>> =
            moshi.adapter<List<String>>(Types.newParameterizedType(List::class.java, String::class.java), kotlin.collections.emptySet(), "children")

    override fun fromJson(reader: JsonReader): LayoutProperties {
        var id: String? = null
        var type: String? = null
        var attributesAsJson: Any? = null
        var children: List<String>? = null
        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(options)) {
                0 -> id = stringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'id' was null at ${reader.path}")
                1 -> type = stringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'elementType' was null at ${reader.path}")
                2 -> attributesAsJson = reader.readJsonValue() ?: throw JsonDataException("Non-null value 'attributes' was null at ${reader.path}")
                3 -> children = listOfStringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'children' was null at ${reader.path}")
                -1 -> {
                    // Unknown name, skip it.
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }
        reader.endObject()

        attributesAsJson ?: throw JsonDataException("Required property 'attributes' missing at ${reader.path}")
        type ?: throw JsonDataException("Required property 'type' missing at ${reader.path}")
        val subjectType = elementNameToType[type] ?: throw JsonDataException("Element type '$type' has no registered json adapter")
        val attributesDelegate = moshi.adapter(subjectType.java)
        return LayoutProperties(
                id = id ?: throw JsonDataException("Required property 'id' missing at ${reader.path}"),
                type = type,
                attributes =  attributesDelegate.fromJsonValue(attributesAsJson) as IAttributes,
                children = children?.toSet() ?: throw JsonDataException("Required property 'children' missing at ${reader.path}")
        )
    }

    override fun toJson(writer: JsonWriter, value: LayoutProperties?) {
        throw NotImplementedError("No layout element to json implementation")
    }
}
