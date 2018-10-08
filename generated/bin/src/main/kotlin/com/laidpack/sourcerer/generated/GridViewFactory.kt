package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.GridView
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.Int
import kotlin.String

open class GridViewFactory<TView : GridView, TAttributes : GridViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AdapterViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "gridView"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = GridView(context)

    override fun init(
        view: TView,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        view.init {
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
            attributes.stretchMode?.let {
                if (stretchMode != it.value) {
                    stretchMode = it.value
                }
            }
            attributes.columnWidth?.let {
                if (requestedColumnWidth != it) {
                    columnWidth = it
                }
            }
            attributes.numColumns?.let {
                if (numColumns != it.value) {
                    numColumns = it.value
                }
            }
            attributes.gravity?.let {
                if (gravity != it) {
                    gravity = it
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
