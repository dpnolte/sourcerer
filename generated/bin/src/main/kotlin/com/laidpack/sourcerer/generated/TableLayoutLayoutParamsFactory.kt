package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.TableLayout
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class TableLayoutLayoutParamsFactory<TLayoutParams : TableLayout.LayoutParams, TAttributes : TableLayoutLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : LinearLayoutLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "android.widget.TableLayout.LayoutParams"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): ViewGroup.LayoutParams = TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT)

    companion object {
        init {
            InflaterComponent.addFactory(TableLayoutLayoutParamsFactory<TableLayout.LayoutParams, TableLayoutLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : TableLayout.LayoutParams, reified TAttributes : TableLayoutLayoutParamsAttributes> invoke() = TableLayoutLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
