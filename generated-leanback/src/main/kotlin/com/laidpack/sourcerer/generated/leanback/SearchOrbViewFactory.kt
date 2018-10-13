package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.view.View
import androidx.leanback.widget.SearchOrbView
import com.laidpack.sourcerer.generated.FrameLayoutFactory
import java.lang.Class
import kotlin.String

open class SearchOrbViewFactory<TView : SearchOrbView, TAttributes : SearchOrbViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = SearchOrbView(context)

    companion object {
        const val elementType: String = "searchOrbView"

        inline operator fun <reified TView : SearchOrbView, reified TAttributes : SearchOrbViewAttributes> invoke() = SearchOrbViewFactory(TView::class.java, TAttributes::class.java)
    }
}
