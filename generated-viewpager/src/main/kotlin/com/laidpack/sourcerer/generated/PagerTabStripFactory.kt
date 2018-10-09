package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.viewpager.widget.PagerTabStrip
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class PagerTabStripFactory<TView : PagerTabStrip, TAttributes : PagerTabStripAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "pagerTabStrip"

    override fun createInstance(context: Context): View = PagerTabStrip(context)

    companion object {
        init {
            InflaterComponent.addFactory(PagerTabStripFactory<PagerTabStrip, PagerTabStripAttributes>())
        }

        inline operator fun <reified TView : PagerTabStrip, reified TAttributes : PagerTabStripAttributes> invoke() = PagerTabStripFactory(TView::class.java, TAttributes::class.java)
    }
}
