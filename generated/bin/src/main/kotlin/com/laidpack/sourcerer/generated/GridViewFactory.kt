package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.GridView
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.String

open class GridViewFactory<TView : GridView, TAttributes : GridViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AdapterViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "gridView"

    override fun createInstance(context: Context): View = GridView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is GridView) {
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
                        if (requestedHorizontalSpacing != it) {
                            horizontalSpacing = it
                        }
                    }
                    attributes.verticalSpacing?.let {
                        if (verticalSpacing != it) {
                            verticalSpacing = it
                        }
                    }
                    attributes.columnWidth?.let {
                        if (requestedColumnWidth != it) {
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

    companion object {
        init {
            InflaterComponent.addFactory(GridViewFactory<GridView, GridViewAttributes>())
        }

        inline operator fun <reified TView : GridView, reified TAttributes : GridViewAttributes> invoke() = GridViewFactory(TView::class.java, TAttributes::class.java)
    }
}