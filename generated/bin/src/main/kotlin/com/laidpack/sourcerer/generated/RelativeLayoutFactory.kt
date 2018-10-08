package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.RelativeLayout
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.Int
import kotlin.String

open class RelativeLayoutFactory<TView : RelativeLayout, TAttributes : RelativeLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "relativeLayout"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = RelativeLayout(context)

    override fun init(
        view: TView,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        view.init {
            attributes.gravity?.let {
                if (gravity != it) {
                    gravity = it
                }
            }
        }
    }

    companion object {
        init {
            InflaterComponent.addFactory(RelativeLayoutFactory<RelativeLayout, RelativeLayoutAttributes>())
        }

        inline operator fun <reified TView : RelativeLayout, reified TAttributes : RelativeLayoutAttributes> invoke() = RelativeLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
