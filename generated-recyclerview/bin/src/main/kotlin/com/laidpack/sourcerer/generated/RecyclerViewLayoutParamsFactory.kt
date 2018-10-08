package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class RecyclerViewLayoutParamsFactory<TLayoutParams : RecyclerView.LayoutParams, TAttributes : RecyclerViewLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupMarginLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "androidx.recyclerview.widget.RecyclerView.LayoutParams"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): ViewGroup.LayoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT)

    companion object {
        init {
            InflaterComponent.addFactory(RecyclerViewLayoutParamsFactory<RecyclerView.LayoutParams, RecyclerViewLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : RecyclerView.LayoutParams, reified TAttributes : RecyclerViewLayoutParamsAttributes> invoke() = RecyclerViewLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
