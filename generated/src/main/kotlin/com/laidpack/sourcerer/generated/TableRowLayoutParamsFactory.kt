package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.TableRow
import com.laidpack.generator.api.LayoutParamsElement
import java.lang.Class
import kotlin.String

@LayoutParamsElement(
        elementType = TableRowLayoutParamsFactory.elementType,
        attributesClazz = TableRowLayoutParamsAttributes::class
)
open class TableRowLayoutParamsFactory<TLayoutParams : TableRow.LayoutParams, TAttributes : TableRowLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : LinearLayoutLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT)

    override fun init(
        layoutParams: ViewGroup.LayoutParams,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(layoutParams, context, attributes)
        if (layoutParams is TableRow.LayoutParams) {
            layoutParams.apply {
                attributes.TableRow_Cell_layout_column?.let {
                    if (it != column) {
                        column = it
                    }
                }
                attributes.TableRow_Cell_layout_span?.let {
                    if (it != span) {
                        span = it
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "android.widget.TableRow.LayoutParams"

        inline operator fun <reified TLayoutParams : TableRow.LayoutParams, reified TAttributes : TableRowLayoutParamsAttributes> invoke() = TableRowLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
