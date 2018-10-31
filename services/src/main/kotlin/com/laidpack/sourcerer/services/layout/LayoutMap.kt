package com.laidpack.sourcerer.services.layout

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
    fun findFirstTypeOf(type: String): LayoutProperties? {
        return findByTypeAndIndex(type, 0)
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
    fun findByTypeAndIndex(type: String, index: Int): LayoutProperties? {
        val elementIds = typeToElements[type] ?: return null
        val elementId = elementIds[index]
        return elements[elementId] ?: return null
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