package com.laidpack.sourcerer.generated.swiperefreshlayout

import android.content.Context
import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.ViewGroupFactory
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = SwipeRefreshLayoutFactory.elementType,
        attributesClazz = SwipeRefreshLayoutAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class SwipeRefreshLayoutFactory<TView : SwipeRefreshLayout, TAttributes : SwipeRefreshLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = SwipeRefreshLayout(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is SwipeRefreshLayout) {
            view.apply {
                attributes.enabled?.let {
                    if (isEnabled != it) {
                        isEnabled = it
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "swipeRefreshLayout"

        inline operator fun <reified TView : SwipeRefreshLayout, reified TAttributes : SwipeRefreshLayoutAttributes> invoke() = SwipeRefreshLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
