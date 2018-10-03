package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import com.laidpack.sourcerer.service.api.LayoutParamsFactoryComponent
import kotlin.String

open class TableLayoutFactory<TView : TableLayout, TAttributes : TableLayoutAttributes> : LinearLayoutFactory<TView, TAttributes>() {
    override val elementName: String = "tableLayout"

    override val defaultLayoutParamsFactory: LayoutParamsFactoryComponent<*, *>
        get() = ViewGroupLayoutParamsFactory<ViewGroup.LayoutParams, ViewGroupLayoutParamsAttributes>()

    override fun createInstance(context: Context): View = TableLayout(context)

    override fun init(
        v: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(v, context, attributes)
    }
}
