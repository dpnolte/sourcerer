package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.TableLayout
import kotlin.String

open class TableLayoutLayoutParamsFactory<TLayoutParams : TableLayout.LayoutParams, TAttributes : TableLayoutLayoutParamsAttributes> : LinearLayoutLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT)

    override fun init(
        lp: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(lp, context, attributes)
    }
}
