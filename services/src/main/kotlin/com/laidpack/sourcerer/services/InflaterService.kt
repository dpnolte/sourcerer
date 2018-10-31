package com.laidpack.sourcerer.services

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import com.laidpack.sourcerer.services.api.LayoutElement
import com.laidpack.sourcerer.services.layout.InflatedLayoutMap
import com.laidpack.sourcerer.services.layout.LayoutMap
import com.laidpack.sourcerer.services.layout.LayoutProperties
import java.lang.IllegalStateException
import java.lang.ref.WeakReference

interface InflaterComponent {
    fun inflate(activity: Activity, json: String, parentView: ViewGroup? = null): InflatedLayoutMap
    fun inflate(activity: Activity, layoutMap: LayoutMap, parentView: ViewGroup? = null): InflatedLayoutMap

    fun addFactory(factory: LayoutElement.Factory)

    companion object {

        fun bootstrap(
                implementation: InflaterComponent = InflaterModule()
        ): InflaterComponent {
            return implementation
        }
    }
}

/**
 * Service that inflates json or LayoutMap to Android's View
 * @param enableViewRecycling experimental recycling of views
 */
class InflaterModule(private val enableViewRecycling: Boolean = false) : InflaterComponent {
    private val factories = mutableMapOf<String, LayoutElement.Factory>()
    private val viewTypeToElementType = mutableMapOf<Class<*>, String>()
    private var layoutMapReference: WeakReference<InflatedLayoutMap>? = null

    override fun inflate(
            activity: Activity,
            json: String,
            parentView: ViewGroup?
    ): InflatedLayoutMap {
        val layoutMap = SerializerComponent.deserialize<LayoutMap>(json)
        return inflate(activity, layoutMap, parentView)
    }

    override fun inflate(
            activity: Activity,
            layoutMap: LayoutMap,
            parentView: ViewGroup?
    ): InflatedLayoutMap {
        val oldLayoutMap = layoutMapReference?.get()
        val recyclableViews = if (enableViewRecycling) {
            getRecyclableViews(parentView, layoutMap, oldLayoutMap)
        } else mapOf()
        parentView?.removeAllViews()

        inflateNode(
                layoutMap.root,
                activity,
                layoutMap,
                recyclableViews,
                parentView
        )
        val map = InflatedLayoutMap(
                viewTypeToElementType,
                layoutMap.elements,
                layoutMap.typeToElements,
                layoutMap.rootElementId
        )
        layoutMapReference = WeakReference(map)

        return map
    }

    private fun inflateNode(
            node: LayoutProperties,
            activity: Activity,
            layoutMap: LayoutMap,
            recyclableViews: Map<Int, View>,
            parentView: ViewGroup? = null
    ) {
        val view = getView(node, activity, recyclableViews)
        view.id = node.hashedId
        viewTypeToElementType[view::class.java] = node.type
        var layoutParams: ViewGroup.LayoutParams? = null
        node.layoutAttributes?.let {
            val layoutParamsFactory = getLayoutParamsFactory(node)
            layoutParams = layoutParamsFactory.create(activity, it)
        }
        addViewToParent(view, layoutParams, parentView, activity)
        if (node.children.isNotEmpty()) {
            val viewGroup = view as? ViewGroup
                    ?: throw IllegalStateException("element '${node.type}/${node.id}' has children but the view is not a view group")
            for (childId in node.children) {
                inflateNode(layoutMap[childId], activity, layoutMap, recyclableViews, viewGroup)
            }
        }
    }

    private fun getView(
            node: LayoutProperties,
            activity: Activity,
            recyclableViews: Map<Int, View>
    ): View {
        val factory = getViewFactory(node)
        return if (enableViewRecycling && recyclableViews.contains(node.hashedId)) {
            val view = recyclableViews[node.hashedId] as View
            val parent = view.parent
            if (parent != null && parent is ViewGroup) {
                parent.removeView(view)
            }
            factory.update(view, activity, node.attributes)
            view
        } else {
            factory.create(activity, node.attributes)
        }
    }

    private fun addViewToParent(
            view: View,
            layoutParams: ViewGroup.LayoutParams?,
            parentView: ViewGroup?,
            activity: Activity
    ) {
        when {
            parentView != null -> {
                layoutParams?.let { view.layoutParams = it }
                parentView.addView(view)
            }
            layoutParams != null -> activity.addContentView(view, layoutParams)
            else -> activity.addContentView(
                    view,
                    ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                    )
            )
        }
    }

    private fun getRecyclableViews(
            parentView: ViewGroup?,
            layoutMap: LayoutMap,
            oldLayoutMap: InflatedLayoutMap?
    ): Map<Int, View> {
        val views = mutableMapOf<Int, View>()
        if (oldLayoutMap != null && parentView != null) {
            for (node in layoutMap) {
                if (oldLayoutMap.contains(node.id)) {
                    val view = parentView.findViewById<View>(node.hashedId)
                    if (view != null) {
                        if (view is ViewGroup) {
                            for (oldChildId in oldLayoutMap[node.id].children) {
                                if (!node.children.contains(oldChildId)) {
                                    view.removeView(view.findViewById(oldChildId.hashCode()))
                                }
                            }
                        }
                        views[node.hashedId] = view
                    }
                }
            }
        }
        return views
    }

    private fun getViewFactory(node: LayoutProperties): LayoutElement.ViewFactory {
        val factory = getFactory(node)
        if (factory !is LayoutElement.ViewFactory) {
            throw IllegalArgumentException("Factory is not a view factory.\nExpected: LayoutElement.ViewFactory, actual: ${factory::class.java.canonicalName}")
        }
        return factory
    }

    private fun getLayoutParamsFactory(node: LayoutProperties): LayoutElement.LayoutParamsFactory {
        val elementType = node.layoutParamsType ?: throw IllegalArgumentException("Layout element ${node.type}/${node.id} has no associated layoutParams element type")
        val factory = getFactory(elementType)
        if (factory !is LayoutElement.LayoutParamsFactory) {
            throw IllegalArgumentException("Factory is not a layout params factory.\nExpected: LayoutElement.LayoutParamsFactory, actual: ${factory::class.java.canonicalName}")
        }
        return factory
    }

    private fun getFactory(node: LayoutProperties): LayoutElement.Factory {
        return getFactory(node.type)
    }

    private fun getFactory(elementType: String): LayoutElement.Factory {
        return factories[elementType]
                ?: throw IllegalArgumentException("No factory registered for element '$elementType'")
    }


    override fun addFactory(factory: LayoutElement.Factory) {
        if (factories.containsKey(factory.elementType)) {
            throw IllegalArgumentException("factory for element '${factory.elementType}' is already registered")
        }
        factories[factory.elementType] = factory
    }
}