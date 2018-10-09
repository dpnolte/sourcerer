package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.leanback.widget.BaseCardView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class BaseCardViewFactory<TView : BaseCardView, TAttributes : BaseCardViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "baseCardView"

    override fun createInstance(context: Context): View = BaseCardView(context)

    companion object {
        init {
            InflaterComponent.addFactory(BaseCardViewFactory<BaseCardView, BaseCardViewAttributes>())
        }

        inline operator fun <reified TView : BaseCardView, reified TAttributes : BaseCardViewAttributes> invoke() = BaseCardViewFactory(TView::class.java, TAttributes::class.java)
    }
}
