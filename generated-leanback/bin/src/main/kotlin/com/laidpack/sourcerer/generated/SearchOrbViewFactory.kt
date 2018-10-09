package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.leanback.widget.SearchOrbView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class SearchOrbViewFactory<TView : SearchOrbView, TAttributes : SearchOrbViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "searchOrbView"

    override fun createInstance(context: Context): View = SearchOrbView(context)

    companion object {
        init {
            InflaterComponent.addFactory(SearchOrbViewFactory<SearchOrbView, SearchOrbViewAttributes>())
        }

        inline operator fun <reified TView : SearchOrbView, reified TAttributes : SearchOrbViewAttributes> invoke() = SearchOrbViewFactory(TView::class.java, TAttributes::class.java)
    }
}
