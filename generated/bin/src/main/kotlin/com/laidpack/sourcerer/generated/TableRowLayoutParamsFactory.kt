package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.TableRow
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class TableRowLayoutParamsFactory<TLayoutParams : TableRow.LayoutParams, TAttributes : TableRowLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : LinearLayoutLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "android.widget.TableRow.LayoutParams"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): ViewGroup.LayoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT)

    companion object {
        init {
            InflaterComponent.addFactory(TableRowLayoutParamsFactory<TableRow.LayoutParams, TableRowLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : TableRow.LayoutParams, reified TAttributes : TableRowLayoutParamsAttributes> invoke() = TableRowLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
