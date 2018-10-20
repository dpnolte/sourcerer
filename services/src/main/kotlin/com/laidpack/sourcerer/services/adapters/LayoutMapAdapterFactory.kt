package com.laidpack.sourcerer.services.adapters

import android.util.Log
import com.laidpack.sourcerer.services.BuildConfig
import com.laidpack.sourcerer.services.LayoutProperties
import com.laidpack.sourcerer.services.LayoutMap
import com.laidpack.sourcerer.services.api.IAttributes
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

    override fun toJson(writer: JsonWriter, value: LayoutMap?) {
        TODO("not yet implemented, do I need this?")
    }

    override fun fromJson(reader: JsonReader): LayoutMap? {
        if (reader.peek() != JsonReader.Token.BEGIN_ARRAY) {
            if (BuildConfig.DEBUG) throw JsonDataException("Expected json array for layout map")
            else Log.e("LayoutMapAdapter", "Expected json array for layout map")
        }
        val childToParent = mutableMapOf<String, String>()
        val elements = mutableMapOf<String, LayoutProperties>()
        val typeToElements = mutableMapOf<String, MutableList<String>>()
        val jsonValues = mutableMapOf<String, Map<*, *>>()

        reader.beginArray()
        while(reader.hasNext()) {
            val jsonValue = reader.readJsonValue()
            val element = layoutElementDelegate.fromJsonValue(jsonValue)
            if (element != null) {
                if (elements.containsKey(element.id)) throw JsonDataException("Elements with duplicate id: ${element.id}")
                elements[element.id] = element
                if (!typeToElements.containsKey(element.type)) {
                    typeToElements[element.type] = mutableListOf()
                }
                (typeToElements[element.type] as MutableList<String>).add(element.id)
                element.children.forEach { childId ->
                    childToParent[childId] = element.id
                }
                if (jsonValue !is Map<*, *>) throw JsonDataException("Invalid json type, expected object but is '${if (jsonValue != null) jsonValue::class.java.canonicalName else "null"}' for $jsonValue")
                jsonValues[element.id] = jsonValue
            }
        }
        reader.endArray()

        // assign parent ids (another loop, TBC)
        val rootElement = assignParentIdsAndReturnRootId(elements, childToParent)

        // now process layout attributes if any
        for (element in elements.values) {
            val (lpType, lpDelegate) = getLayoutParamsDelegate(element, elements)
                    ?: defaultLayoutParamsDelegate
            val jsonValue = jsonValues[element.id] as Map<*,*>
            val attributesValue = jsonValue["attributes"]
            if (attributesValue != null) {
                val attributes = lpDelegate.fromJsonValue(attributesValue) as? IAttributes
                    ?: continue
                element.layoutAttributes = attributes
                element.layoutParamsType = lpType
            }
        }

        return LayoutMap(elements,typeToElements, rootElement)
    }

    private fun assignParentIdsAndReturnRootId(
            elements: Map<String, LayoutProperties>,
            childToParent: Map<String, String>
    ): String {
        var rootElement = ""
        for (element in elements.values) {
            when {
                childToParent.containsKey(element.id) -> element.parentId = childToParent[element.id]
                rootElement.isBlank() -> rootElement = element.id
                else -> throw JsonDataException("Multiple root elements found. Only one root element is allowed to be set as activity content view\n.First root element: '$rootElement', second: '${element.type}/${element.id}'")
            }
        }
        return rootElement
    }

    private fun getLayoutParamsDelegate(
            element: LayoutProperties,
            elements: Map<String,LayoutProperties>
    ): LayoutParamsDelegateWrapper? {
        var parentElement = if (element.parentId != null) elements[element.parentId as String] else null
        var depth = 0
        while(parentElement != null) {
            try {
                return elementToLayoutParamsDelegate(parentElement.type)
            } catch (e: IllegalArgumentException) {
                // do nothing..
                depth += 1
                if (depth == MAX_DEPTH) {
                    throw JsonDataException("Max depth ($MAX_DEPTH) reached for searching parent element with layout parameters. Element: ${element.type}/${element.id}. Is element added as child to another element and has the parent linked view group to layout params element type (@see SerializerComponent::associateViewGroupWithLayoutParams)?")
                }
            }
            parentElement = elements[parentElement.parentId]
        }
        return null
    }

    companion object {
        const val MAX_DEPTH = 10
    }
}