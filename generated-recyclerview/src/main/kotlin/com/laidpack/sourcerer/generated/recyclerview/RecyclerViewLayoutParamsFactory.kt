package com.laidpack.sourcerer.generated.recyclerview

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.laidpack.generator.api.LayoutParamsElement
import com.laidpack.sourcerer.generated.ViewGroupMarginLayoutParamsFactory
import java.lang.Class
import kotlin.String

@LayoutParamsElement(
        elementType = RecyclerViewLayoutParamsFactory.elementType,
        attributesClazz = RecyclerViewLayoutParamsAttributes::class
)
open class RecyclerViewLayoutParamsFactory<TLayoutParams : RecyclerView.LayoutParams, TAttributes : RecyclerViewLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupMarginLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT)

    companion object {
        const val elementType: String = "androidx.recyclerview.widget.RecyclerView.LayoutParams"

        inline operator fun <reified TLayoutParams : RecyclerView.LayoutParams, reified TAttributes : RecyclerViewLayoutParamsAttributes> invoke() = RecyclerViewLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
