package com.laidpack.sourcerer.service

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import com.laidpack.sourcerer.service.api.LayoutElement
import java.lang.IllegalStateException

interface InflaterComponent {
    fun inflate(activity: Activity, json: String)
    fun inflate(activity: Activity, layoutMap: LayoutMap)

    fun addFactory(factory: LayoutElement.Factory)

    companion object {
        private val instance by lazy { serviceLocator().inflaterService }

        fun addFactory(factory: LayoutElement.Factory) {
            instance.addFactory(factory)
        }
    }
}

class InflaterModule : InflaterComponent {
    private val factories = mutableMapOf<String, LayoutElement.Factory>()

    override fun inflate(activity: Activity, json: String) {
        val layoutMap = SerializerComponent.deserialize<LayoutMap>(json)
        inflate(activity, layoutMap)
    }

    override fun inflate(activity: Activity, layoutMap: LayoutMap) {
        val rootNode = layoutMap.elements[layoutMap.rootElementId] as LayoutProperties
        inflateNode(rootNode, activity, layoutMap)
    }

    private fun inflateNode(node: LayoutProperties, activity: Activity, layoutMap: LayoutMap, parentView: ViewGroup? = null) {
        val factory = getViewFactory(node)
        val view = factory.create(activity, node.attributes)
        var layoutParams: ViewGroup.LayoutParams? = null
        node.layoutAttributes?.let {
            val layoutParamsFactory = getLayoutParamsFactory(node)
            layoutParams = layoutParamsFactory.create(activity, it)
        }
        addViewToParent(view, layoutParams, parentView, activity)
        if (node.children.isNotEmpty()) {
            val viewGroup = view as? ViewGroup
                    ?: throw IllegalStateException("element '${node.elementName}/${node.id}' has children but the view is not a view group")
            for (childId in node.children) {
                val childNode = layoutMap.elements[childId]
                        ?: throw IllegalStateException("Child '$childId' defined in '${node.elementName}/${node.id} does not exist in layout map")
                inflateNode(childNode, activity, layoutMap, viewGroup)
            }
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

    private fun getViewFactory(node: LayoutProperties): LayoutElement.ViewFactory {
        val factory = getFactory(node)
        if (factory !is LayoutElement.ViewFactory) {
            throw IllegalArgumentException("Factory is not a view factory.\nExpected: LayoutElement.ViewFactory, actual: ${factory::class.java.canonicalName}")
        }
        return factory
    }

    private fun getLayoutParamsFactory(node: LayoutProperties): LayoutElement.LayoutParamsFactory {
        val elementName = node.layoutParamsElementName ?: throw IllegalArgumentException("Layout element ${node.elementName}/${node.id} has no associated layoutParamsElementName")
        val factory = getFactory(elementName)
        if (factory !is LayoutElement.LayoutParamsFactory) {
            throw IllegalArgumentException("Factory is not a layout params factory.\nExpected: LayoutElement.LayoutParamsFactory, actual: ${factory::class.java.canonicalName}")
        }
        return factory
    }

    private fun getFactory(node: LayoutProperties): LayoutElement.Factory {
        return getFactory(node.elementName)
    }

    private fun getFactory(elementName: String): LayoutElement.Factory {
        return factories[elementName]
                ?: throw IllegalArgumentException("No factory registered for element '$elementName'")
    }


    override fun addFactory(factory: LayoutElement.Factory) {
        if (factories.containsKey(factory.elementName)) {
            throw IllegalArgumentException("factory for element '${factory.elementName}' is already registered")
        }
        factories[factory.elementName] = factory
    }
}