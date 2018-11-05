package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.view.View
import com.google.android.material.tabs.TabItem
import com.laidpack.generator.api.ViewElement
import com.laidpack.sourcerer.generated.ViewFactory
import java.lang.Class
import kotlin.String

@ViewElement(
        elementType = TabItemFactory.elementType,
        attributesClazz = TabItemAttributes::class
)
open class TabItemFactory<TView : TabItem, TAttributes : TabItemAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = TabItem(context)

    companion object {
        const val elementType: String = "tabItem"

        inline operator fun <reified TView : TabItem, reified TAttributes : TabItemAttributes> invoke() = TabItemFactory(TView::class.java, TAttributes::class.java)
    }
}
