package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import android.widget.GridLayout
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class GridLayoutLayoutParamsFactory<TLayoutParams : GridLayout.LayoutParams, TAttributes : GridLayoutLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "android.widget.GridLayout.LayoutParams"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): ViewGroup.LayoutParams = GridLayout.LayoutParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))

    companion object {
        init {
            InflaterComponent.addFactory(GridLayoutLayoutParamsFactory<GridLayout.LayoutParams, GridLayoutLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : GridLayout.LayoutParams, reified TAttributes : GridLayoutLayoutParamsAttributes> invoke() = GridLayoutLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
