package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.ViewGroup
import com.google.android.material.chip.ChipGroup
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class ChipGroupLayoutParamsFactory<TLayoutParams : ChipGroup.LayoutParams, TAttributes : ChipGroupLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "com.google.android.material.chip.ChipGroup.LayoutParams"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): ViewGroup.LayoutParams = ChipGroup.LayoutParams(ChipGroup.LayoutParams.MATCH_PARENT, ChipGroup.LayoutParams.MATCH_PARENT)

    companion object {
        init {
            InflaterComponent.addFactory(ChipGroupLayoutParamsFactory<ChipGroup.LayoutParams, ChipGroupLayoutParamsAttributes>())
        }

        inline operator fun <reified TLayoutParams : ChipGroup.LayoutParams, reified TAttributes : ChipGroupLayoutParamsAttributes> invoke() = ChipGroupLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
