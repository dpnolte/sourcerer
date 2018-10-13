package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.TableRow
import java.lang.Class
import kotlin.String

open class TableRowFactory<TView : TableRow, TAttributes : TableRowAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = TableRow(context)

    companion object {
        const val elementType: String = "tableRow"

        inline operator fun <reified TView : TableRow, reified TAttributes : TableRowAttributes> invoke() = TableRowFactory(TView::class.java, TAttributes::class.java)
    }
}
