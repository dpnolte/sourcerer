package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import com.laidpack.sourcerer.service.api.init
import kotlin.String

open class GridViewFactory<TView : GridView, TAttributes : GridViewAttributes> : AdapterViewFactory<TView, TAttributes>() {
    override val elementName: String = "gridView"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = GridView(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as GridView
        view.init {
            attributes.stretchMode?.let {
                if (stretchMode != it.value) {
                    stretchMode = it.value
                }
            }
            attributes.numColumns?.let {
                if (numColumns != it.value) {
                    numColumns = it.value
                }
            }
            if (Build.VERSION.SDK_INT >= 16) {
                attributes.horizontalSpacing?.let {
                    if (horizontalSpacing != it) {
                        horizontalSpacing = it
                    }
                }
                attributes.verticalSpacing?.let {
                    if (verticalSpacing != it) {
                        verticalSpacing = it
                    }
                }
                attributes.columnWidth?.let {
                    if (columnWidth != it) {
                        columnWidth = it
                    }
                }
                attributes.gravity?.let {
                    if (gravity != it) {
                        gravity = it
                    }
                }
            }
        }
    }
}
