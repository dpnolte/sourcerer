package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.GridView
import com.laidpack.generator.api.ViewGroupElement
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = GridViewFactory.elementType,
        attributesClazz = GridViewAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class GridViewFactory<TView : GridView, TAttributes : GridViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : AbsListViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = GridView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is GridView) {
            view.apply {
                attributes.stretchMode?.let {
                    if (stretchMode != it.value) {
                        stretchMode = it.value
                    }
                }
                if (attributes.numColumns.hasInteger || attributes.numColumns.hasEnum) {
                    val localNumColumns = when {
                        attributes.numColumns.hasInteger -> attributes.numColumns.integer
                        else -> attributes.numColumns.enum
                    }
                    if (numColumns != localNumColumns) {
                        numColumns = localNumColumns
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
                    if (attributes.gravity.hasInteger || attributes.gravity.hasFlags) {
                        val localGravity = when {
                            attributes.gravity.hasInteger -> attributes.gravity.integer
                            else -> attributes.gravity.flags
                        }
                        if (gravity != localGravity) {
                            gravity = localGravity
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "gridView"

        inline operator fun <reified TView : GridView, reified TAttributes : GridViewAttributes> invoke() = GridViewFactory(TView::class.java, TAttributes::class.java)
    }
}
