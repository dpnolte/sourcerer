package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.view.View
import androidx.leanback.widget.BaseCardView
import com.laidpack.sourcerer.generated.FrameLayoutFactory
import java.lang.Class
import kotlin.String

open class BaseCardViewFactory<TView : BaseCardView, TAttributes : BaseCardViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = BaseCardView(context)

    companion object {
        const val elementType: String = "baseCardView"

        inline operator fun <reified TView : BaseCardView, reified TAttributes : BaseCardViewAttributes> invoke() = BaseCardViewFactory(TView::class.java, TAttributes::class.java)
    }
}
