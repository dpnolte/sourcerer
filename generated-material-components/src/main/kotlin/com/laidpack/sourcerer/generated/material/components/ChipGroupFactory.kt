package com.laidpack.sourcerer.generated.material.components

import android.content.Context
import android.view.View
import com.google.android.material.chip.ChipGroup
import com.laidpack.sourcerer.generated.ViewGroupFactory
import java.lang.Class
import kotlin.String

open class ChipGroupFactory<TView : ChipGroup, TAttributes : ChipGroupAttributes>(instanceType: Class<TView>, attributesType: Class<TAttributes>) : ViewGroupFactory<TView, TAttributes>(instanceType, attributesType) {
    override val elementType: String = Companion.elementType

    override fun createInstance(context: Context): View = ChipGroup(context)

    companion object {
        const val elementType: String = "chipGroup"

        inline operator fun <reified TView : ChipGroup, reified TAttributes : ChipGroupAttributes> invoke() = ChipGroupFactory(TView::class.java, TAttributes::class.java)
    }
}
