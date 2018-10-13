package com.laidpack.sourcerer.services

import android.app.Activity
import android.view.View
import com.laidpack.sourcerer.services.api.IAttributes
import java.lang.IllegalStateException
import kotlin.IllegalArgumentException


open class LayoutMap(
        val elements : Map<String, LayoutProperties>,
        val typeToElements: Map<String /* elementType */, List<String> /* element ids */>,
        val rootElementId: String
) : Iterable<LayoutProperties> {
    fun containsType(type: String): Boolean {
        return typeToElements.containsKey(type)
    }
    fun firstTypeOf(type: String): LayoutProperties {
        return this[type, 0]
    }
    fun typeOf(type: String): List<LayoutProperties> {
        val elementIds = typeToElements[type] ?: throw IllegalArgumentException("Layout map contains no element of type '$type'")
        return elementIds.map { this[it] }
    }
    fun contains (id: String): Boolean {
        return elements.containsKey(id)
    }
    fun contains (hashedId: Int): Boolean {
        return elements.any { it.value.hashedId == hashedId }
    }
    operator fun get(type: String, index: Int): LayoutProperties {
        val elementIds = typeToElements[type] ?: throw IllegalArgumentException("Layout map contains no element of type '$type'")
        val elementId = elementIds[index]
        return elements[elementId] ?: throw IllegalStateException("Layout map has type '$type' registered, but element id '${elementIds.first()}' not")
    }
    operator fun get(elementId: String): LayoutProperties {
        return elements[elementId] ?: throw IllegalArgumentException("Layout map contains no element with id '$elementId'")
    }
    override operator fun iterator(): Iterator<LayoutProperties> {
        return elements.values.iterator()
    }
    val root: LayoutProperties
        get() = elements[rootElementId] as LayoutProperties
    val size: Int
        get() = elements.size
    fun first(): LayoutProperties {
        return elements.values.first()
    }
}

data class LayoutProperties(
        val id: String,
        val type: String,
        val attributes: IAttributes,
        val children: List<String>
) {
    val hashedId: Int = id.hashCode()
    var parentId: String? = null
    var layoutAttributes: IAttributes? = null
    var layoutParamsType: String? = null
}

class InflatedLayoutMap (
        val viewTypeToElementType: Map<Class<*>, String>,
        elements : Map<String, LayoutProperties>,
        typeToElements: Map<String /* elementType */, List<String> /* element ids */>,
        rootElementId: String
)  : LayoutMap (elements, typeToElements, rootElementId) {

    inline fun <reified TView: View> viewsOfType(activity: Activity): List<TView> {
        val elementType = viewTypeToElementType[TView::class.java]
                ?: throw IllegalArgumentException("View ${TView::class.java} is not mapped to an element type")
        val elements = typeOf(elementType)
        val views = mutableListOf<TView>()
        for (element in elements) {
            val view = activity.findViewById<TView>(element.hashedId)
            views.add(view)
        }
        return views
    }

    inline fun <reified TView: View> firstViewOfType(activity: Activity): TView {
        val elementType = viewTypeToElementType[TView::class.java]
                ?: throw IllegalArgumentException("View ${TView::class.java} is not mapped to an element type")
        val element = firstTypeOf(elementType)
        return activity.findViewById(element.hashedId)
    }

    fun <TView: View> findViewById(activity: Activity, id: String): TView {
        val element = this[id]
        return activity.findViewById(element.hashedId)
    }
}