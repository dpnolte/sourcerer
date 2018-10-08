package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.TableRow
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class TableRowFactory<TView : TableRow, TAttributes : TableRowAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "tableRow"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = TableRow(context)

    companion object {
        init {
            InflaterComponent.addFactory(TableRowFactory<TableRow, TableRowAttributes>())
        }

        inline operator fun <reified TView : TableRow, reified TAttributes : TableRowAttributes> invoke() = TableRowFactory(TView::class.java, TAttributes::class.java)
    }
}
