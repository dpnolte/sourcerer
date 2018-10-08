package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.wear.widget.SwipeDismissFrameLayout
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class SwipeDismissFrameLayoutFactory<TView : SwipeDismissFrameLayout, TAttributes : SwipeDismissFrameLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "swipeDismissFrameLayout"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = SwipeDismissFrameLayout(context)

    companion object {
        init {
            InflaterComponent.addFactory(SwipeDismissFrameLayoutFactory<SwipeDismissFrameLayout, SwipeDismissFrameLayoutAttributes>())
        }

        inline operator fun <reified TView : SwipeDismissFrameLayout, reified TAttributes : SwipeDismissFrameLayoutAttributes> invoke() = SwipeDismissFrameLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
