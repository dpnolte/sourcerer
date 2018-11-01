package com.laidpack.sourcerer.generated.leanback

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.leanback.widget.SearchOrbView
import com.laidpack.sourcerer.generated.FrameLayoutFactory
import java.lang.Class
import kotlin.String

open class SearchOrbViewFactory<TView : SearchOrbView, TAttributes : SearchOrbViewAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = SearchOrbView(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is SearchOrbView) {
            view.apply {
                attributes.lbsearchOrbIcon?.let {
                    val localLbsearchOrbIcon = ContextCompat.getDrawable(context, it) as Drawable
                    if (orbIcon != localLbsearchOrbIcon) {
                        orbIcon = localLbsearchOrbIcon
                    }
                }
            }
        }
    }

    companion object {
        const val elementType: String = "searchOrbView"

        inline operator fun <reified TView : SearchOrbView, reified TAttributes : SearchOrbViewAttributes> invoke() = SearchOrbViewFactory(TView::class.java, TAttributes::class.java)
    }
}
