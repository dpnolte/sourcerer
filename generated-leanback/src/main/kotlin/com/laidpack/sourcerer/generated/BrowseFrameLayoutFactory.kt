package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.leanback.widget.BrowseFrameLayout
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class BrowseFrameLayoutFactory<TView : BrowseFrameLayout, TAttributes : BrowseFrameLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "browseFrameLayout"

    override fun createInstance(context: Context): View = BrowseFrameLayout(context)

    companion object {
        init {
            InflaterComponent.addFactory(BrowseFrameLayoutFactory<BrowseFrameLayout, BrowseFrameLayoutAttributes>())
        }

        inline operator fun <reified TView : BrowseFrameLayout, reified TAttributes : BrowseFrameLayoutAttributes> invoke() = BrowseFrameLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
