package com.laidpack.sourcerer.generated.viewpager

import android.content.Context
import android.view.View
import androidx.viewpager.widget.PagerTitleStrip
import com.laidpack.sourcerer.generated.ViewGroupFactory
import java.lang.Class
import kotlin.String

open class PagerTitleStripFactory<TView : PagerTitleStrip, TAttributes : PagerTitleStripAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = PagerTitleStrip(context)

    override fun init(
        view: View,
        context: Context,
        attributes: TAttributes
    ) {
        super.init(view, context, attributes)
        if (view is PagerTitleStrip) {
            view.apply {
                attributes.gravity?.let {
                    setGravity(it.value)
                }
            }
        }
    }

    companion object {
        const val elementType: String = "pagerTitleStrip"

        inline operator fun <reified TView : PagerTitleStrip, reified TAttributes : PagerTitleStripAttributes> invoke() = PagerTitleStripFactory(TView::class.java, TAttributes::class.java)
    }
}
