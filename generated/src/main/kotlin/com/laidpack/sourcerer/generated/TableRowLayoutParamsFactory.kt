package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.TableRow
import java.lang.Class
import kotlin.String

open class TableRowLayoutParamsFactory<TLayoutParams : TableRow.LayoutParams, TAttributes : TableRowLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : LinearLayoutLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT)

    companion object {
        const val elementType: String = "android.widget.TableRow.LayoutParams"

        inline operator fun <reified TLayoutParams : TableRow.LayoutParams, reified TAttributes : TableRowLayoutParamsAttributes> invoke() = TableRowLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
