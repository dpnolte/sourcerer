package com.laidpack.sourcerer.services.layout

import android.app.Activity
import android.view.View

class InflatedLayoutMap (
        val viewTypeToElementType: Map<Class<*>, String>,
        elements : Map<String, LayoutProperties>,
        typeToElements: Map<String /* elementType */, List<String> /* element ids */>,
        rootElementId: String
)  : LayoutMap(elements, typeToElements, rootElementId) {

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
        return findFirstViewOfType<TView>(activity)
                ?: throw IllegalArgumentException("View ${TView::class.java} is not mapped to an element type")
    }
    inline fun <reified TView: View> findFirstViewOfType(activity: Activity): TView? {
        val elementType = viewTypeToElementType[TView::class.java]
            ?: return null
        val element = findFirstTypeOf(elementType)
            ?: return null
        return activity.findViewById(element.hashedId)
    }

    fun <TView: View> findViewById(activity: Activity, id: String): TView {
        val element = this[id]
        return activity.findViewById(element.hashedId)
    }
}