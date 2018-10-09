package com.laidpack.sourcerer.generated

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.RelativeLayout
import com.laidpack.sourcerer.service.InflaterComponent
import com.laidpack.sourcerer.service.api.init
import java.lang.Class
import kotlin.String

open class RelativeLayoutFactory<TView : RelativeLayout, TAttributes : RelativeLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "relativeLayout"

    override fun createInstance(context: Context): View = RelativeLayout(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is RelativeLayout) {
            view.init {
                if (Build.VERSION.SDK_INT >= 16) {
                    attributes.gravity?.let {
                        if (gravity != it) {
                            gravity = it
                        }
                    }
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
