package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import com.google.android.material.tabs.TabItem
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class TabItemFactory<TView : TabItem, TAttributes : TabItemAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "tabItem"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = TabItem(context)

    companion object {
        init {
            InflaterComponent.addFactory(TabItemFactory<TabItem, TabItemAttributes>())
        }

        inline operator fun <reified TView : TabItem, reified TAttributes : TabItemAttributes> invoke() = TabItemFactory(TView::class.java, TAttributes::class.java)
    }
}
