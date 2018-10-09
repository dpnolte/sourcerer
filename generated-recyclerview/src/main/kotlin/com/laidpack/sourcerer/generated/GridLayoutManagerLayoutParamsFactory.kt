package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class GridLayoutManagerLayoutParamsFactory<TLayoutParams : GridLayoutManager.LayoutParams, TAttributes : GridLayoutManagerLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "androidx.recyclerview.widget.GridLayoutManager.LayoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = GridLayoutManager.LayoutParams(GridLayoutManager.LayoutParams.MATCH_PARENT, GridLayoutManager.LayoutParams.MATCH_PARENT)

    companion object {
        init {
            InflaterComponent.addFactory(GridLayoutManagerLayoutParamsFactory<GridLayoutManager.LayoutParams, GridLayoutManagerLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : GridLayoutManager.LayoutParams, reified TAttributes : GridLayoutManagerLayoutParamsAttributes> invoke() = GridLayoutManagerLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
