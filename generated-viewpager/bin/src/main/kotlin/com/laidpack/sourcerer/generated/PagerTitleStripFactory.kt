package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.viewpager.widget.PagerTitleStrip
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class PagerTitleStripFactory<TView : PagerTitleStrip, TAttributes : PagerTitleStripAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "pagerTitleStrip"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = PagerTitleStrip(context)

    companion object {
        init {
            InflaterComponent.addFactory(PagerTitleStripFactory<PagerTitleStrip, PagerTitleStripAttributes>())
        }

        inline operator fun <reified TView : PagerTitleStrip, reified TAttributes : PagerTitleStripAttributes> invoke() = PagerTitleStripFactory(TView::class.java, TAttributes::class.java)
    }
}
