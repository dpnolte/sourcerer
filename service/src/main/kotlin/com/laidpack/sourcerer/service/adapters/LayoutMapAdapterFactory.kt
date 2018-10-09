package com.laidpack.sourcerer.service.adapters

import android.util.Log
import com.laidpack.sourcerer.service.BuildConfig
import com.laidpack.sourcerer.service.LayoutProperties
import com.laidpack.sourcerer.service.LayoutMap
import com.laidpack.sourcerer.service.api.IAttributes
import com.squareup.moshi.*
import java.lang.reflect.Type
import kotlin.IllegalArgumentException

internal typealias layoutAdapterRetriever = (elementName: String) -> LayoutParamsDelegateWrapper
internal class LayoutMapAdapterFactory(
        private val elementToLayoutParamsDelegate: layoutAdapterRetriever,
        private val defaultLayoutParamsDelegate: () -> LayoutParamsDelegateWrapper
): JsonAdapter.Factory {
    override fun create(t: Type?, annotations: MutableSet<out Annotation>?, moshi: Moshi?): JsonAdapter<*>? {
        val type = t ?: return null
        val rawType = Types.getRawType(type)
        if (rawType != targetType) {
            return null
        }

        return LayoutMapAdapter(moshi as Moshi, elementToLayoutParamsDelegate, defaultLayoutParamsDelegate())
    }
    companion object {
        val targetType = LayoutMap::class.java
    }
}

data class LayoutParamsDelegateWrapper(val elementName: String, val delegate: JsonAdapter<*>)

// TODO: maybe add support for denormalized json?
internal class LayoutMapAdapter(
        moshi: Moshi,
        private val elementToLayoutParamsDelegate: layoutAdapterRetriever,
        private val defaultLayoutParamsDelegate: LayoutParamsDelegateWrapper
) : JsonAdapter<LayoutMap>() {

    private val layoutElementDelegate = moshi.adapter<LayoutProperties>(LayoutProperties::class.java)
    private var rootElement : String = ""
    private val childToParent = mutableMapOf<String, String>()
    private val elements = mutableMapOf<String, LayoutProperties>()
    private val jsonValues = mutableMapOf<String, Map<*, *>>()

    override fun toJson(writer: JsonWriter, value: LayoutMap?) {
        TODO("not yet implemented, do I need this?")
    }

    override fun fromJson(reader: JsonReader): LayoutMap? {
        if (reader.peek() != JsonReader.Token.BEGIN_ARRAY) {
            if (BuildConfig.DEBUG) throw JsonDataException("Expected json array for layout map")
            else Log.e("LayoutMapAdapter", "Expected json array for layout map")
        }

        reader.beginArray()
        while(reader.hasNext()) {
            val jsonValue = reader.readJsonValue()
            val element = layoutElementDelegate.fromJsonValue(jsonValue)
            if (element != null) {
                elements[element.id] = element
                element.children.forEach { childId ->
                    childToParent[childId] = element.id
                }
                if (jsonValue !is Map<*, *>) throw JsonDataException("Invalid json type, expected object but is '${if (jsonValue != null) jsonValue::class.java.canonicalName else "null"}' for $jsonValue")
                jsonValues[element.id] = jsonValue
            }
        }
        reader.endArray()

        // assign parent ids (another loop, TBC)
        assignParentIds(elements, childToParent)

        // now process layout attributes if any
        for (element in elements.values) {
            val (lpElementName, lpDelegate) = getLayoutParamsDelegate(element, elements)
                    ?: defaultLayoutParamsDelegate
            val jsonValue = jsonValues[element.id] as Map<*,*>
            val attributesValue = jsonValue["attributes"]
            if (attributesValue != null) {
                val attributes = lpDelegate.fromJsonValue(attributesValue) as? IAttributes
                    ?: continue
                element.layoutAttributes = attributes
                element.layoutParamsElementName = lpElementName
            }
        }

        return LayoutMap(elements,rootElement)
    }

    private fun assignParentIds(
            elements: Map<String, LayoutProperties>,
            childToParent: Map<String, String>
    ) {
        for (element in elements.values) {
            when {
                childToParent.containsKey(element.id) -> element.parentId = childToParent[element.id]
                rootElement.isBlank() -> rootElement = element.id
                else -> throw JsonDataException("Multiple root elements found. Only one root element is allowed to be set as activity content view\n.First root element: '$rootElement', second: '${element.elementName}/${element.id}'")
            }
        }
    }

    private fun getLayoutParamsDelegate(
            element: LayoutProperties,
            elements: Map<String,LayoutProperties>
    ): LayoutParamsDelegateWrapper? {
        var parentElement = if (element.parentId != null) elements[element.parentId as String] else null
        while(parentElement != null) {
            try {
                return elementToLayoutParamsDelegate(element.elementName)
            } catch (e: IllegalArgumentException) {
                // do nothing..
            }
            parentElement = elements[parentElement.parentId]
        }
        return null
    }
}