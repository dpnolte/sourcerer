package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.view.View
import androidx.leanback.widget.HorizontalGridView
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = HorizontalGridViewFactory.elementType,
        attributesClazz = HorizontalGridViewAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class HorizontalGridViewFactory<TView : HorizontalGridView, TAttributes : HorizontalGridViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : BaseGridViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = HorizontalGridView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is HorizontalGridView) {
            view.apply {
                attributes.lbrowHeight?.let {
                    if (height != it) {
                        setRowHeight(it)
                    }
                }
                attributes.lbnumberOfRows?.let {
                    setNumRows(it)
                }
            }
        }
    }

    companion object {
        const val elementType: String = "horizontalGridView"

        inline operator fun <reified TView : HorizontalGridView, reified TAttributes : HorizontalGridViewAttributes> invoke() = HorizontalGridViewFactory(TView::class.java, TAttributes::class.java)
    }
}
