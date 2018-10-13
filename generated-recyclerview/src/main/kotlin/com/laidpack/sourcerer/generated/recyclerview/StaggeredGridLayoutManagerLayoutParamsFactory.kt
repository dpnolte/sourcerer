package com.laidpack.sourcerer.generated.recyclerview

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsFactory
import java.lang.Class
import kotlin.String

open class StaggeredGridLayoutManagerLayoutParamsFactory<TLayoutParams : StaggeredGridLayoutManager.LayoutParams, TAttributes : StaggeredGridLayoutManagerLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = StaggeredGridLayoutManager.LayoutParams(StaggeredGridLayoutManager.LayoutParams.MATCH_PARENT, StaggeredGridLayoutManager.LayoutParams.MATCH_PARENT)

    companion object {
        const val elementType: String =
                "androidx.recyclerview.widget.StaggeredGridLayoutManager.LayoutParams"

        inline operator fun <reified TLayoutParams : StaggeredGridLayoutManager.LayoutParams, reified TAttributes : StaggeredGridLayoutManagerLayoutParamsAttributes> invoke() = StaggeredGridLayoutManagerLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
