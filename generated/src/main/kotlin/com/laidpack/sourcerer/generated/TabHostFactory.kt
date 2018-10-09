package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import android.widget.TabHost
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class TabHostFactory<TView : TabHost, TAttributes : TabHostAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : FrameLayoutFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "tabHost"

    override fun createInstance(context: Context): View = TabHost(context)

    companion object {
        init {
            InflaterComponent.addFactory(TabHostFactory<TabHost, TabHostAttributes>())
        }

        inline operator fun <reified TView : TabHost, reified TAttributes : TabHostAttributes> invoke() = TabHostFactory(TView::class.java, TAttributes::class.java)
    }
}
