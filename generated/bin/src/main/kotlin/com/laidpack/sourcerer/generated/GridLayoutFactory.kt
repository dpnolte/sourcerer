package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.GridLayout
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.Int
import kotlin.String

open class GridLayoutFactory<TView : GridLayout, TAttributes : GridLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "gridLayout"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = GridLayout(context)

    override fun init(
        view: TView,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        view.init {
            attributes.orientation?.let {
                if (orientation != it) {
                    orientation = it
                }
            }
            attributes.rowCount?.let {
                if (rowCount != it) {
                    rowCount = it
                }
            }
            attributes.columnCount?.let {
                if (columnCount != it) {
                    columnCount = it
                }
            }
            attributes.useDefaultMargins?.let {
                if (useDefaultMargins != it) {
                    useDefaultMargins = it
                }
            }
            attributes.alignmentMode?.let {
                if (alignmentMode != it) {
                    alignmentMode = it
                }
            }
            attributes.rowOrderPreserved?.let {
                if (isRowOrderPreserved != it) {
                    isRowOrderPreserved = it
                }
            }
            attributes.columnOrderPreserved?.let {
                if (isColumnOrderPreserved != it) {
                    isColumnOrderPreserved = it
                }
            }
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(GridLayoutFactory<GridLayout, GridLayoutAttributes>())
        }

        inline operator fun <reified TView : GridLayout, reified TAttributes : GridLayoutAttributes> invoke() = GridLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
