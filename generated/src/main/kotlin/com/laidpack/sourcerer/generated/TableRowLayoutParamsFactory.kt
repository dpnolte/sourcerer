package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.TableRow
import kotlin.String

open class TableRowLayoutParamsFactory<TLayoutParams : TableRow.LayoutParams, TAttributes : TableRowLayoutParamsAttributes> : LinearLayoutLayoutParamsFactory<TLayoutParams, TAttributes>() {
    override val elementName: String = "layoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT)
}
