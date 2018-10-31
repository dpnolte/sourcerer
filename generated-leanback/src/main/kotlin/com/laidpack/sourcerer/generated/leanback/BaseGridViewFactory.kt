package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.view.View
import androidx.leanback.widget.BaseGridView
import com.laidpack.sourcerer.generated.recyclerview.RecyclerViewFactory
import java.lang.Class
import kotlin.String

open class BaseGridViewFactory<TView : BaseGridView, TAttributes : BaseGridViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : RecyclerViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is BaseGridView) {
            view.apply {
                attributes.lbandroid_verticalSpacing?.let {
                    if (verticalSpacing != it) {
                        verticalSpacing = it
                    }
                }
                attributes.lbandroid_horizontalSpacing?.let {
                    if (horizontalSpacing != it) {
                        horizontalSpacing = it
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "baseGridView"

        inline operator fun <reified TView : BaseGridView, reified TAttributes : BaseGridViewAttributes> invoke() = BaseGridViewFactory(TView::class.java, TAttributes::class.java)
    }
}
