package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.view.ViewGroup
import com.google.android.material.chip.ChipGroup
import com.laidpack.sourcerer.generated.ViewGroupLayoutParamsFactory
import java.lang.Class
import kotlin.String

open class ChipGroupLayoutParamsFactory<TLayoutParams : ChipGroup.LayoutParams, TAttributes : ChipGroupLayoutParamsAttributes>(instanceType: Class<TLayoutParams>, attributesType: Class<TAttributes>) : ViewGroupLayoutParamsFactory<TLayoutParams, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): ViewGroup.LayoutParams = ChipGroup.LayoutParams(ChipGroup.LayoutParams.MATCH_PARENT, ChipGroup.LayoutParams.MATCH_PARENT)

    companion object {
        const val elementType: String = "com.google.android.material.chip.ChipGroup.LayoutParams"

        inline operator fun <reified TLayoutParams : ChipGroup.LayoutParams, reified TAttributes : ChipGroupLayoutParamsAttributes> invoke() = ChipGroupLayoutParamsFactory(TLayoutParams::class.java, TAttributes::class.java)
    }
}
