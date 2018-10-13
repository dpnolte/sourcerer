package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.view.View
import com.google.android.material.bottomappbar.BottomAppBar
import com.laidpack.sourcerer.generated.ViewGroupFactory
import java.lang.Class
import kotlin.String

open class BottomAppBarFactory<TView : BottomAppBar, TAttributes : BottomAppBarAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = BottomAppBar(context)

    companion object {
        const val elementType: String = "bottomAppBar"

        inline operator fun <reified TView : BottomAppBar, reified TAttributes : BottomAppBarAttributes> invoke() = BottomAppBarFactory(TView::class.java, TAttributes::class.java)
    }
}
