package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.view.View
import androidx.leanback.widget.VerticalGridView
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = VerticalGridViewFactory.elementType,
        attributesClazz = VerticalGridViewAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class VerticalGridViewFactory<TView : VerticalGridView, TAttributes : VerticalGridViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : BaseGridViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = VerticalGridView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is VerticalGridView) {
            view.apply {
                attributes.lbcolumnWidth?.let {
                    if (width != it) {
                        setColumnWidth(it)
                    }
                }
                attributes.lbnumberOfColumns?.let {
                    setNumColumns(it)
                }
            }
        }
    }

    companion object {
        const val elementType: String = "verticalGridView"

        inline operator fun <reified TView : VerticalGridView, reified TAttributes : VerticalGridViewAttributes> invoke() = VerticalGridViewFactory(TView::class.java, TAttributes::class.java)
    }
}
