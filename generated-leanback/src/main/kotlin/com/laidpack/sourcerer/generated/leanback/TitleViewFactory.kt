package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.view.View
import androidx.leanback.widget.TitleView
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.FrameLayoutFactory
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = TitleViewFactory.elementType,
        attributesClazz = TitleViewAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class TitleViewFactory<TView : TitleView, TAttributes : TitleViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = TitleView(context)

    companion object {
        const val elementType: String = "titleView"

        inline operator fun <reified TView : TitleView, reified TAttributes : TitleViewAttributes> invoke() = TitleViewFactory(TView::class.java, TAttributes::class.java)
    }
}
