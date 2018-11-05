package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.view.View
import androidx.leanback.widget.BrowseFrameLayout
import com.laidpack.generator.api.ViewGroupElement
import com.laidpack.sourcerer.generated.FrameLayoutFactory
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsAttributes
import java.lang.Class
import kotlin.String

@ViewGroupElement(
        elementType = BrowseFrameLayoutFactory.elementType,
        attributesClazz = BrowseFrameLayoutAttributes::class,
        layoutParamAttributesClazz = ViewGroupLayoutParamsAttributes::class
)
open class BrowseFrameLayoutFactory<TView : BrowseFrameLayout, TAttributes : BrowseFrameLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = BrowseFrameLayout(context)

    companion object {
        const val elementType: String = "browseFrameLayout"

        inline operator fun <reified TView : BrowseFrameLayout, reified TAttributes : BrowseFrameLayoutAttributes> invoke() = BrowseFrameLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
