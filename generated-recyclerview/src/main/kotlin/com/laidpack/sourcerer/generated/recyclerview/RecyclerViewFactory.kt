package com.laidpack.sourcerer.generated.recyclerview

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.ViewGroupFactory
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = RecyclerViewFactory.elementType,
        attributesClazz = RecyclerViewAttributes::class,
        layoutParamAttributesClazz = RecyclerViewLayoutParamsAttributes::class
)
open class RecyclerViewFactory<TView : RecyclerView, TAttributes : RecyclerViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = RecyclerView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is RecyclerView) {
            view.apply {
                attributes.clipToPadding?.let {
                    if (isEnabled != it) {
                        isNestedScrollingEnabled = it
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "recyclerView"

        inline operator fun <reified TView : RecyclerView, reified TAttributes : RecyclerViewAttributes> invoke() = RecyclerViewFactory(TView::class.java, TAttributes::class.java)
    }
}
