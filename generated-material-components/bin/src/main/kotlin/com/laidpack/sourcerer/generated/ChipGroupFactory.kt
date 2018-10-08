package com.laidpack.sourcerer.generated

import android.content.Context
import android.view.View
import com.google.android.material.chip.ChipGroup
import com.laidpack.sourcerer.service.InflaterComponent
import java.lang.Class
import kotlin.Int
import kotlin.String

open class ChipGroupFactory<TView : ChipGroup, TAttributes : ChipGroupAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementName: String = "chipGroup"

    override val fallBackElementName: String? = null

    override val minimumApiLevel: Int = 0

    override fun createInstance(context: Context): View = ChipGroup(context)

    companion object {
        init {
            InflaterComponent.addFactory(ChipGroupFactory<ChipGroup, ChipGroupAttributes>())
        }

        inline operator fun <reified TView : ChipGroup, reified TAttributes : ChipGroupAttributes> invoke() = ChipGroupFactory(TView::class.java, TAttributes::class.java)
    }
}
