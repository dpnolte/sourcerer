package com.laidpack.sourcerer.service.adapters

import com.laidpack.sourcerer.service.LayoutElement
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlin.String
import kotlin.collections.List
import kotlin.reflect.KClass

internal class LayoutElementAdapter(private val moshi: Moshi, private val elementNameToType: Map<String, KClass<*>>) : JsonAdapter<LayoutElement>() {
    private val options: JsonReader.Options =
            JsonReader.Options.of("id", "element", "attributes", "children")

    private val stringAdapter: JsonAdapter<String> =
            moshi.adapter<String>(String::class.java, kotlin.collections.emptySet(), "id")

    private val listOfStringAdapter: JsonAdapter<List<String>> =
            moshi.adapter<List<String>>(Types.newParameterizedType(List::class.java, String::class.java), kotlin.collections.emptySet(), "children")

    override fun fromJson(reader: JsonReader): LayoutElement {
        var id: String? = null
        var elementName: String? = null
        var attributesAsJson: Any? = null
        var children: List<String>? = null
        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(options)) {
                0 -> id = stringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'id' was null at ${reader.path}")
                1 -> elementName = stringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'elementName' was null at ${reader.path}")
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
        elementName ?: throw JsonDataException("Required property 'elementName' missing at ${reader.path}")
        val subjectType = elementNameToType[elementName] ?: throw JsonDataException("Element name $elementName has no registered json adapter")
        val attributesDelegate = moshi.adapter(subjectType.java)
        return LayoutElement(
                id = id ?: throw JsonDataException("Required property 'id' missing at ${reader.path}"),
                elementName = elementName,
                attributes =  attributesDelegate.fromJsonValue(attributesAsJson) as IAttributes,
                children = children ?: throw JsonDataException("Required property 'children' missing at ${reader.path}"))
    }

    override fun toJson(writer: JsonWriter, value: LayoutElement?) {
        throw NotImplementedError("No layout element to json implementation")
    }
}
