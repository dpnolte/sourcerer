package com.laidpack.sourcerer.generated.recyclerview

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.laidpack.generator.api.LayoutParamsElement
import java.lang.Class
import kotlin.String

@LayoutParamsElement(
        elementType = GridLayoutManagerLayoutParamsFactory.elementType,
        attributesClazz = GridLayoutManagerLayoutParamsAttributes::class
)
open class GridLayoutManagerLayoutParamsFactory<TLayoutParams : GridLayoutManager.LayoutParams, TAttributes : GridLayoutManagerLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : RecyclerViewLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = GridLayoutManager.LayoutParams(GridLayoutManager.LayoutParams.MATCH_PARENT, GridLayoutManager.LayoutParams.MATCH_PARENT)

    companion object {
        const val elementType: String =
                "androidx.recyclerview.widget.GridLayoutManager.LayoutParams"

        inline operator fun <reified TLayoutParams : GridLayoutManager.LayoutParams, reified TAttributes : GridLayoutManagerLayoutParamsAttributes> invoke() = GridLayoutManagerLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
