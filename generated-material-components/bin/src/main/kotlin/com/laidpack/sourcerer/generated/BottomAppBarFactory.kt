package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import com.google.android.material.bottomappbar.BottomAppBar
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class BottomAppBarFactory<TView : BottomAppBar, TAttributes : BottomAppBarAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "bottomAppBar"

    override fun createInstance(context: Context): View = BottomAppBar(context)

    companion object {
        init {
            InflaterComponent.addFactory(BottomAppBarFactory<BottomAppBar, BottomAppBarAttributes>())
        }

        inline operator fun <reified TView : BottomAppBar, reified TAttributes : BottomAppBarAttributes> invoke() = BottomAppBarFactory(TView::class.java, TAttributes::class.java)
    }
}
