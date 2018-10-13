package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.TableLayout
import java.lang.Class
import kotlin.String

open class TableLayoutLayoutParamsFactory<TLayoutParams : TableLayout.LayoutParams, TAttributes : TableLayoutLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : LinearLayoutLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT)

    companion object {
        const val elementType: String = "android.widget.TableLayout.LayoutParams"

        inline operator fun <reified TLayoutParams : TableLayout.LayoutParams, reified TAttributes : TableLayoutLayoutParamsAttributes> invoke() = TableLayoutLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
