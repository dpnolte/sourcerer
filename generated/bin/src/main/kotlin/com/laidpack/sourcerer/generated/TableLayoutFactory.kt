package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.TableLayout
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class TableLayoutFactory<TView : TableLayout, TAttributes : TableLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : LinearLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "tableLayout"

    override fun createInstance(context: Context): View = TableLayout(context)

    companion object {
        init {
            InflaterComponent.addFactory(TableLayoutFactory<TableLayout, TableLayoutAttributes>())
        }

        inline operator fun <reified TView : TableLayout, reified TAttributes : TableLayoutAttributes> invoke() = TableLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
