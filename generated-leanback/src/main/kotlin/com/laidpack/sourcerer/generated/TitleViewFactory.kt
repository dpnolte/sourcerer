package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.leanback.widget.TitleView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class TitleViewFactory<TView : TitleView, TAttributes : TitleViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "titleView"

    override fun createInstance(context: Context): View = TitleView(context)

    companion object {
        init {
            InflaterComponent.addFactory(TitleViewFactory<TitleView, TitleViewAttributes>())
        }

        inline operator fun <reified TView : TitleView, reified TAttributes : TitleViewAttributes> invoke() = TitleViewFactory(TView::class.java, TAttributes::class.java)
    }
}
