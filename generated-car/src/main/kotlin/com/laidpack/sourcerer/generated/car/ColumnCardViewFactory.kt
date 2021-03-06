package com.laidpack.sourcerer.generated.car

import android.content.Context
import android.view.View
import androidx.car.widget.ColumnCardView
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import com.laidpack.sourcerer.generated.cardview.CardViewFactory
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = ColumnCardViewFactory.elementType,
        attributesClazz = ColumnCardViewAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class ColumnCardViewFactory<TAttributes : ColumnCardViewAttributes>(attributesType: Class<TAttributes>) : CardViewFactory<ColumnCardView, TAttributes>(ColumnCardView::class.java, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = ColumnCardView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is ColumnCardView) {
            view.apply {
                attributes.columnSpan?.let {
                    if (columnSpan != it) {
                        columnSpan = it
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "columnCardView"

        inline operator fun <reified TAttributes : ColumnCardViewAttributes> invoke() = ColumnCardViewFactory(TAttributes::class.java)
    }
}
