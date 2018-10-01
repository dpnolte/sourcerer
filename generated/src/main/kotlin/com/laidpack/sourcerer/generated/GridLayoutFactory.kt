package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import com.laidpack.sourcerer.generated.init
import com.laidpack.sourcerer.generated.toPorterDuffMode
import com.laidpack.sourcerer.generated.toScaleType
import com.laidpack.sourcerer.generated.toTruncateAt
import kotlin.String

open class GridLayoutFactory<TView : GridLayout, TAttributes : GridLayoutAttributes> : ViewGroupFactory<TView, TAttributes>() {
    override val elementName: String = "gridLayout"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = GridLayout(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
        val view = v as GridLayout
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
}
