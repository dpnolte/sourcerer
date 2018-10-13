package com.laidpack.sourcerer.generated.gridlayout

import android.content.Context
import android.view.View
import androidx.gridlayout.widget.GridLayout
import com.laidpack.sourcerer.generated.ViewGroupFactory
import java.lang.Class
import kotlin.String

open class GridLayoutFactory<TView : GridLayout, TAttributes : GridLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = GridLayout(context)

    companion object {
        const val elementType: String = "gridlayout.gridLayout"

        inline operator fun <reified TView : GridLayout, reified TAttributes : GridLayoutAttributes> invoke() = GridLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
