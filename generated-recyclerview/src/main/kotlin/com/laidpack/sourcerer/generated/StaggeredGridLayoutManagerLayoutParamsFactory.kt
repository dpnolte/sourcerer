package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.String

open class StaggeredGridLayoutManagerLayoutParamsFactory<TLayoutParams : StaggeredGridLayoutManager.LayoutParams, TAttributes : StaggeredGridLayoutManagerLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String =
            "androidx.recyclerview.widget.StaggeredGridLayoutManager.LayoutParams"

    override fun createInstance(context: Context): ViewGroup.LayoutParams = StaggeredGridLayoutManager.LayoutParams(StaggeredGridLayoutManager.LayoutParams.MATCH_PARENT, StaggeredGridLayoutManager.LayoutParams.MATCH_PARENT)

    companion object {
        init {
            InflaterComponent.addFactory(StaggeredGridLayoutManagerLayoutParamsFactory<StaggeredGridLayoutManager.LayoutParams, StaggeredGridLayoutManagerLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : StaggeredGridLayoutManager.LayoutParams, reified TAttributes : StaggeredGridLayoutManagerLayoutParamsAttributes> invoke() = StaggeredGridLayoutManagerLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
