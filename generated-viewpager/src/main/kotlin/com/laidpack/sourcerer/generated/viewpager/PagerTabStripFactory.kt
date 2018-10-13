package com.laidpack.sourcerer.generated.viewpager

import android.content.Context
import android.view.View
import androidx.viewpager.widget.PagerTabStrip
import com.laidpack.sourcerer.generated.ViewGroupFactory
import java.lang.Class
import kotlin.String

open class PagerTabStripFactory<TView : PagerTabStrip, TAttributes : PagerTabStripAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = PagerTabStrip(context)

    companion object {
        const val elementType: String = "pagerTabStrip"

        inline operator fun <reified TView : PagerTabStrip, reified TAttributes : PagerTabStripAttributes> invoke() = PagerTabStripFactory(TView::class.java, TAttributes::class.java)
    }
}
