package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import androidx.gridlayout.widget.GridLayout
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class GridLayoutFactory<TView : GridLayout, TAttributes : GridLayoutAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "gridLayout"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = GridLayout(context)

    companion object {
        init {
            InflaterComponent.addFactory(GridLayoutFactory<GridLayout, GridLayoutAttributes>())
        }

        inline operator fun <reified TView : GridLayout, reified TAttributes : GridLayoutAttributes> invoke() = GridLayoutFactory(TView::class.java, TAttributes::class.java)
    }
}
